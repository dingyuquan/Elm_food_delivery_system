package com.neusoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.po.CommonResult;
import com.neusoft.po.Orders;
import com.neusoft.service.OrdersService;

@CrossOrigin("*")
@RestController
@RequestMapping("/OrdersController")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;
	
	@PostMapping("/createOrders/{userId}/{businessId}/{daId}/{orderTotal}")
	public CommonResult<Integer> createOrders(
			@PathVariable("userId") String userId,
			@PathVariable("businessId") Integer businessId,
			@PathVariable("daId") Integer daId,
			@PathVariable("orderTotal") Double orderTotal) throws Exception{
			Orders param = new Orders();
			param.setUserId(userId);
			param.setBusinessId(businessId);
			param.setDaId(daId);
			param.setOrderTotal(orderTotal);
			int orderId = ordersService.createOrders(param);
			return new CommonResult(200, "success", orderId);
	}
	
	@GetMapping("/getOrdersById/{orderId}")
	public CommonResult<Orders> getOrdersById(@PathVariable("orderId") Integer orderId) throws Exception{
		Orders orders = ordersService.getOrdersById(orderId);
		return new CommonResult(200, "success", orders);
	}

	@GetMapping("/listOrdersByUserId/{userId}")
	public CommonResult<Orders> listOrdersByUserId(@PathVariable("userId") String userId) throws Exception{
		List<Orders> list = ordersService.listOrdersByUserId(userId);
		return new CommonResult(200, "success", list);
	}
	
	// 下面三个方法的修改不保证正确
	// 本方法用积分支付订单（支付一部分）
	@PutMapping("/payOrdersUsingScore/{orderId}")
	public CommonResult<Integer> payOrdersUsingScore(@PathVariable("orderId") Integer orderId) throws Exception{
		int result = ordersService.payOrdersUsingScore(orderId);
		return new CommonResult(200, "success", result);
	}
	
	// 本方法不用积分支付订单，只用钱包
	@PutMapping("/payOrders/{orderId}")
	public CommonResult<Integer> payOrders(@PathVariable("orderId") Integer orderId) throws Exception{
		int result = ordersService.payOrders(orderId);
		return new CommonResult(200, "success", result);
	}
	
	// 本方法用于返回当前订单中使用积分的抵扣情况，返回值用了一个list封装了两个数：表示花费积分和抵扣金额
	@GetMapping("/showDeductionAmount/{orderId}")
	public CommonResult<List> showDeductionAmount(@PathVariable("orderId") Integer orderId) throws Exception{
		List<String> list = ordersService.showDeductionAmount(orderId);
		return new CommonResult(200, "success", list);
	}
}
