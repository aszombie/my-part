//package com.finalproject.demo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.example.demo.interceptor.JWTinterceptor;
//
//@Configuration
//@EnableTransactionManagement
//public class SpringBootConfig implements WebMvcConfigurer {
//	@Autowired
//	private JWTinterceptor jwtInterceptor;
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(jwtInterceptor).addPathPatterns("/member/detail");
//	}
//}
