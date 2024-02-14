package com.majuran.movies.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.majuran.movies.model.Movie;

import java.io.IOException;
import java.util.List;

public class MockData {

    public static final String INITIAL_DATA_JSON = """
            [
                {
                    "id": 1,
                    "name": "Captain America: Summer Soldier",
                    "screen": "Standard",
                    "slots": [
                        {
                            "id": 1,
                            "date": "2024-05-20",
                            "time": "1345"
                        },
                        {
                            "id": 2,
                            "date": "2024-05-20",
                            "time": "1600"
                        },
                        {
                            "id": 3,
                            "date": "2024-06-01",
                            "time": "2000"
                        }
                    ]
                },
                {
                    "id": 2,
                    "name": "Captain America: Summer Soldier",
                    "screen": "IMAX",
                    "slots": [
                        {
                            "id": 4,
                            "date": "2024-05-20",
                            "time": "1300"
                        }
                    ]
                },
                {
                    "id": 3,
                    "name": "Mission Impossible: Infinite Possibilities",
                    "screen": "Standard",
                    "slots": [
                        {
                            "id": 5,
                            "date": "2024-05-20",
                            "time": "1000"
                        },
                        {
                            "id": 6,
                            "date": "2024-05-20",
                            "time": "1300"
                        },
                        {
                            "id": 7,
                            "date": "2024-05-21",
                            "time": "1000"
                        },
                        {
                            "id": 8,
                            "date": "2024-05-21",
                            "time": "1300"
                        }
                    ]
                },
                {
                    "id": 4,
                    "name": "Batman Kapow! Bam! Sok! Blap!",
                    "screen": "Standard",
                    "slots": [
                        {
                            "id": 9,
                            "date": "2024-05-20",
                            "time": "1000"
                        },
                        {
                            "id": 10,
                            "date": "2024-05-21",
                            "time": "1300"
                        }
                    ]
                },
                {
                    "id": 5,
                    "name": "Batman Kapow! Bam! Sok! Blap!",
                    "screen": "IMAX",
                    "slots": [
                        {
                            "id": 11,
                            "date": "2024-05-20",
                            "time": "1300"
                        },
                        {
                            "id": 12,
                            "date": "2024-05-21",
                            "time": "1000"
                        },
                        {
                            "id": 13,
                            "date": "2024-05-22",
                            "time": "1400"
                        }
                    ]
                }
            ]
            """;

    public final static String DATE_FILTER_EQUAL_OR_ABOVE_5_21_DATA_JSON = """
            [
                {
                    "name": "Captain America: Summer Soldier",
                    "slots": [
                       {
                            "date": "2024-06-01",
                            "time": "2000"
                        }
                    ],
                    "screen": "Standard"
                },
                {
                    "name": "Mission Impossible: Infinite Possibilities",
                    "slots": [
                        {
                            "date": "2024-05-21",
                            "time": "1000"
                        },
                        {
                            "date": "2024-05-21",
                            "time": "1300"
                        }
                    ],
                    "screen": "Standard"
                },
                {
                    "name": "Batman Kapow! Bam! Sok! Blap!",
                    "slots": [
                        {
                            "date": "2024-05-21",
                            "time": "1300"
                        }
                    ],
                    "screen": "Standard"
                },
                {
                    "name": "Batman Kapow! Bam! Sok! Blap!",
                    "slots": [
                        {
                            "date": "2024-05-21",
                            "time": "1000"
                        },
                        {
                            "date": "2024-05-22",
                            "time": "1400"
                        }
                    ],
                    "screen": "IMAX"
                }
            ]
            """;

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            List<Movie> movies = objectMapper.readValue(INITIAL_DATA_JSON, new TypeReference<>() {
            });

            // Print the movies for verification
            for (Movie movie : movies) {
                System.out.println("Name: " + movie.getName());
                System.out.println("Screen: " + movie.getScreen());
                System.out.printf(movie.getSlots().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
