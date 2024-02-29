package com.aiplusplus.favorites;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@Slf4j
@MapperScan("com.aiplusplus.favorites.mapper")
@SpringBootApplication
public class FavoritesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FavoritesApplication.class, args);
        log.info("FavoritesApplication started successfully!");
    }

}
