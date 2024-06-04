package com.movies;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.movies.model.Movie;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SampleJsonRead {
    public static void main(String[] args) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("D:\\notes\\aws-example\\movies-service\\src\\main\\resources\\sample.json"));
            List movies = new ArrayList<Movie>();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
