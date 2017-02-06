[![Build Status](https://travis-ci.org/toedter/chatty.svg?branch=master)](https://travis-ci.org/toedter/chatty)

# currency-converter
Currency converter application using a public currency converter API 

This is a demo Currency Converter web application that uses one of the public currency converter APIs from Open exchange rates.

##Technologies

Spring Framework

Spring Boot 

Spring MVC

Spring Security

Spring Data

JPA - with Hibernate as persistence provider and MySQL as database.

Ehcache 

Thymeleaf - pretty cool view technology which nicely integrates with Spring MVC. 

Cucumber - for BDD-style tests

##System requirements

JDK 8 and Maven >= 3.2.

##Configurations

application.properties contains various settings for MySQL and OpenExchangeRates app id which is required for calling the API. 

##Spring Actuator

After you register and log in you will be able to access Spring Actuator endpoints, e.g. http://localhost:8080/dump for thread dump, health indicators and much more.

## Continous Integraion

Continous integration with Travis-CI. Build indicator status is displayed at the top.

## Running the application

mvn spring-boot:run

This should start the embedded Tomcat and the application will be available at http://localhost:8080/

## Running the tests

mvn test

