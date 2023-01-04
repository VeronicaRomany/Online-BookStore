package com.databaseproject.backend.request;

import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Getter
@Setter
public class ModifyBookRequest {
    private String oldISBN;
    private String newISBN;
    private String newTitle;
    private String newPublisher;
    private Integer newPubYear;
    private Integer newPrice;
    private String newCategory;
    private Integer newStock;
    private Integer newThreshold;
    private String newImageURL;
    private String[] newAuthors;
}
