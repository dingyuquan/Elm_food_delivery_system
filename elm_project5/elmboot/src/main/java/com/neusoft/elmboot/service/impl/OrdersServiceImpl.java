package com.neusoft.elmboot.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.elmboot.mapper.CartMapper;
import com.neusoft.elmboot.mapper.OrderDetailetMapper;
import com.neusoft.elmboot.mapper.OrdersMapper;
import com.neusoft.elmboot.po.Cart;
import com.neusoft.elmboot.po.OrderDetailet;
import com.neusoft.elmboot.po.Orders;
import com.neusoft.elmboot.service.OrdersService;
import com.neusoft.elmboot.service.ScoreService;
import com.neusoft.elmboot.service.VirtualWalletService;
import com.neusoft.elmboot.strategy.CreditToMoneyStrategy;
import com.neusoft.elmboot.strategy.CreditToMoneyStrategy1;
import com.neusoft.elmboot.strategy.MoneyToCreditStrategy;
import com.neusoft.elmboot.strategy.MoneyToCreditStrategy1;
import com.neusoft.elmboot.util.CommonUtil;
import com.neusoft.elmboot.vo.ScoreVO;

@Service
public class OrdersServiceImpl implements OrdersService{

	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private OrdersMapper ordersMapper;
	
	@Autowired
	private OrderDetailetMapper orderDetailetMapper;
	
	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private VirtualWalletService virtualWalletService;
	
	private CreditToMoneyStrategy creditToMoneyStrategy = new CreditToMoneyStrategy1();
	private MoneyToCreditStrategy moneyToCreditStrategy = new MoneyToCreditStrategy1();
	
	@Override
	@Transactional
	public int createOrders(Orders orders) {
		//1、查询当前用户购物车中当前商家的所有食品
		Cart cart = new Cart();
		cart.setUserId(orders.getUserId());
		cart.setBusinessId(orders.getBusinessId());
		List<Cart> cartList = cartMapper.listCart(cart);
		
		//2、创建订单（返回生成的订单编号）
		orders.setOrderDate(CommonUtil.getCurrentDate());
		ordersMapper.saveOrders(orders);
		int orderId = orders.getOrderId();
		
		//3、批量添加订单明细
		List<OrderDetailet> list = new ArrayList<>();
		for(Cart c : cartList) {
			OrderDetailet od = new OrderDetailet();
			od.setOrderId(orderId);
			od.setFoodId(c.getFoodId());
			od.setQuantity(c.getQuantity());
			list.add(od);
		}
		orderDetailetMapper.saveOrderDetailetBatch(list);
		
		//4、从购物车表中删除相关食品信息
		cartMapper.removeCart(cart);
		
		return orderId;
	}
	
	@Override
	public int payOrdersUsingScore(Integer orderId) { // 返回0代表失败，1代表成功

		// 1、根据订单id取出订单
		Orders order = getOrdersById(orderId);
		String userId = order.getUserId();
		double total = order.getOrderTotal();
		// 2、从积分中查出积分
		BigDecimal myBalanceTmp = virtualWalletService.getBalanceByUserId(userId);
		// double myBalance = myBalanceTmp.doubleValue();
		int myCredit = scoreService.getCreditByUserId(userId);
		double deductedMoney = creditToMoneyStrategy.creditToMoney(myCredit);

		// 3、判断钱包余额是否足够
		/*
		 * if(!((myBalance >= total) || ((myBalance >= total * 0.8) && (deductedMoney >=
		 * total * 0.2)))) { return -1; // 余额不足时，不进行任何操作 }
		 */

		// 4、用积分支付（假设，20%可用积分，剩下80%需要现金） 需要添加判断钱包是否足够支付
		if (deductedMoney >= total * 0.2) { // 积分达到阈值，只抵用20%
			// 支付积分部分
			ScoreVO scoreVO1 = new ScoreVO();
			scoreVO1.setUserId(userId);
			scoreVO1.setChannelId(3);
			scoreVO1.setCredit(creditToMoneyStrategy.moneyBackTocredit(total * 0.2)); // 要改
			int res = virtualWalletService.payOrderByUserId(userId, new BigDecimal(total * 0.8));
			if(res == 0) return 0;
			scoreService.expendCredit(scoreVO1);
		} else {
			ScoreVO scoreVO2 = new ScoreVO();
			scoreVO2.setUserId(userId);
			scoreVO2.setChannelId(3);
			scoreVO2.setCredit(creditToMoneyStrategy.moneyBackTocredit(deductedMoney));
			double leftMoney = total - deductedMoney;
			int res = virtualWalletService.payOrderByUserId(userId, new BigDecimal(leftMoney));
			if(res == 0) return 0;
			scoreService.expendCredit(scoreVO2);
		}

		// 5、根据订单获取积分
		int creditNum = moneyToCreditStrategy.moneyToCredit(total);
		ScoreVO scoreVO3 = new ScoreVO();
		scoreVO3.setUserId(userId);
		scoreVO3.setChannelId(1);
		scoreVO3.setCredit(creditNum);
		scoreService.saveCredit(scoreVO3);
		return ordersMapper.payOrdersById(orderId);
	}

	@Override
	public Orders getOrdersById(Integer orderId) {
		return ordersMapper.getOrdersById(orderId);
	}

	@Override
	public List<Orders> listOrdersByUserId(String userId) {
		return ordersMapper.listOrdersByUserId(userId);
	}

	@Override
	public int payOrders(Integer orderId) {
		// TODO Auto-generated method stub
		// 1、根据订单id取出订单
		Orders order = getOrdersById(orderId);
		String userId = order.getUserId();
		double total = order.getOrderTotal();
		
		int res = virtualWalletService.payOrderByUserId(userId, new BigDecimal(total));
		if(res == 0) return 0;
		
		int creditNum = moneyToCreditStrategy.moneyToCredit(total);
		ScoreVO scoreVO3 = new ScoreVO();
		scoreVO3.setUserId(userId);
		scoreVO3.setChannelId(1);
		scoreVO3.setCredit(creditNum);
		scoreService.saveCredit(scoreVO3);
		
		return ordersMapper.payOrdersById(orderId);
	}

	@Override
	public List<String> showDeductionAmount(Integer orderId) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		Integer cre;
		Double amount;
		
		// 1、根据订单id取出订单
		Orders order = getOrdersById(orderId);
		String userId = order.getUserId();
		double total = order.getOrderTotal();
		// 2、从积分中查出积分
		BigDecimal myBalanceTmp = virtualWalletService.getBalanceByUserId(userId);
		// double myBalance = myBalanceTmp.doubleValue();
		int myCredit = scoreService.getCreditByUserId(userId);
		double deductedMoney = creditToMoneyStrategy.creditToMoney(myCredit);
		
		if (deductedMoney >= total * 0.2) { // 积分达到阈值，只抵用20%
			// 支付积分部分
			amount = total * 0.2;
			cre = creditToMoneyStrategy.moneyBackTocredit(amount);
		} else {
			cre = myCredit;
			amount = deductedMoney;
		}
		
		list.add(cre.toString());
		list.add(amount.toString());
		return list;
	}

}
