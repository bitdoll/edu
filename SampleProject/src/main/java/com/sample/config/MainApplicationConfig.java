package com.sample.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * JPA Reposiory / Component Base Setting
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages={"com.sample.domain"})
@EnableEntityLinks
@ComponentScan(basePackages={"com.sample"})
public class MainApplicationConfig {

}
