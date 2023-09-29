package com.movies;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MoviesApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoviesApplication.class, args);
    }

    @Bean
    public CommandLineRunner initializeData(MovieRepository movieRepository) {
        return args -> {
            Movie movie1 = new Movie("Movie 1", "Description of Movie 1", 120);
            Movie movie2 = new Movie("Movie 2", "Description of Movie 2", 90);

            movieRepository.save(movie1);
            movieRepository.save(movie2);

            System.out.println("Initial movie data has been loaded.");
        };
    }
}
