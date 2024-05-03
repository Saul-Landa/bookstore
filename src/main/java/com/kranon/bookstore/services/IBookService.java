package com.kranon.bookstore.services;

import com.kranon.bookstore.models.Book;

import java.util.List;

public interface IBookService {
    List<Book> find(String search);
    List<Book> find(String name, String author, String year) throws Exception;
    Book findByName(String name);
    Book save(Book book) throws Exception;
    List<Book>  update(Book book, String name, String author) throws Exception;
    void delete(String name, String author, String year) throws Exception;
}
