package com.example.Testbcnc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.Testbcnc.dto")
public class TestbcncApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestbcncApplication.class, args);
	}

}
