package homework5.dao;

import homework5.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    Author insert(Author author);

    Optional<Author> findById(long id);

    List<Author> findAll();

    void deleteById(long id);
}
