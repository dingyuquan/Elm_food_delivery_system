package com.neusoft.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.domain.Transaction;
import com.neusoft.domain.VirtualWallet;
import com.neusoft.po.CommonResult;
import com.neusoft.po.User;
import com.neusoft.service.VirtualWalletService;

@CrossOrigin("*")
@RestController
@RequestMapping("/VirtualWalletController")
public class VirtualWalletController {
	
	@Autowired
	private VirtualWalletService virtualwalletService;
	
	@PostMapping("/createWalletByUserId/{userId}")
	public CommonResult<Integer> creatWalletByUserId(
			@PathVariable("userId") String userId) throws Exception{
		int result = virtualwalletService.createWalletByUserId(userId);
		return new CommonResult(200, "success", result);
	}
	
	@GetMapping("/getBalanceByUserId/{userId}")
	public CommonResult<BigDecimal> getBalanceByUserId(
			@PathVariable("userId") String userId) throws Exception{
		
		BigDecimal result = virtualwalletService.getBalanceByUserId(userId);
		return new CommonResult(200, "success", result);
	}
	
	@PutMapping("/cashoutByWalletId/{userId}/{amount}")
	public CommonResult<Integer> cashoutByWalletId(
			@PathVariable("userId") String userId,
			@PathVariable("amount") BigDecimal amount) throws Exception{
		int result = virtualwalletService.cashoutByWalletId(userId, amount);
		return new CommonResult(200, "success", result);
	}
	
	@PutMapping("/cashinByWalletId/{userId}/{amount}")
	public CommonResult<Integer> cashinByWalletId(
			@PathVariable("userId") String userId,
			@PathVariable("amount") BigDecimal amount) throws Exception{
		int result = virtualwalletService.cashinByWalletId(userId, amount);
		return new CommonResult(200, "success", result);
	}
	
	@GetMapping("/getTransactionByUserId/{userId}")
	public CommonResult<List<Transaction>> getTransactionByUserId(
			@PathVariable("userId") String userId) throws Exception{
		List<Transaction> result = virtualwalletService.getTransactionByUserId(userId);
		return new CommonResult(200, "success", result);
	}
	
	@PutMapping("/payOrderByUserId/{userId}/{orderTotal}")
	public CommonResult<Integer> payOrderByUserId(
			@PathVariable("userId") String userId,
			@PathVariable("orderTotal") BigDecimal orderTotal) throws Exception{
		int result = virtualwalletService.payOrderByUserId(userId, orderTotal);
		return new CommonResult(200, "success", result);
	}
	
	
}
