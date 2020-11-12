package com.algaworks.brewer;

import com.algaworks.brewer.storage.FotoLocalStorage;
import com.algaworks.brewer.storage.FotoStorage;
import com.algaworks.brewer.thymeleaf.dialect.BrewerDialect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class BrewerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrewerApplication.class, args);
	}

	@Bean
	public BrewerDialect brewerDialect(){
		return new BrewerDialect();
	}

	@Bean
	public FotoStorage fotoStorage(){
		return new FotoLocalStorage();
	}
}
