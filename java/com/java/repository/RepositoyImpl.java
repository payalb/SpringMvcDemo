package com.java.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation=Propagation.REQUIRED,transactionManager="txManager", readOnly=false)
@Primary

public class RepositoyImpl implements AccountRepository{
	
	@Autowired JdbcTemplate template;

	public void depositMoney(float amt, int accId) {
		template.update("update account set amt= amt+? where accId= ?",amt, accId);
		throw new RuntimeException();
	
	}

	public void withdrawMoney(float amt, int accId) {
		template.update("update account set amt= amt-? where accId= ?",amt, accId);
		
	}

}
