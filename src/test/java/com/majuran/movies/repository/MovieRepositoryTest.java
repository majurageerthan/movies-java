package com.majuran.movies.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.majuran.movies.dto.MovieDto;
import com.majuran.movies.model.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static com.majuran.movies.util.DateUtil.getLocalDate;
import static com.majuran.movies.util.MockData.DATE_FILTER_EQUAL_OR_ABOVE_5_21_DATA_JSON;
import static com.majuran.movies.util.MockData.INITIAL_DATA_JSON;
import static com.majuran.movies.util.MockFilterByScreenData.IMAX_DATA_JSON;
import static com.majuran.movies.util.MockFilterByScreenData.STANDARD_DATA_JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class MovieRepositoryTest {
    static ObjectMapper objectMapper;
    static List<Movie> initialMoviesData;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SlotRepository slotRepository;

    @BeforeAll
    static void beforeAll() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        initialMoviesData = getMoviesFromJson(INITIAL_DATA_JSON);
    }

    private static List<Movie> getMoviesFromJson(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static void assertEqualsMovies(List<Movie> expected, List<Movie> actual) {
        assertEquals(expected.stream().map(MovieDto::new).toList(), actual.stream().map(MovieDto::new).toList());
    }


    @BeforeEach
    void setUp() {
        movieRepository.saveAll(initialMoviesData);
    }

    @AfterEach
    void tearDown() {
        slotRepository.deleteAll();
        movieRepository.deleteAll();
        slotRepository.resetAutoIncrement();
        movieRepository.resetAutoIncrement();
    }


    @Test
    void testSaveAndFindMovie() {
        var actual = movieRepository.findAll();
        assertEqualsMovies(initialMoviesData, actual);
    }

    @Nested
    class FilterMoviesByScreenTests {
        @Test
        void filterByScreenStandard() {
            var actual = movieRepository.findByScreen("Standard");
            assertEqualsMovies(getMoviesFromJson(STANDARD_DATA_JSON), actual);
        }

        @Test
        void filterByImax() {
            var actual = movieRepository.findByScreen("IMAX");
            assertEqualsMovies(getMoviesFromJson(IMAX_DATA_JSON), actual);
        }
    }

    @Nested
    class FilterMoviesTests {
        @Test
        void filterByDateEqualOrAbove521() {
            var actual = movieRepository.findMoviesWithSlotsAfterDate(getLocalDate("20240521"));
            System.out.println(actual);
            assertEquals(getMoviesFromJson(DATE_FILTER_EQUAL_OR_ABOVE_5_21_DATA_JSON), actual);
        }
    }

}