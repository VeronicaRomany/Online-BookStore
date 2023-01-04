package com.databaseproject.backend.request;

import com.databaseproject.backend.searchkey.Key;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class DetailedBookSearchRequest extends Key implements Serializable {
    private String ISBN;
    private String title;
    private String publisher;
    private Integer pubYear;
    private String price;
    private String category;
    private Integer stock;
    private String authorName;
    private String method;
    private String order;
    private Integer pageNumber;
    private Integer countInPage;
}
