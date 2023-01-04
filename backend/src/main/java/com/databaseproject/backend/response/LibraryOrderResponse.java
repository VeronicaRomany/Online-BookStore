package com.databaseproject.backend.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LibraryOrderResponse {
    private int orderID;
    private int quantity;
    private String ISBN;
    private String title;
    private String publisher;
    private Integer pubYear;
    private String category;
    private Integer stock;
    private Integer threshold;
    private String imageURL;
}
