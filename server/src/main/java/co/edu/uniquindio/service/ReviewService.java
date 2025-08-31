package co.edu.uniquindio.service;

import co.edu.uniquindio.entity.Review;
import co.edu.uniquindio.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;


    public Review createReview(Review review) {

        userService.getUserById(review.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        bookService.getBookByIsbn(review.getBook().getIsbn())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        return reviewRepository.save(review);
    }

    public Review updateReviewStatus(Integer reviewId, String status) {
        if (!List.of("draft", "preview", "published").contains(status)) {
            throw new RuntimeException("Estado inválido. Use: draft, preview o published");
        }

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));


        return reviewRepository.save(review);
    }

    public List<Review> getBookReviews(String isbn) {
        return reviewRepository.findByBookIsbn(isbn);
    }



    public List<Review> getUserReviews(Integer userId) {
        return reviewRepository.findByUserId(userId);
    }


    public Review getReviewById(Integer id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));
    }

    public Review updateReview(Integer id, Review reviewDetails) {
        Review review = getReviewById(id);

        review.setContent(reviewDetails.getContent());


        return reviewRepository.save(review);
    }

    public void deleteReview(Integer id) {
        Review review = getReviewById(id);
        reviewRepository.delete(review);
    }

}