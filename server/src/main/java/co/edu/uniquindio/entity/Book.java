package co.edu.uniquindio.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="bookId")
    private String isbn;

    @Column(name="title", length = 45, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "authorId") // FK en la tabla libro
    private Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Rating> ratings = new ArrayList<>();

    public Book(){}

    public Book(String name, Author author) {
        this.title = name;
        this.author = author;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String name) {
        this.isbn = name;
    }

    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public List<Review> getReviews() {
        return reviews;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    public List<Rating> getRating() {
        return ratings;
    }
    public void setRating(List<Rating> rating) {
        this.ratings = rating;
    }

}
