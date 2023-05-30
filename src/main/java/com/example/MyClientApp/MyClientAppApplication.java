package com.example.MyClientApp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "MyClient",version = "1.0.0",description = "MyClient"))
public class MyClientAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyClientAppApplication.class, args);
	}

}
