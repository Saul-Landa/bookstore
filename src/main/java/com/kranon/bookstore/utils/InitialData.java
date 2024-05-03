package com.kranon.bookstore.utils;

import com.kranon.bookstore.models.Book;
import com.kranon.bookstore.services.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class InitialData implements CommandLineRunner {

    private final IBookService bookService;
    private final Logger logger = LoggerFactory.getLogger(InitialData.class);

    public InitialData(IBookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) {
        List<Book> books = Arrays.asList(
                new Book("Divina comedia", "Dante Alighieri", "1320"),
                new Book("Hamlet", "William Shakespeare", "1623"),
                new Book("El resplandor", "Stephen King", "1977"),
                new Book("Cementerio de animales", "Stephen King", "1983"),
                new Book("Frankenstein", "Mary Shelley", "1818"),
                new Book("El padrino", "Mario Puzo", "1969")
        );

        books.forEach( book -> {
            if (bookService.findByName(book.getBookName()) == null) {
                try {
                    bookService.save(book);
                } catch (Exception exception) {
                    logger.error(exception.getMessage());
                }
            }
        });
    }
}

