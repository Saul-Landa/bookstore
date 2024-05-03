package com.kranon.bookstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    private String bookName;
    private String author;
    private String releaseDate;
}
