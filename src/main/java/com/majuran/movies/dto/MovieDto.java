package com.majuran.movies.dto;

import com.majuran.movies.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MovieDto {
    private long id;
    private String name;
    private String screen;
    private List<SlotDto> slots;

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.screen = movie.getScreen();
        this.slots = movie.getSlots().stream().map(SlotDto::new).toList();
    }
}
