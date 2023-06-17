package com.dareen.spu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableFeignClients("com.dareen.spu.User")
@ComponentScan(basePackages = "com.dareen.spu")
@Import(CurrencyConverterController.class)
public class SpuApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpuApplication.class, args);
	}

}
