package com.databaseproject.backend;

import com.databaseproject.backend.config.RsaKeyProperties;

import com.databaseproject.backend.repository.classes.UserRepository;
import com.databaseproject.backend.repository.interfaces.IManagerRepository;
import com.databaseproject.backend.repository.interfaces.ISearchingRepository;
import com.databaseproject.backend.request.*;
import com.databaseproject.backend.searchkey.DetailedBookKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.relational.core.sql.In;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.time.Year;
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
	ISearchingRepository repo;

	@Bean
	CommandLineRunner commandLineRunner(ISearchingRepository repo){
		return args -> {
			SearchByRequest request = new SearchByRequest();
			request.setPageNumber(1);
			request.setCountInPage(10);
			DetailedBookKey key = new DetailedBookKey();
			key.setAuthorName(null);
			key.setISBN("123");
			key.setCategory(null);
			key.setPublisher(null);
			key.setPubYear(null);
			key.setTitle(null);
			key.setPrice(null);
			key.setStock(null);
			request.setKey(key);
//			request.setISBN("9234567890123");
//
//			request.setAuthors(new String[] {"First Author", "Second Author"});
//			request.setCategory("Science");
//			request.setPrice(30);
//			request.setPublisher("PName");
//			request.setThreshold(4);
//			request.setTitle("A Title");
//			request.setPubYear(2022);
//			request.setImageURL("URL");
			System.out.println(repo.searchBooks(request));
		};
	}

}
