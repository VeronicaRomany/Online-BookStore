package com.databaseproject.backend;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.databaseproject.backend.Reporting.Repository.ReportingRepository;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {

	@Autowired
	ReportingRepository repo;

	public static void main(String[] args) {



		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Map<String, Object> l = repo.getBestSellings();
		int x = 0;
		
	}

}
