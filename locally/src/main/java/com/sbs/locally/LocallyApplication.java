package com.sbs.locally;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LocallyApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocallyApplication.class, args);
	}

}
