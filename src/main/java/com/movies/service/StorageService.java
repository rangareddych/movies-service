package com.movies.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.movies.model.Movie;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public List<Movie> readFile(String key){
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, key));

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(object.getObjectContent()))) {
           return getAllMoviesFromFile(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the S3Object input stream when done
            try {
                object.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private List<Movie> getAllMoviesFromFile(BufferedReader reader){
        List<Movie> movies = new ArrayList();
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                Movie movie = new Movie();

                movie.setCast((JSONArray)jsonObject.get("cast"));
                movie.setThumbnail(jsonObject.get("thumbnail") != null ? jsonObject.get("thumbnail").toString() : null);
                movie.setExtract(jsonObject.get("extract") != null ? jsonObject.get("extract").toString() : null);
                movie.setYear(jsonObject.get("year") != null ? Integer.parseInt(jsonObject.get("year").toString()) : 0);
                movie.setGenres((JSONArray)jsonObject.get("genres"));
                movie.setThumbnailWidth(jsonObject.get("thumbnail_width") != null ? jsonObject.get("year").toString() : null);
                movie.setThumbnailHeight(jsonObject.get("thumbnail_height") != null ? jsonObject.get("thumbnail_height").toString() : null);
                movie.setHref(jsonObject.get("href") != null ? jsonObject.get("href").toString() : null);
                movie.setTitle(jsonObject.get("title") != null ? jsonObject.get("title").toString() : null);
                movies.add(movie);
            }
            System.out.println(movies);
            return movies;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
