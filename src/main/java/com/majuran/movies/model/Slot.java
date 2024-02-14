package com.majuran.movies.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "movie_slot")
public class Slot {
    public Slot(long id, LocalDate date, String time) {
        this.id = id;
        this.date = date;
        this.time = time;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date", columnDefinition = "DATE")
    private LocalDate date;

    @Column(name = "time")
    private String time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private Movie movie;

}

