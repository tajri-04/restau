package com.javatech.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.javatech.*"})
@EntityScan(basePackages = {"com.javatech.*"})
@EnableJpaRepositories(basePackages = {"com.javatech.*"})
public class Config {
 }
