package co.edu.uniquindio.repository;

import co.edu.uniquindio.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {

    List<Rating> findByBookIsbn(String isbn);

    List<Rating> findByUserId(Integer userId);

    Optional<Rating> findByUserIdAndBookIsbn(Integer userId, String isbn);

}
