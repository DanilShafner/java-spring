package com.shafner.mod1.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.shafner.mod1")
@PropertySource("classpath:application.properties")

public class AppConfiguration {
}
