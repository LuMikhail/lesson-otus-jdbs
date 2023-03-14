package homework5.dao;

import homework5.domain.Author;

import java.util.List;

public interface AuthorDao {

    Author insert(Author author);

    Author findById(long id);

    List<Author> findAll();

    void deleteById(long id);
}
