package com.majuran.movies.repository;

import com.majuran.movies.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

    @Modifying
    @Query(value = "ALTER TABLE movie_slot ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

    List<Slot> findByDate(LocalDate date);

    @Query("SELECT e FROM Slot e WHERE e.date >= :date")
    List<Slot> findByDateGreaterThanOrEqual(LocalDate date);
}