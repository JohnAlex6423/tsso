package com.olcow.tsso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.olcow.tsso.mapper")
public class TssoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TssoApplication.class, args);
	}

}