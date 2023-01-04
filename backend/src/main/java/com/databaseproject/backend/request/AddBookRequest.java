package com.databaseproject.backend.request;

import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Getter
@Setter
public class AddBookRequest {
    private String ISBN;
    private String title;
    private String publisher;
    private Integer pubYear;
    private Integer price;
    private String category;
    private Integer stock;
    private Integer threshold;
    private String imageURL;
    private String[] authors;
}
