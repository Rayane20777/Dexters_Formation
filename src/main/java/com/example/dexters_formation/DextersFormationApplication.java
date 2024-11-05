package com.example.dexters_formation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DextersFormationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DextersFormationApplication.class, args);
	}

}
