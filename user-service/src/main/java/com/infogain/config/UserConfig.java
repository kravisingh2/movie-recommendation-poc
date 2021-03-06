package com.infogain.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.infogain.controller", "com.infogain.service",  "com.infogain.exception"})
@EntityScan(basePackages = {"com.infogain.entity"})
@EnableJpaRepositories(basePackages = {"com.infogain.repository"})
public class UserConfig {

}
