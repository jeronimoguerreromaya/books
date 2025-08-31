package co.edu.uniquindio.repository;

import co.edu.uniquindio.entity.Author;
import co.edu.uniquindio.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

    // Buscar autor por nombre exacto
    Optional<Author> findByName(String name);

    // Buscar autores por nombre (contains, case insensitive)
    List<Author> findByNameContainingIgnoreCase(String name);

    // Buscar autor por email
    Optional<Author> findByEmail(String email);

}
