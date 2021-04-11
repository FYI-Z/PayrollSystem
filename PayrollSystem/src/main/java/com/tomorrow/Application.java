package com.tomorrow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.tomorrow.dao")
public class Application {
    public static void main(String[] args) {
        Long time=System.currentTimeMillis();
        SpringApplication.run(Application.class,args);
        System.out.println("===应用启动耗时："+(System.currentTimeMillis()-time)+"===");
    }
}