package com.databaseproject.backend.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Getter
@Setter
public class BookInfoResponse {
    private String ISBN;
    private String title;
    private String publisher;
    private Integer pubYear; // Might cause a problem since its type is YEAR in db
    private String category;
    private Integer stock;
    private Integer threshold;
}
