package co.edu.uniquindio.service;

import co.edu.uniquindio.entity.Rating;
import co.edu.uniquindio.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;


    public Rating createRating(Rating rating) {

        userService.getUserById(rating.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        bookService.getBookByIsbn(rating.getBook().getIsbn())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));


        if (rating.getScore() < 1 || rating.getScore() > 5) {
            throw new RuntimeException("La calificación debe estar entre 1 y 5");
        }
        return ratingRepository.save(rating);
    }



    public List<Rating> getBookRatings(String isbn) {
        return ratingRepository.findByBookIsbn(isbn);
    }

    public List<Rating> getUserRatings(Integer userId) {
        return ratingRepository.findByUserId(userId);
    }

    public Optional<Rating> getUserBookRating(Integer userId, String isbn) {
        return ratingRepository.findByUserIdAndBookIsbn(userId, isbn);
    }

    public Rating updateRating(Integer ratingId, Integer newScore) {
        if (newScore < 1 || newScore > 5) {
            throw new RuntimeException("La calificación debe estar entre 1 y 5");
        }

        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new RuntimeException("Calificación no encontrada"));

        rating.setScore(newScore);
        return ratingRepository.save(rating);
    }

    public void deleteRating(Integer id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calificación no encontrada"));
        ratingRepository.delete(rating);
    }





}
