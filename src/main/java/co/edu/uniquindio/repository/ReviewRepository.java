package co.edu.uniquindio.repository;

import co.edu.uniquindio.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {


    List<Review> findByBookIsbn(String isbn);

    List<Review> findByUserId(Integer userId);

}
