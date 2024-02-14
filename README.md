# API Documentation

This documentation provides details about the endpoints exposed by the Movie Controller in the application.

## Endpoints

### 1. Get All Movies

- **URL:** `/movies`
- **Method:** `GET`
- **Description:** Fetches a list of all movies filtered by optional parameters.
- **Parameters:**
    - `startDate` (Optional): Filter movies by start date.
    - `screenType` (Optional): Filter movies by screen type ( Standard or IMAX)
- **Response:** Returns a JSON array of `MovieDto` objects representing the movies.

#### Example Request:

```http
GET /movies?startDate=20240521&screenType=IMAX
```

#### Example Response:

```json
[
  {
    "id": 5,
    "name": "Batman Kapow! Bam! Sok! Blap!",
    "screen": "IMAX",
    "slots": [
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
```

### 2. Get Movie by ID

- **URL:** `/movies/{id}`
- **Method:** `GET`
- **Description:** Fetches a movie by its ID.
- **Path Variable:**
    - `id`: ID of the movie to retrieve.
- **Response:** Returns a `MovieDto` object representing the movie with the specified ID.

#### Example Request:

```http
GET /movies/1
```

#### Example Response:

```json
{
  "id": 4,
  "name": "Batman Kapow! Bam! Sok! Blap!",
  "screen": "xz",
  "slots": [
    {
      "id": 15,
      "date": "2024-05-29",
      "time": "1111"
    }
  ]
}
```

### 3. Update Movie

- **URL:** `/movies/{id}`
- **Method:** `PUT`
- **Description:** Updates a movie with the specified ID.
- **Path Variable:**
    - `id`: ID of the movie to update.
- **Request Body:** JSON object representing the updated movie (`MovieDto`).
- **Response:** No content (204) if the update is successful.

#### Example Request Body:
```json
[
  {
    "id": 4,
    "name": "Updated Movie",
    "screen": "IMAX",
    "slots": [
      {
        "id": 9,
        "date": "2024-05-29",
        "time": "2222"
      }
    ]
  }
]
```

#### Example Response:

```
Status: 204 No Content
```

---


## Instructions to Run Code

### Clone the Repository

```bash
git clone https://github.com/majurageerthan/movies-java.git
```

### Navigate to the Project Directory

```bash
cd movies-java
```

### Switch to main branch

```bash
git switch main
```

### Ensure Java 17 is Installed

Confirm that Java 17 or a compatible version is installed on your system.

### Ensure Maven 3.9.5 or Higher is Installed

Verify that Apache Maven 3.9.5 or a later version is installed on your machine.

### Build the Project

```bash
mvn clean install
```
This command will compile the Java source code, run tests, and package everything into an executable JAR file.

### Run the Application

```bash
java -jar target/movies-java-0.0.1-SNAPSHOT.jar
```

### Run Unit Tests

```bash
mvn test
```

### Access the Application:
After the application has started, you can access it using a web browser or an API client. By default, Spring Boot applications run on port 8080. Open your web browser and navigate to http://localhost:8080 to access the application.

### Stop the Application:
To stop the running Spring Boot application, you can press Ctrl + C in the terminal where the application is running.

---

These instructions are provided with the assumption that Git, Java, and Maven are already installed on the system. If adjustments are necessary based on the specific environment, kindly make the required modifications.
