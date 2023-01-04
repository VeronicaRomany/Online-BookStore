package com.databaseproject.backend.searchkey;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Year;

@Getter
@Setter
public class YearKey extends Key implements Serializable {
    Integer year;
}
