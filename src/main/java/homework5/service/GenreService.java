package homework5.service;

import homework5.domain.Genre;

import java.util.List;

public interface GenreService {

    void insertGenre(String genre);

    List<Genre> findAllGenres();
}
