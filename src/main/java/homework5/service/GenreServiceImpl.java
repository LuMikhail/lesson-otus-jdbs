package homework5.service;

import homework5.dao.GenreDao;
import homework5.domain.Genre;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    @Transactional
    public void insertGenre(String genre) {
        genreDao.insert(new Genre(genre));
    }

    @Override
    public List<Genre> findAllGenres() {
        return genreDao.findAll();
    }
}

