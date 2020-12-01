package com.impl.zuulapigateway.zuulapigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.impl.zuulapigateway.zuulapigateway.filter.CustomPostFilter;
import com.impl.zuulapigateway.zuulapigateway.filter.CustomPreFilter;

@Configuration
public class ZuulConfig {

	@Bean
	public CustomPreFilter customPreFilter() {
		return new CustomPreFilter();
	}
	
	@Bean
	public CustomPostFilter customPostFilter() {
		return new CustomPostFilter();
	}
}
