package com.currency.conversion.test.cucumber;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.currency.conversion.ApplicationConfig;
import com.currency.conversion.SecurityConfig;

@Configuration
@EnableAutoConfiguration
@Import({ ApplicationConfig.class, SecurityConfig.class })
@ActiveProfiles("test")
@Component
/*@EnableJpaRepositories(basePackages = "com.currency.conversion.dao")
@ComponentScan(basePackages = { "com.currency.conversion.service", "com.currency.conversion.dao", "com.currency.conversion.model" })*/
public class TestConfig {
	
/*	@Value("${spring.datasource.url}")
	private String dbUrl;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	
	@Value("${spring.datasource.password}")
	private String password;*/
	
	@Bean
	public Validator localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}
	
	@Bean
	public DataSource dataSource() {
	    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	        dataSourceBuilder.url("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6154997?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull");
	        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
	        dataSourceBuilder.username("sql6154997");
	        dataSourceBuilder.password("pvqUKLzxAT");
	        return dataSourceBuilder.build();   
	}
}
