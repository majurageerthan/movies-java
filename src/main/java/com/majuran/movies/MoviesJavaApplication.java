package com.majuran.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MoviesJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesJavaApplication.class, args);
	}

}
