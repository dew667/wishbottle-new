package com.hust13.wishbottle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan(value = "com.hust13.wishbottle.mapper")
@SpringBootApplication
@EnableCaching
public class WishbottleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WishbottleApplication.class, args);
    }

}
