package com.databaseproject.backend.searchkey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString

public class IntKey extends Key implements Serializable {
    Integer integer;
}
