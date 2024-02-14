package com.majuran.movies.service;

import com.majuran.movies.dto.MovieDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> getAllMovies(String date, String screenType);

    MovieDto getMovieById(long id);

    void updateMovie(long id, MovieDto movieDto);

}
