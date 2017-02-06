package com.currency.conversion;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("com.currency.conversion.web")
public class WebConfig extends WebMvcConfigurerAdapter {
}
