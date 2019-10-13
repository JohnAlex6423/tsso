package com.olcow.tsso;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.olcow.tsso.dao")
public class TssoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TssoApplication.class, args);
	}

}