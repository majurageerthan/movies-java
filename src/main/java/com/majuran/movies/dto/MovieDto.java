package com.majuran.movies.dto;

import com.majuran.movies.model.Movie;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MovieDto {
    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.screen = movie.getScreen();
        this.slotList = movie.getBlogList().stream().map(SlotDto::new).toList();
    }

    private long id;
    private String name;
    private String screen;
    private List<SlotDto> slotList;
}
