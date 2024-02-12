package com.majuran.movies.service;

import com.majuran.movies.dto.MovieDto;
import com.majuran.movies.model.Movie;
import com.majuran.movies.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Cacheable("movies")
    public List<MovieDto> getAllMovies(String startDate, String screenType) {
        // Implement logic to filter movies based on startDate and screenType
        // You can use the methods defined in the MovieRepository
        return movieRepository.findAll().stream()
                .map(MovieDto::new).toList();
    }

    @Cacheable("movie")
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public void updateMovie(Long id, Movie updatedMovie) {
        movieRepository.findById(id).ifPresent(existingMovie -> movieRepository.save(updatedMovie));
    }
}