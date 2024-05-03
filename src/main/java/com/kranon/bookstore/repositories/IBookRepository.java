package com.kranon.bookstore.repositories;

import com.kranon.bookstore.models.Book;

import java.util.List;

public interface IBookRepository {

    Book[] findAll();
    void save(List<Book> books);
}
