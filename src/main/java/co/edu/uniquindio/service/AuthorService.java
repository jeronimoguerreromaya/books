package co.edu.uniquindio.service;

import co.edu.uniquindio.entity.Author;
import co.edu.uniquindio.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Integer id) {
        return authorRepository.findById(id);
    }

    public Author createAuthor(Author author) {
        // Validar que el email no exista
        if (authorRepository.findByEmail(author.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }
        return authorRepository.save(author);
    }

    public Author updateAuthor(Integer id, Author authorDetails) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        author.setName(authorDetails.getName());
        author.setEmail(authorDetails.getEmail());

        return authorRepository.save(author);
    }

    public void deleteAuthor(Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        authorRepository.delete(author);
    }

    public List<Author> searchAuthorsByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name);
    }

    public Optional<Author> getAuthorByEmail(String email) {
        return authorRepository.findByEmail(email);
    }

}
