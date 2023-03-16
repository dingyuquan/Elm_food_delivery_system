package com.neusoft.strategy;

public abstract class CreditToMoneyStrategy {
	public abstract double creditToMoney(Integer credit);
	public abstract int moneyBackTocredit(double money);
}
