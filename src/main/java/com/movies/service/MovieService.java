package com.movies.service;

import com.movies.model.Movie;
import com.movies.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private StorageService storageService;

    public Movie saveMovie(Movie movie){
        movie.setMovieId(UUID.randomUUID().toString());
        log.info("Movie title : {} assigned ID as {}", movie.getTitle(), movie.getMovieId());
        return repository.save(movie);
    }

    public List<Movie> getMoviesByTitle(String title){
        log.info("Calling DAO for fetching Movies with title {}", title);
        return repository.findByTitle(title);
    }

    public List<Movie> getMoviesByYear(int year){
        log.info("Calling DAO for fetching Movies in the year {}", year);
        return repository.findByYear(year);
    }

    public List<Movie> getMoviesByCast(String cast){
        log.info("Calling DAO for fetching Movies casting {}", cast);
        return repository.findByCast(cast);
    }

    public List<Movie> getMoviesByGenre(String genre){
        log.info("Calling DAO for fetching Movies for genre {}", genre);
        return repository.findByGenre(genre);
    }

    public Movie updateMovie(Movie movie){

        //Get existing movie document from database
        //Populate  new values from movie object from request to existing movie in database
        log.info("Calling DAO for fetching Movies with movie ID : {}", movie.getMovieId());
        Optional<Movie> existingOptional = repository.findById(movie.getMovieId());

        if(existingOptional.isPresent()){
            log.info("Movie found in the database with Movie ID : {}", movie.getMovieId());
            Movie existing = existingOptional.get();
            try {
                BeanUtils.copyProperties(movie, existing);
            }catch (Exception e){
                log.error("Exception occurred while copying values from Movie request object to Movie database object");
            }
            return repository.save(existing);
        }
        log.info("Movie not found in the database with movie ID : {}", movie.getMovieId());
        return null;
    }

    public int saveAllMoviesFromFile(String fileName) {
        List<Movie> movies = storageService.readFile(fileName);
        List<Movie> savedMovies = repository.saveAll(movies);
        return savedMovies.size();
    }
}
