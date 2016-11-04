package com.spc.jpa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * JPA Reposiory / Component Base Setting
 * @author lKJ
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages={"com.spc.jpa.domain"})
@EnableEntityLinks
@ComponentScan(basePackages={"com.spc.jpa"})
public class MainApplicationConfig {

}
