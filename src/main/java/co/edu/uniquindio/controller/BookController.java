package co.edu.uniquindio.controller;

import co.edu.uniquindio.entity.Book;
import co.edu.uniquindio.service.BookService;
import co.edu.uniquindio.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private RatingService ratingService;


    @GetMapping("/search")
    public ResponseEntity<List<Book>> basicSearch(@RequestParam String q) {
        return ResponseEntity.ok(bookService.basicSearch(q));
    }


    @GetMapping("/advanced-search")
    public ResponseEntity<List<Book>> advancedSearch(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String isbn) {

        return ResponseEntity.ok(bookService.advancedSearch(title, author, isbn));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        try {
            Book savedBook = bookService.createBook(book);
            return ResponseEntity.status(201).body(savedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable String isbn, @RequestBody Book bookDetails) {
        try {
            Book updatedBook = bookService.updateBook(isbn, bookDetails);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        try {
            bookService.deleteBook(isbn);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable Integer authorId) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(authorId));
    }

    @GetMapping("/min-reviews/{minReviews}")
    public ResponseEntity<List<Book>> getBooksWithMinReviews(@PathVariable int minReviews) {
        return ResponseEntity.ok(bookService.getBooksWithMinReviews(minReviews));
    }
}
