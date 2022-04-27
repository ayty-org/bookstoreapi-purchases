package br.com.bookstoreapi.purchases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookstorePurchasesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstorePurchasesApplication.class, args);
	}

}
