package com.currency.conversion.test.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", monochrome = true, plugin = {
		"html:target/cucumber-html-report"},
glue={"cucumber.api.spring", "classpath:com.currency.conversion.test.cucumber"})
public class RunCukesTest {

}
