package co.edu.uniquindio.entity;

import jakarta.persistence.*;


import java.util.Date;
import java.util.List;

@Entity
@Table(name="ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ratingId")
    private int id;

    @Column(name="score", nullable = false)
    private int score;

    @Column(name="date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;



    public Rating(){}

    public Rating(int score, Date date) {
        this.score = score;
        this.date = date;

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public Date getFecha() {
        return date;
    }
    public void setFecha(Date date) {
        this.date = date;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public User getUser() {
        return user;
    }
    public Book getBook() {
        return book;
    }
}
