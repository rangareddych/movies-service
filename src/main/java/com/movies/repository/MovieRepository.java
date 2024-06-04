package com.movies.repository;

import com.movies.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, String> {

    List<Movie> findByTitle(String title);

    List<Movie> findByYear(int year);

    @Query("{cast: ?0}")
    List<Movie> findByCast(String cast);

    @Query("{genre: ?0}")
    List<Movie> findByGenre(String genre);


}
