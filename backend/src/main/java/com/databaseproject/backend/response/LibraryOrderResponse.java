package com.databaseproject.backend.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LibraryOrderResponse {
    int orderID;
    String ISBN;
    int quantity;
    String title;
    int stock;
    String imageURL;
}
