package com.majuran.movies.controller;

import com.majuran.movies.dto.MovieDto;
import com.majuran.movies.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieDto> getAllMovies(@RequestParam(required = false) String startDate,
                                       @RequestParam(required = false) String screenType) {
        return movieService.getAllMovies(startDate, screenType);
    }

    @GetMapping("/{id}")
    public MovieDto getMovieById(@PathVariable long id) {
        return movieService.getMovieById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateMovie(@PathVariable long id, @RequestBody MovieDto movie) {
        movieService.updateMovie(id, movie);
    }
}