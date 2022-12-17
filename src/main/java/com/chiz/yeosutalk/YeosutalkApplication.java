package com.chiz.yeosutalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class YeosutalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(YeosutalkApplication.class, args);
	}

}
