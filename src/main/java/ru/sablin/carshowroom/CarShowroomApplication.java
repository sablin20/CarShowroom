package ru.sablin.carshowroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CarShowroomApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarShowroomApplication.class, args);
    }
}