package com.majuran.movies.repository;

import com.majuran.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Method to find movies by their name
    List<Movie> findByName(String name);
}