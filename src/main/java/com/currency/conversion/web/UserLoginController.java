package com.currency.conversion.web;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class UserLoginController extends WebMvcConfigurerAdapter {

	@RequestMapping("/")
	public String home(Principal principal) {

		if (principal == null) {
			return "redirect:login";
		}
		return "redirect:/home";
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}
}
