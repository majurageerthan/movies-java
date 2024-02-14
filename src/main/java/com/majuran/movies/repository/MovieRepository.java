package com.majuran.movies.repository;

import com.majuran.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByName(String name);

    List<Movie> findByScreen(String screen);

    @Query("SELECT DISTINCT m FROM Movie m INNER JOIN FETCH m.slots s WHERE s.date >= :date")
    List<Movie> findMoviesWithSlotsAfterDate(@Param("date") LocalDate date);

    @Query("SELECT DISTINCT m FROM Movie m INNER JOIN FETCH m.slots s WHERE s.date >= :date AND m.screen= :screen")
    List<Movie> findMoviesByScreenWithSlotsAfterDate(@Param("date") LocalDate date, @Param("screen") String screen);

    @Modifying
    @Query(value = "ALTER TABLE movie ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();
}