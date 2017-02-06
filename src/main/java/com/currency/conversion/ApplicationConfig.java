package com.currency.conversion;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@Configuration
@EnableCaching
@EnableJpaRepositories(basePackages = "com.currency.conversion.dao")
@ComponentScan(basePackages = { "com.currency.conversion.service", "com.currency.conversion.dao",
		"com.currency.conversion.model" })
@EntityScan("com.currency.conversion.model")
public class ApplicationConfig {
	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}
}
