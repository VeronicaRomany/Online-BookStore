package com.databaseproject.backend.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Year;
import java.util.List;

@Getter
@Setter
@ToString
public class BookInfoResponse {
    private String ISBN;
    private String title;
    private String publisher;
    private Integer pubYear; // Might cause a problem since its type is YEAR in db
    private String category;
    private Integer stock;
    private Integer threshold;
    private String imageURL;
    private Integer price;
    private List<String> authors;
}
