package com.majuran.movies.service;

import com.majuran.movies.dto.MovieDto;
import com.majuran.movies.model.Movie;
import com.majuran.movies.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.majuran.movies.util.DateUtil.getLocalDate;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    //    @Cacheable("movies")
    public List<MovieDto> getAllMovies(String date, String screenType) {
        List<Movie> movies;

        if (date != null) {
            if (screenType != null) {
                movies = movieRepository.findMoviesByScreenWithSlotsAfterDate(getLocalDate(date), screenType);
            } else {
                movies = movieRepository.findMoviesWithSlotsAfterDate(getLocalDate(date));
            }
        } else if (screenType != null) {
            movies = movieRepository.findByScreen(screenType);
        } else {
            movies = movieRepository.findAll();
        }

        return movies.stream().map(MovieDto::new).toList();
    }

    //    @Cacheable("movies")
    public MovieDto getMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.map(MovieDto::new).orElse(null);
    }

    //    @CacheEvict(value = "movies", key = "#id")
    public void updateMovie(Long id, MovieDto movieDto) {
        movieRepository.findById(id).ifPresent(existingMovie -> {
            existingMovie.setName(movieDto.getName());
            existingMovie.setScreen(movieDto.getScreen());
            movieRepository.save(existingMovie);
        });
    }

}