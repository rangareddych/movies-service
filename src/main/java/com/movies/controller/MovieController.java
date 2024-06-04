package com.movies.controller;

import com.movies.model.Movie;
import com.movies.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Inserts Movie into database")
    public Movie saveMovie(@RequestBody Movie movie){
        log.info("Received movie : "+movie.getTitle());
        if(StringUtils.hasText(movie.getMovieId())){
            log.error("This movie with movie ID : {} already existing in database", movie.getMovieId());
            return null;
        }
        return service.saveMovie(movie);
    }

    @GetMapping("file/{fileName}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Inserts all the movies from the file passed in the request")
    public int saveAllMoviesFromFile(@PathVariable String fileName){
        return service.saveAllMoviesFromFile(fileName);
    }

    @GetMapping("/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Gives Movies by Title from database")
    public List<Movie> getMovieByTitle(@PathVariable String title){
        log.info("Fetching Movies By title");
        return service.getMoviesByTitle(title);
    }

    @GetMapping("/year/{year}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Gives Movies by Year from database")
    public List<Movie> getMovieByYear(@PathVariable int year){
        log.info("Fetching Movies By year");
        return service.getMoviesByYear(year);
    }

    @GetMapping("/cast/{cast}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Gives Movies by Cast from database")
    public List<Movie> getMoviesByCast(@PathVariable String cast){
        log.info("Fetching Movies By cast");
        return service.getMoviesByCast(cast);
    }

    @GetMapping("/genre/{genre}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Gives Movies by Genre from database")
    public List<Movie> getMoviesByGenre(@PathVariable String genre){
        log.info("Fetching Movies By genre");
        return service.getMoviesByGenre(genre);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Updates Movie in database")
    public Movie updateMovie(@RequestBody Movie movie){
        log.info("Updating Movie for the movie id : "+movie.getMovieId());
        return service.updateMovie(movie);
    }
}
