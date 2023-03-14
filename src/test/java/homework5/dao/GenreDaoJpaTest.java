package homework5.dao;

import homework5.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@JdbcTest
@Import(GenreDaoJdbs.class)
class GenreDaoJpaTest {

    private static final long FIRST_GENRE_ID = 1;
    private static final String FIRST_GENRE_FIRST_NAME = "Программирование";
    private static final int GENRE_COUNT = 2;

    @Autowired
    private GenreDao genreDao;

    @Test
    void shouldAddGenre() {
        genreDao.insert(new Genre("Проза"));
        assertThat(genreDao.findAll()).hasSize(GENRE_COUNT + 1);

    }

    @Test
    void shouldReturnGenreById() {
        Genre expectedGenre = new Genre(1, FIRST_GENRE_FIRST_NAME);
        Genre actualGenre = genreDao.findById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    void shouldFindAllGenre() {
        List<Genre> genres = genreDao.findAll();
        assertThat(genres).hasSize(GENRE_COUNT);
    }

    @Test
    void shouldCorrectDeleteAuthorById() {
        assertThatCode(() -> genreDao.findById(FIRST_GENRE_ID))
                .doesNotThrowAnyException();
        genreDao.deleteById(FIRST_GENRE_ID);
        assertThat(genreDao.findAll()).hasSize(GENRE_COUNT - 1);
    }
}