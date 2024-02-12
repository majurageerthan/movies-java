A Spring Boot application that provides the following REST endpoints:

/movies - returns all movies. Implement caching when returning results.

The following optional parameters can be passed to narrow down the results.
'startDate': '<yyyyMMdd_format>' // starting date, i.e. if the date is today, the search should list movies from today onwards
'screenType': '<string>' // Standard or IMAX

/movies/{id} - get movie information.

/movies/{id} - update movie information.
