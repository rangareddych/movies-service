package com.movies.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "movies")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movie {
    @Id
    private String movieId;
    private String title;
    private int year;
    private List<String> cast;
    private List<String> genres;
    private String href;
    private String extract;
    private String thumbnail;
    private String thumbnailWidth;
    private String thumbnailHeight;
}
