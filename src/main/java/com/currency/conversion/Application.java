package com.currency.conversion;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@Import({ ApplicationConfig.class, SecurityConfig.class, WebConfig.class })
public class Application extends SpringBootServletInitializer {
  
	@Value("${spring.datasource.url}")
	private String dbUrl;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
	
	@Bean
	@Primary
	public Validator localValidatorFactoryBean() {
	    return new LocalValidatorFactoryBean();
	}
	
	@Bean
	public DataSource dataSource() {
	    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	        dataSourceBuilder.url(dbUrl);
	        dataSourceBuilder.driverClassName(driverClassName);
	        dataSourceBuilder.username(username);
	        dataSourceBuilder.password(password);
	        return dataSourceBuilder.build();   
	}
}