package co.edu.uniquindio.repository;

import co.edu.uniquindio.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {

    // Search by title
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Search by author
    @Query("SELECT b FROM Book b WHERE b.author.name LIKE %:authorName%")
    List<Book> findByAuthorNameContaining(@Param("authorName") String authorName);

    // Search by ISBN
    Optional<Book> findByIsbn(String isbn);


    @Query("SELECT b FROM Book b WHERE " +
            "(:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:author IS NULL OR LOWER(b.author.name) LIKE LOWER(CONCAT('%', :author, '%'))) AND " +
            "(:isbn IS NULL OR b.isbn = :isbn)")
    List<Book> searchBooks(@Param("title") String title,
                           @Param("author") String author,
                           @Param("isbn") String isbn);


    @Query("SELECT b FROM Book b WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(b.author.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> searchByKeyword(@Param("keyword") String keyword);


    List<Book> findByAuthorId(Integer authorId);


    @Query("SELECT b FROM Book b WHERE SIZE(b.reviews) > :minReviews")
    List<Book> findBooksWithMinReviews(@Param("minReviews") int minReviews);

}
