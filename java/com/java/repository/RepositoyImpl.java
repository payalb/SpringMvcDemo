package com.java.repository;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation=Propagation.REQUIRED,transactionManager="txManager")
@Primary
public class RepositoyImpl implements AccountRepository{
	
	@Autowired JdbcTemplate template;

	@Transactional(readOnly=false, rollbackFor=SQLException.class)
	public void depositMoney(float amt, int accId) {
		template.update("update account set amt= amt+? where accId= ?",amt, accId);
		throw new RuntimeException();//by default does a rollback for runtime exception
	
	}
	@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
	public void withdrawMoney(float amt, int accId) {
		//Do u want see uncommitted: dirty read
		//committed data:
		//isolation..: even not see changes which are committed
		float amtInDb= template.queryForObject("select amt from account where accId= ?",new Object[] { accId}, float.class);
		if(amtInDb>= amt) {
		template.update("update account set amt= amt-? where accId= ? ",amt, accId);
		}else {
			throw new DatabaseException("Not enough balance!");
		}
		//rollback is done automatically for runtimeExceptions
	}

	@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED)
	public float getBalance(int accId) {
		return template.queryForObject("select amt from account where accId= ?",new Object[] { accId}, float.class);
	}
	//transfer money: withdraw+ deposit: same tx.
}
