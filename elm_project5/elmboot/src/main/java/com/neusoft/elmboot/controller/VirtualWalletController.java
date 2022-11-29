package com.neusoft.elmboot.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.domain.Transaction;
import com.neusoft.elmboot.domain.VirtualWallet;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.VirtualWalletService;

@RestController
@RequestMapping("/VirtualWalletController")
public class VirtualWalletController {
	
	@Autowired
	private VirtualWalletService virtualwalletService;
	
//	@RequestMapping("/creatWalletByUserId")
//	public int creatWalletByUserId(String userId) throws Exception{
//		return virtualwalletService.creatWalletByUserId(userId);
//	}
	
	@RequestMapping("/getBalanceByUserId")
	public BigDecimal getBalanceByUserId(String userId) throws Exception{
		return virtualwalletService.getBalanceByUserId(userId);
	}
	
	@RequestMapping("/cashoutByWalletId")
	public int cashoutByWalletId(String userId, BigDecimal amount) throws Exception{
		return virtualwalletService.cashoutByWalletId(userId, amount);
	}
	
	@RequestMapping("/cashinByWalletId")
	public int cashinByWalletId(String userId, BigDecimal amount) throws Exception{
		return virtualwalletService.cashinByWalletId(userId, amount);
	}
	
	@RequestMapping("/getTransactionByUserId")
	public List<Transaction> getTransactionByUserId(String userId) throws Exception{
		return virtualwalletService.getTransactionByUserId(userId);
	}
	
	@RequestMapping("/payOrderByUserId")
	public int payOrderByUserId(String userId, BigDecimal orderTotal) throws Exception{
		return virtualwalletService.payOrderByUserId(userId, orderTotal);
	}
	
	
}
