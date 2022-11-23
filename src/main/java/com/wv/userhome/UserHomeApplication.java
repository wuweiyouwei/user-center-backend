package com.wv.userhome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.wv.userhome.mapper")
public class UserHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserHomeApplication.class, args);
    }

}
