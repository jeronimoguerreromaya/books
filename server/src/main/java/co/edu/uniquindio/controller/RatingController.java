package co.edu.uniquindio.controller;


import co.edu.uniquindio.entity.Rating;
import co.edu.uniquindio.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@CrossOrigin(origins = "*")
public class RatingController {

    @Autowired
    private RatingService ratingService;


    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        try {
            Rating savedRating = ratingService.createRating(rating);
            return ResponseEntity.status(201).body(savedRating);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/book/{isbn}")
    public ResponseEntity<List<Rating>> getBookRatings(@PathVariable String isbn) {
        return ResponseEntity.ok(ratingService.getBookRatings(isbn));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getUserRatings(@PathVariable Integer userId) {
        return ResponseEntity.ok(ratingService.getUserRatings(userId));
    }

    @GetMapping("/user/{userId}/book/{isbn}")
    public ResponseEntity<Rating> getUserBookRating(
            @PathVariable Integer userId,
            @PathVariable String isbn) {

        return ratingService.getUserBookRating(userId, isbn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(
            @PathVariable Integer id,
            @RequestParam Integer score) {

        try {
            Rating updatedRating = ratingService.updateRating(id, score);
            return ResponseEntity.ok(updatedRating);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Integer id) {
        try {
            ratingService.deleteRating(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}