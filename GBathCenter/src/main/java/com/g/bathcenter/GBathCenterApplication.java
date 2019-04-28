package com.g.bathcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class GBathCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(GBathCenterApplication.class, args);
	}

}
