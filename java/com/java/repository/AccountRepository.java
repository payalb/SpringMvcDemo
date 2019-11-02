package com.java.repository;

public interface AccountRepository {
	public void depositMoney(float amt, int accId);
	public void withdrawMoney(float amt, int accId);
	/*public void transferMoney(int amt, int acc1, int acc2);*/
	public float getBalance(int accId);
}
