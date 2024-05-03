package com.kranon.bookstore.repositories.Implements;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kranon.bookstore.models.Book;
import com.kranon.bookstore.repositories.IBookRepository;
import com.kranon.bookstore.utils.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class BookRepository implements IBookRepository {

    private final ObjectMapper objectMapper;
    private final FileReader fileReader;
    private final Logger logger = LoggerFactory.getLogger(BookRepository.class);

    public BookRepository(ObjectMapper objectMapper, FileReader fileReader) {
        this.objectMapper = objectMapper;
        this.fileReader = fileReader;
    }

    @Override
    public Book[] findAll() {
        try {
            byte[] jsonData = fileReader.readFromFile();

            if (jsonData == null) return new Book[0];

            return objectMapper.readValue(jsonData, Book[].class);
        } catch (IOException exception) {
            logger.error(exception.getMessage());
            return null;
        }
    }

    @Override
    public void save(List<Book> books) {
        try {
            String jsonData = objectMapper.writeValueAsString(books);
            fileReader.writeToFile(jsonData);
        } catch (IOException exception) {
            logger.error(exception.getMessage());
        }
    }
}
