package com.happy.delivery.infra.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.happy.delivery.infra.mybatis")
public class MybatisConfig {

}
