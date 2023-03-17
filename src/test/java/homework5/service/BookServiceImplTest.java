package homework5.service;

import homework5.dao.BookDao;
import homework5.dao.CommentDao;
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
    @Import({CommentServiceImpl.class, BookServiceImpl.class})
    static class NestedTestConfiguration {
    }

    @MockBean
    private CommentDao commentDao;

    @MockBean
    private BookDao bookDao;

    @Autowired
    private BookServiceImpl bookService;

    @Test
    void calledCorrectlyMethodInsertBook() {
        Book book = new Book(0, "Скриба", new Author(3L), new Genre(2L), 3, null);
        bookService.insertBook("Скриба", 3, 2, 3);
        verify(bookDao).insert(book);
    }

    @Test
    void calledCorrectlyMethodFindBookById() {
        bookService.findBookById(1L);
        verify(bookDao).findById(1L);
    }

    @Test
    void calledCorrectlyMethodFindAllBooks() {
        bookService.findAllBooks();
        verify(bookDao).findAll();
    }

    @Test
    void calledCorrectlyMethodFindBooksContainThisGenre() {
        Genre genre = new Genre(1);
        bookService.findBooksContainThisGenre(1);
        verify((bookDao)).findByGenre(genre);
    }

    @Test
    void calledCorrectlyMethodFindBooksContainThisAuthor() {
        Author author = new Author(1);
        bookService.findBooksContainThisAuthor(1);
        verify(bookDao).findByAuthor(author);
    }

    @Test
    void calledCorrectlyMethodUpdateBookTitleById() {
        bookService.updateBookTitleById(1, "Новое название книги");
        verify(bookDao).updateTitleById(1, "Новое название книги");
    }

    @Test
    void calledCorrectlyMethodDeleteBooksById() {
        bookService.deleteBooksById(1L);
        verify(bookDao).deleteById(1L);
    }
}