package co.edu.uniquindio.controller;

import co.edu.uniquindio.entity.Review;
import co.edu.uniquindio.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        try {
            Review savedReview = reviewService.createReview(review);
            return ResponseEntity.status(201).body(savedReview);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PatchMapping("/{id}/status")
    public ResponseEntity<Review> updateReviewStatus(
            @PathVariable Integer id,
            @RequestParam String status) {

        try {
            Review updatedReview = reviewService.updateReviewStatus(id, status);
            return ResponseEntity.ok(updatedReview);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // ✅ Obtener reseñas de un libro (historia de usuario)
    @GetMapping("/book/{isbn}")
    public ResponseEntity<List<Review>> getBookReviews(@PathVariable String isbn) {
        return ResponseEntity.ok(reviewService.getBookReviews(isbn));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getUserReviews(@PathVariable Integer userId) {
        return ResponseEntity.ok(reviewService.getUserReviews(userId));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Integer id) {
        try {
            Review review = reviewService.getReviewById(id);
            return ResponseEntity.ok(review);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Integer id, @RequestBody Review reviewDetails) {
        try {
            Review updatedReview = reviewService.updateReview(id, reviewDetails);
            return ResponseEntity.ok(updatedReview);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer id) {
        try {
            reviewService.deleteReview(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
