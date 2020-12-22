package com.spring.redis.streams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RedisConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisConsumerApplication.class, args);
	}

}
