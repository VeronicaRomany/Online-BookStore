package com.databaseproject.backend.searchkey;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StringKey extends Key implements Serializable {
    String string;
}
