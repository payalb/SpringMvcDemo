package com.java.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.java.repository.AccountRepository;

@Service
@Transactional(propagation=Propagation.REQUIRES_NEW,transactionManager="txManager")
public class AccountServiceImpl implements AccoutnService {
	Logger logger= Logger.getLogger(AccountServiceImpl.class);

	@Autowired
	AccountRepository repository;

	@Override
	public void depositMoney(float amt, int accId) {
		logger.info("Depositing method called");
		repository.depositMoney(amt, accId);
		logger.info("Depositing method exiting..");
	}

	@Override
	public void withdrawMoney(float amt, int accId) {
		//tx.begin()
		repository.withdrawMoney(amt, accId);
	}

	@Override
	public void transferMoney(float amt, int acc1, int acc2) {
		//tx.begin()
		repository.withdrawMoney(amt, acc1);
		repository.depositMoney(amt, acc2);
	}

	@Override
	public float getBalance(int accId) {
		return repository.getBalance(accId);
	}

}
