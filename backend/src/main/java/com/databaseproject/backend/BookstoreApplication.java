package com.databaseproject.backend;

import com.databaseproject.backend.config.RsaKeyProperties;

import com.databaseproject.backend.repository.classes.UserRepository;
import com.databaseproject.backend.repository.interfaces.IManagerRepository;
import com.databaseproject.backend.request.CreateLibraryOrderRequest;
import com.databaseproject.backend.request.CreateOrderRequest;
import com.databaseproject.backend.request.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.relational.core.sql.In;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@EnableConfigurationProperties(RsaKeyProperties.class)
@ConfigurationPropertiesScan
@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

	@Autowired
	IManagerRepository repo;

	@Bean
	CommandLineRunner commandLineRunner(IManagerRepository repo){
		return args -> {
			CreateLibraryOrderRequest request = new CreateLibraryOrderRequest();
			request.setISBN("1234567890123");
			request.setQuantity(20);

			System.out.println(repo.createLibraryOrder(request));
		};
	}

}
