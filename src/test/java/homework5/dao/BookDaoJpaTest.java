package homework5.dao;

import homework5.domain.Author;
import homework5.domain.Book;
import homework5.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DataJpaTest
@Import(BookDaoJpa.class)
class BookDaoJpaTest {

    private static final long FIRST_BOOK_ID = 1;
    private static final String FIRST_BOOK_FIRST_TITLE = "Над осевшими могилами";
    private static final int BOOK_COUNT = 4;
    private static final long GENRE_ID = 1;
    private static final long AUTHOR_ID = 4;

    @Autowired
    private TestEntityManager em;

    @Autowired
    private BookDao bookDao;

    @Test
    void shouldAddBook() {
       bookDao.insert(new Book(5L, "Скриба", new Author(3L), new Genre(2L), 3, null));
        assertThat(bookDao.findAll()).hasSize(BOOK_COUNT + 1);
        List<Book> books = bookDao.findAll();
    }

    @Test
    void shouldReturnBookById() {
        Optional<Book> book = bookDao.findById(FIRST_BOOK_ID);
        assertThat(book).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("title", FIRST_BOOK_FIRST_TITLE);
    }

    @Test
    void shouldFindAllBook() {
        List<Book> author = bookDao.findAll();
        assertThat(author).hasSize(BOOK_COUNT);
    }

    @Test
    void shouldCorrectDeleteBookById() {
        assertThatCode(() -> bookDao.findById(FIRST_BOOK_ID))
                .doesNotThrowAnyException();
        bookDao.deleteById(FIRST_BOOK_ID);
        assertThat(bookDao.findAll()).hasSize(BOOK_COUNT - 1);
    }

    @Test
    void shouldFindAllByGenre() {
        List<Book> onlyGenres = bookDao.findByGenre(new Genre(GENRE_ID));
        List<Book> booksFilterByGenre = bookDao.findAll().stream()
                .filter(book -> book.getGenre().getId() == GENRE_ID)
                .collect(Collectors.toList());
        assertThat(onlyGenres).hasSize(2).isEqualTo(booksFilterByGenre);
    }

    @Test
    void shouldFindAllByAuthor() {
        List<Book> onlyAuthors = bookDao.findByAuthor(new Author(AUTHOR_ID));
        List<Book> booksFilterByAuthor = bookDao.findAll().stream()
                .filter(book -> book.getAuthor().getId() == AUTHOR_ID)
                .collect(Collectors.toList());
        assertThat(onlyAuthors).hasSize(1).isEqualTo(booksFilterByAuthor);
    }
}
