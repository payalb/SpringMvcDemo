package com.java.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DbDataLoader {
	
	@Autowired JdbcTemplate template;

	@PostConstruct
	@Transactional(transactionManager="txManager")
	public void initData() {
		template.execute("drop table if exists account;");
		template.execute("create table if not exists account ( accId integer primary key, amt numeric(10,2));");
		template.update("insert into account values (1, 5000);");
		template.update("insert into account values (2, 5000);");
	}
	@PreDestroy
	public void destroy() {
		
	}
}
