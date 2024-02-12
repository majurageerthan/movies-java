package com.majuran.movies.repository;

import com.majuran.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByName(String name);

    List<Movie> findByScreen(String screen);

    List<Movie> findBySlotList_Date(LocalDate slotDate);

    List<Movie> findByScreenAndSlotListDateGreaterThanEqual(String screen, LocalDate slotDate);

    @Query("SELECT DISTINCT m FROM Movie m INNER JOIN m.slotList s WHERE s.date >= :date")
    List<Movie> findMoviesBySlotDateAfterOrEqualTo(LocalDate date);

}