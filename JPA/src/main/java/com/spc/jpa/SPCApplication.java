package com.spc.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import com.spc.jpa.config.MainApplicationConfig;


/**
 * Main Class
 * @author lKJ
 */
@SpringBootApplication(scanBasePackageClasses = {MainApplicationConfig.class})
public class SPCApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SPCApplication.class, args);
		
	}

}
