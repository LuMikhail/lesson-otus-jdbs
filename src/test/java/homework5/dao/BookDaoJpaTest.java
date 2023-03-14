package homework5.dao;

import homework5.domain.Author;
import homework5.domain.Book;
import homework5.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@JdbcTest
@Import(BookDaoJdbs.class)
class BookDaoJpaTest {

    private static final long FIRST_BOOK_ID = 1;
    private static final int BOOK_COUNT = 4;
    private static final long GENRE_ID = 1;
    private static final long AUTHOR_ID = 4;

    @Autowired
    private BookDao bookDao;

    @Test
    void shouldAddBook() {
       bookDao.insert(new Book(5L, "Скриба", new Author(3L), new Genre(2L), 3));
        assertThat(bookDao.findAll()).hasSize(BOOK_COUNT + 1);
    }

    @Test
    void shouldReturnBookById() {
        Book expectedBook = new Book(1L, "Над осевшими могилами", new Author(4L, "Джесс Уолтер"),
                new Genre(2L, "Детектив"), 3);
        Book actualBook = bookDao.findById(1);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
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
    void shouldFindAllBooksByGenre() {
        List<Book> onlyGenres = bookDao.findBooksByGenreId(GENRE_ID);
        List<Book> booksFilterByGenre = bookDao.findAll().stream()
                .filter(book -> book.getGenre().getId() == GENRE_ID)
                .collect(Collectors.toList());
        assertThat(onlyGenres).hasSize(2).isEqualTo(booksFilterByGenre);
    }

    @Test
    void shouldFindAllBooksByAuthor() {
        List<Book> onlyAuthors = bookDao.findBooksByAuthorId(AUTHOR_ID);
        List<Book> booksFilterByAuthor = bookDao.findAll().stream()
                .filter(book -> book.getAuthor().getId() == AUTHOR_ID)
                .collect(Collectors.toList());
        assertThat(onlyAuthors).hasSize(1).isEqualTo(booksFilterByAuthor);
    }
}
