package com.sample;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;
 
@SpringBootApplication
public class SampleProjectApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SampleProjectApplication.class, args);
	}
	
	@Bean
	public RequestContextListener requestContextListener(){
	    return new RequestContextListener();
	} 
	
	@Override 
	public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup( servletContext );
	}	
}
