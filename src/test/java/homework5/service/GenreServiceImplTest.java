package homework5.service;

import homework5.dao.GenreDao;
import homework5.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.verify;

@SpringBootTest
class GenreServiceImplTest {

    @Configuration
    @Import(GenreServiceImpl.class)
    static class NestedTestConfiguration {
    }

    @MockBean
    private GenreDao genreDao;

    @Autowired
    private GenreServiceImpl genreService;

    @Test
    void calledCorrectlyInsertGenre() {
        Genre genre = new Genre("Проза");
        genreService.insertGenre("Проза");
        verify(genreDao).insert(genre);
    }

    @Test
    void calledCorrectlyFindAllGenres() {
        genreService.findAllGenres();
        verify(genreDao).findAll();
    }
}
