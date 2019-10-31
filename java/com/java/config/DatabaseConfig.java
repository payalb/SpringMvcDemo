package com.java.config;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


public class DatabaseConfig {

	@Bean
	public JdbcTemplate template() {
		JdbcTemplate template= new JdbcTemplate();
		template.setDataSource(datasource());
		return template;
	}
	
	@Bean("datasource")
	public DataSource datasource() {
		DriverManagerDataSource ds= new DriverManagerDataSource();
		ds.setUrl("jdbc:postgresql://localhost:5432/postgres");
		ds.setUsername("postgres");
		ds.setPassword("postgres");
		ds.setDriverClassName("org.postgresql.Driver");
	//	ds.setDefaultAutoCommit(false);
		return ds;
	}
	
	@Bean("txManager")
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager txmanager= new DataSourceTransactionManager();
		txmanager.setDataSource(datasource());
		return txmanager;
	}
	
}
