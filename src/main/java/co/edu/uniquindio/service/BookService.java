package co.edu.uniquindio.service;


import co.edu.uniquindio.entity.*;
import co.edu.uniquindio.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RatingService ratingService;

    // Basic Search
    public List<Book> basicSearch(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return bookRepository.findAll();
        }
        return bookRepository.searchByKeyword(searchTerm.trim());
    }

    // Advance Search
    public List<Book> advancedSearch(String title, String author, String isbn) {
        // Convertir empty strings a null para la query
        String titleParam = (title != null && !title.trim().isEmpty()) ? title.trim() : null;
        String authorParam = (author != null && !author.trim().isEmpty()) ? author.trim() : null;
        String isbnParam = (isbn != null && !isbn.trim().isEmpty()) ? isbn.trim() : null;

        return bookRepository.searchBooks(titleParam, authorParam, isbnParam);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Book createBook(Book book) {
        // Validar que el ISBN no exista
        if (bookRepository.findByIsbn(book.getIsbn()).isPresent()) {
            throw new RuntimeException("El ISBN ya existe");
        }
        return bookRepository.save(book);
    }

    public Book updateBook(String isbn, Book bookDetails) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());

        return bookRepository.save(book);
    }

    public void deleteBook(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        bookRepository.delete(book);
    }

    public List<Book> getBooksByAuthor(Integer authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    public List<Book> getBooksWithMinReviews(int minReviews) {
        return bookRepository.findBooksWithMinReviews(minReviews);
    }
}

