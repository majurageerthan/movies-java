package com.majuran.movies.dto;

import com.majuran.movies.model.Slot;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SlotDto {
    private long id;
    private LocalDate date;
    private String time;

    public SlotDto(Slot slot) {
        this.id = slot.getId();
        this.date = slot.getDate();
        this.time = slot.getTime();
    }
}
