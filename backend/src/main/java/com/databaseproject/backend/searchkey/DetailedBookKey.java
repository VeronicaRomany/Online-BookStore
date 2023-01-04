package com.databaseproject.backend.searchkey;

import com.databaseproject.backend.searchkey.Key;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Year;

@Getter
@Setter
public class DetailedBookKey extends Key implements Serializable {
    private String ISBN;
    private String title;
    private String publisher;
    private Integer pubYear;
    private String price;
    private String category;
    private Integer stock;
    private String authorName;
}
