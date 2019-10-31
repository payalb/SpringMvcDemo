package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.java.repository.AccountRepository;

@Service
@Transactional(propagation=Propagation.REQUIRES_NEW,transactionManager="txManager")
public class AccountServiceImpl implements AccoutnService {

	@Autowired
	AccountRepository repository;

	@Override
	public void depositMoney(float amt, int accId) {
		repository.depositMoney(amt, accId);
	}

	@Override
	public void withdrawMoney(float amt, int accId) {
		repository.withdrawMoney(amt, accId);
	}

	@Override
	public void transferMoney(float amt, int acc1, int acc2) {
		repository.withdrawMoney(amt, acc1);
		repository.depositMoney(amt, acc2);
	}

}
