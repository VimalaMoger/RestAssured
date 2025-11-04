package com.moger.automation.pojos;

import lombok.Data;

import java.util.List;

@Data
public class Book {

    private long id;

    private String title;

    private String image_url;

    private int publicationYear;

    private List<Publisher> publishers;

    private List<Author> authors;

    private BookGenre genre;


    // Enum declaration
    public enum BookGenre {
        TECHNICAL,
        PHILOSOPHY
    }

    private double amazonRating;
}
