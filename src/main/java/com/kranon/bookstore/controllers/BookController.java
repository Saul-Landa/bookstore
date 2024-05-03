package com.kranon.bookstore.controllers;

import com.kranon.bookstore.models.Book;
import com.kranon.bookstore.responses.Response;
import com.kranon.bookstore.services.IBookService;
import com.kranon.bookstore.utils.GlobalVariables;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/book")
public class BookController {

    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Object> find(
            @RequestParam(required = false) String bookName,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String year
    ) {
        try {
            return ResponseEntity.ok(new Response<>(bookService.find(bookName, author, year)));
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().body(new Response<>(exception.getMessage(), false));
        }
    }

    @GetMapping("/search/{search}")
    public ResponseEntity<Object> find(@PathVariable("search") String search) {
        return ResponseEntity.ok(new Response<>(bookService.find(search)));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Book book) {
        try {
            return ResponseEntity.ok(new Response<>(bookService.save(book)));
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().body(new Response<>(exception.getMessage(), false));
        }
    }

    @PutMapping
    public ResponseEntity<Object> update(
            @RequestBody Book book,
            @RequestParam(required = false) String bookName,
            @RequestParam(required = false) String author
    ) {
        try {
            return ResponseEntity.ok(new Response<>(bookService.update(book, bookName, author)));
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().body(new Response<>(exception.getMessage(), false));
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(
            @RequestParam(required = false) String bookName,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String year
    ) {
        try {
            bookService.delete(bookName, author, year);

            return ResponseEntity.ok(new Response<>(GlobalVariables.SUCCESS_DELETE_MESSAGE, true));
        }catch (Exception exception) {
            return ResponseEntity.internalServerError().body(new Response<>(exception.getMessage(), false));
        }
    }
}
