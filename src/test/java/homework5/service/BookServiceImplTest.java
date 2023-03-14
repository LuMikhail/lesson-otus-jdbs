package homework5.service;

import homework5.dao.AuthorDao;
import homework5.dao.BookDao;
import homework5.dao.GenreDao;
import homework5.domain.Author;
import homework5.domain.Book;
import homework5.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.verify;

@SpringBootTest
class BookServiceImplTest {

    @Configuration
    @Import({BookServiceImpl.class})
    static class NestedTestConfiguration {
    }

    @MockBean
    private BookDao bookDao;

    @MockBean
    private GenreDao genreDao;

    @MockBean
    private AuthorDao authorDao;

    @Autowired
    private BookServiceImpl bookService;

    @Test
    void calledCorrectlyMethodInsertBook() {
        Book book = new Book(0, "Скриба", new Author(3L), new Genre(2L), 3);
        bookService.insertBook("Скриба", 3, 2, 3);
        verify(bookDao).insert(book);
    }

    @Test
    void calledCorrectlyMethodFindBookById() {
        bookService.findBookById(1L);
        verify(bookDao).findById(1L);
    }

    @Test
    void calledCorrectlyMethodFindBooksContainThisGenre() {
        Genre genre = new Genre(1);
        bookService.findBooksContainThisGenre(1);
        verify((bookDao)).findBooksByGenreId(genre.getId());
    }

    @Test
    void calledCorrectlyMethodFindBooksContainThisAuthor() {
        Author author = new Author(1);
        bookService.findBooksContainThisAuthor(1);
        verify(bookDao).findBooksByAuthorId(author.getId());
    }

    @Test
    void calledCorrectlyMethodFindAllBooks() {
        bookService.findAllBooks();
        verify(bookDao).findAll();
    }
}