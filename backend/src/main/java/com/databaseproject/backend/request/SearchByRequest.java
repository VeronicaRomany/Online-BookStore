package com.databaseproject.backend.request;

import com.databaseproject.backend.searchkey.Key;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchByRequest {
    Key key;
    String method;
    String order;
    Integer pageNumber;
    Integer countInPage;
}
