package com.majuran.movies.repository;

import com.majuran.movies.model.Slot;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

    @Modifying
    @Query(value = "ALTER TABLE movie_slot ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();
}