package com.majuran.movies.repository;

import com.majuran.movies.model.Movie;
import jakarta.transaction.Transactional;
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

    List<Movie> findBySlots_Date(LocalDate slotDate);

    List<Movie> findByScreenAndSlotsDateGreaterThanEqual(String screen, LocalDate slotDate);

    @Query("SELECT DISTINCT m FROM Movie m INNER JOIN m.slots s WHERE s.date >= :date")
    List<Movie> findMoviesBySlotDateAfterOrEqualTo(LocalDate date);

    //    @Query("SELECT m FROM Movie m INNER JOIN Slot s ON m.id = s.movie.id AND s.date >= :date")
    @Query("SELECT DISTINCT m FROM Movie m JOIN FETCH m.slots s WHERE s.date > :date")
    List<Movie> findMoviesWithSlotsAfterDate(@Param("date") LocalDate date);

    @Modifying
    @Query(value = "ALTER TABLE movie ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();
}