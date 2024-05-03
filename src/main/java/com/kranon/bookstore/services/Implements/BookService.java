package com.kranon.bookstore.services.Implements;

import com.kranon.bookstore.models.Book;
import com.kranon.bookstore.repositories.IBookRepository;
import com.kranon.bookstore.services.IBookService;
import com.kranon.bookstore.utils.GlobalVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService implements IBookService {

    private final IBookRepository bookRepository;
    private final Logger logger = LoggerFactory.getLogger(BookService.class);

    public BookService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        Book[] booksArray = bookRepository.findAll();
        return Arrays.asList(booksArray);
    }

    @Override
    public Book findByName(String name) {
        List<Book> books = findAll();
        Optional<Book> book = books.stream().filter(x -> x.getBookName().equals(name)).findFirst();

        return book.orElse(null);
    }

    public List<Book> findAllByAuthor(String author) {
        List<Book> books = findAll();
        books = books.stream().filter(x -> x.getAuthor().equals(author)).toList();

        return books;
    }

    public List<Book> findAllByYear(String year) {
        List<Book> books = findAll();
        books = books.stream().filter(x -> x.getReleaseDate().equals(year)).toList();

        return books;
    }

    @Override
    public List<Book> find(String search) {
        List<Book> books = findAll();
        books = books.stream().filter( x ->
                x.getBookName().equals(search)
                || x.getAuthor().equals(search)
                || x.getReleaseDate().equals(search) ).toList();

        return  books;
    }

    @Override
    public List<Book> find(String name, String author, String year) throws Exception {
        if (name != null) {
            return Collections.singletonList(findByName(name));
        }
        if (author != null) {
            return findAllByAuthor(author);
        }
        if (year != null) {
            return findAllByYear(year);
        }

        throw new Exception(GlobalVariables.NO_PARAMETERS_ERROR);
    }

    @Override
    public Book save(Book book) throws Exception {
        if (book.getBookName() == null || book.getBookName().trim().isEmpty()) {
            throw new Exception(GlobalVariables.NAME_REQUIRED_ERROR);
        }

        if (findByName(book.getBookName()) != null) {
            throw new Exception(GlobalVariables.BOOK_EXISTS_ERROR);
        }

        List<Book> books = new ArrayList<>(findAll());
        books.add(book);
        bookRepository.save(books);
        return book;
    }

    @Override
    public List<Book> update(Book book, String name, String author) throws Exception {
        if (book.getBookName() == null || book.getBookName().trim().isEmpty()) {
            throw new Exception(GlobalVariables.NAME_REQUIRED_ERROR);
        }

        if (name != null) {
            return Collections.singletonList(updateByName(book, name));
        }
        if (author != null) {
            return updateByAuthor(book, author);
        }
        throw new Exception(GlobalVariables.NO_PARAMETERS_ERROR);
    }

    public Book update(Book book, Book bookToUpdate) {
        LinkedList <Book> books = new LinkedList<>(findAll());
        int index = books.indexOf(book);

        books.set(index, bookToUpdate);
        bookRepository.save(books);
        return bookToUpdate;
    }

    public Book updateByName(Book book, String name) throws Exception {
        Book bookDB = findByName(name);
        if (bookDB == null) {
            throw new Exception(GlobalVariables.NOT_FOUND_ERROR);
        }

        return update(bookDB, book);
    }

    public List<Book> updateByAuthor(Book book, String author) {
        List<Book> books = findAllByAuthor(author);
        books.forEach(bookDB -> {
            update(bookDB, book);
        });

        return books;
    }

    @Override
    public void delete(String name, String author, String year) throws Exception {
        if (name != null) {
            deleteByName(name);
            return;
        }
        if (author != null) {
            deleteByAuthor(author);
            return;
        }
        if (year != null) {
            deleteByYear(year);
            return;
        }
        throw new Exception(GlobalVariables.NO_PARAMETERS_ERROR);
    }

    public void deleteByName(String name) {
        Book book = findByName(name);
        if (book == null) return;

        delete(book);
    }

    public void deleteByAuthor(String author) {
        List<Book> books = findAllByAuthor(author);
        if (books.isEmpty()) return;

        books.forEach(this::delete);
    }

    public void deleteByYear(String year) {
        List<Book> books = findAllByYear(year);
        if (books.isEmpty()) return;

        books.forEach(this::delete);
    }

    public void delete(Book book) {
        try {
            LinkedList <Book> books = new LinkedList<>(findAll());
            books.remove(book);
            bookRepository.save(books);

        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
    }
}
