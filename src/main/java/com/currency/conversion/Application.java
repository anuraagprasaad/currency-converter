package com.currency.conversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
	
}
