package com.moger.automation.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
