package com.thuannd.crawler.fxssi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.thuannd")
public class FxssiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FxssiApplication.class, args);
	}

}
