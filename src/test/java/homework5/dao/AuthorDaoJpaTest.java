package homework5.dao;

import homework5.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@JdbcTest
@Import(AuthorDaoJdbs.class)
class AuthorDaoJpaTest {

    private static final long FIRST_AUTHOR_ID = 1;
    private static final String FIRST_AUTHOR_FIRST_NAME = "Крейг Уоллс";
    private static final int AUTHOR_COUNT = 4;

    @Autowired
    private AuthorDao authorDao;

    @Test
    void shouldAddAuthor() {
        authorDao.insert(new Author("Джош Блох"));
        assertThat(authorDao.findAll()).hasSize(AUTHOR_COUNT + 1);
    }

    @Test
    void shouldReturnAuthorById() {
        Author expectedAuthor = new Author(1, FIRST_AUTHOR_FIRST_NAME);
        Author actualAuthor = authorDao.findById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void shouldFindAllAuthor() {
        List<Author> author = authorDao.findAll();
        assertThat(author).hasSize(AUTHOR_COUNT);
    }

    @Test
    void shouldCorrectDeleteAuthorById() {
        assertThatCode(() -> authorDao.findById(FIRST_AUTHOR_ID))
                .doesNotThrowAnyException();

        authorDao.deleteById(FIRST_AUTHOR_ID);
        assertThat(authorDao.findAll()).hasSize(AUTHOR_COUNT - 1);
    }
}