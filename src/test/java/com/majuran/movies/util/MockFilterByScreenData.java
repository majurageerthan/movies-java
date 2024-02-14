package com.majuran.movies.util;

public class MockFilterByScreenData {

    public static final String IMAX_DATA_JSON = """
            [
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

    public static final String STANDARD_DATA_JSON = """
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
                }
            ]
            """;
}