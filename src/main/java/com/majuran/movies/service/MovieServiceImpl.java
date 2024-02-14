package com.majuran.movies.service;

import com.majuran.movies.dto.MovieDto;
import com.majuran.movies.exception.NotFoundException;
import com.majuran.movies.exception.ServiceException;
import com.majuran.movies.model.Movie;
import com.majuran.movies.model.Slot;
import com.majuran.movies.repository.MovieRepository;
import com.majuran.movies.repository.SlotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.majuran.movies.util.DateUtil.getLocalDate;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final SlotRepository slotRepository;

    @Override
    @Cacheable(value = "movies")
    public List<MovieDto> getAllMovies(String date, String screenType) {
        try {
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
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch movies", e);
        }
    }

    @Override
    @Cacheable(value = "movies", key = "#id")
    public MovieDto getMovieById(long id) {
        try {
            Optional<Movie> movie = movieRepository.findById(id);
            return movie.map(MovieDto::new).orElseThrow(() -> new NotFoundException("Movie not found with id: " + id));
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch movie by id: " + id, e);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "movies", allEntries = true)
    public void updateMovie(long id, MovieDto movieDto) {
        try {
            movieRepository.findById(id).ifPresent(existingMovie -> {
                removeExistingSlots(existingMovie);
                existingMovie.setName(movieDto.getName());
                existingMovie.setScreen(movieDto.getScreen());
                existingMovie.setSlots(getSlots(movieDto, existingMovie));
                movieRepository.save(existingMovie);
            });
        } catch (Exception e) {
            throw new ServiceException("Failed to update movie with id: " + id, e);
        }
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
        try {
            List<Slot> existingSlots = movie.getSlots();
            existingSlots.forEach(slot -> slot.setMovie(null));
            slotRepository.deleteAll(existingSlots);
            movie.setSlots(new ArrayList<>());
            movieRepository.save(movie);
        } catch (Exception e) {
            throw new ServiceException("Failed to remove existing slots for movie: " + movie.getId(), e);
        }
    }
}