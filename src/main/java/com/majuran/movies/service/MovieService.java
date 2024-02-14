package com.majuran.movies.service;

import com.majuran.movies.dto.MovieDto;
import com.majuran.movies.model.Movie;
import com.majuran.movies.model.Slot;
import com.majuran.movies.repository.MovieRepository;
import com.majuran.movies.repository.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.majuran.movies.util.DateUtil.getLocalDate;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final SlotRepository slotRepository;


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
            removeExistingSlots(existingMovie);
            existingMovie.setName(movieDto.getName());
            existingMovie.setScreen(movieDto.getScreen());
            existingMovie.setSlots(getSlots(movieDto, existingMovie));
            movieRepository.save(existingMovie);
        });
    }

    private List<Slot> getSlots(MovieDto movieDto, Movie existingMovie) {
        return movieDto.getSlots().stream().map(slotDto -> {
            Slot slot = new Slot();
            slot.setMovie(existingMovie);
            slot.setDate(slotDto.getDate());
            slot.setTime(slotDto.getTime());
            return slot;
        }).collect(Collectors.toList());
    }

    private void removeExistingSlots(Movie movie) {
        List<Slot> existingSlots = movie.getSlots();
        existingSlots.forEach(slot -> slot.setMovie(null));
        slotRepository.deleteAll(existingSlots);
        movie.setSlots(new ArrayList<>());
        movieRepository.save(movie);
    }
}