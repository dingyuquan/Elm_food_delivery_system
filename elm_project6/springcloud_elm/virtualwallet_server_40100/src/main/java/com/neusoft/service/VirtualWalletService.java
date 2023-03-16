package com.neusoft.service;

import java.math.BigDecimal;
import java.util.List;

import com.neusoft.domain.Transaction;

public interface VirtualWalletService {
	public int createWalletByUserId(String userId);
	public BigDecimal getBalanceByUserId(String userId);
	public int cashoutByWalletId(String userId, BigDecimal amount);
	public int cashinByWalletId(String userId, BigDecimal amount);
	public List<Transaction> getTransactionByUserId(String userId);
	public int payOrderByUserId(String userId, BigDecimal orderTotal);
}
