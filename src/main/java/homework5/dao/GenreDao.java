package homework5.dao;

import homework5.domain.Genre;

import java.util.List;

public interface GenreDao {

    Genre insert(Genre genre);

    Genre findById(long id);

    List<Genre> findAll();

    void deleteById(long id);
}
