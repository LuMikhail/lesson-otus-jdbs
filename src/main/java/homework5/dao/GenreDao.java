package homework5.dao;

import homework5.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    Genre insert(Genre genre);

    Optional<Genre> findById(long id);

    List<Genre> findAll();

    void deleteById(long id);
}
