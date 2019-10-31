package com.java.service;

public interface AccoutnService {

	public void depositMoney(float amt, int accId);
	public void withdrawMoney(float amt, int accId);
	public void transferMoney(float amt, int acc1, int acc2);
}
