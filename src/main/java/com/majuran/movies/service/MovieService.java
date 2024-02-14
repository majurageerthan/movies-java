package com.majuran.movies.service;

import com.majuran.movies.dto.MovieDto;
import com.majuran.movies.dto.SlotDto;
import com.majuran.movies.model.Movie;
import com.majuran.movies.model.Slot;
import com.majuran.movies.repository.MovieRepository;
import com.majuran.movies.repository.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                return getMoviesFilteredByDateAndScreen(date, screenType);
            } else {
                return getMoviesFilteredByDate(date);
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

    private List<MovieDto> getMoviesFilteredByDateAndScreen(String date, String screen) {
        return getMoviesFilteredByDate(date).stream()
                .filter(movieDto -> movieDto.getScreen().equals(screen))
                .toList();
    }

    private List<MovieDto> getMoviesFilteredByDate(String date) {
        List<Slot> slots = slotRepository.findByDateGreaterThanOrEqual(getLocalDate(date));
        Map<Long, MovieDto> movieDtoMap = new HashMap<>();

        slots.forEach(slot -> {
            Long movieId = slot.getMovie().getId();
            MovieDto movieDto = movieDtoMap.computeIfAbsent(movieId, id -> MovieDto.builder()
                    .id(id)
                    .name(slot.getMovie().getName())
                    .screen(slot.getMovie().getScreen())
                    .slots(new ArrayList<>())
                    .build());
            movieDto.getSlots().add(new SlotDto(slot));
        });

        return new ArrayList<>(movieDtoMap.values());
    }
}