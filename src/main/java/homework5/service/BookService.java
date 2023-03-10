package homework5.service;

import homework5.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void insertBook(String title, long idAuthor, long idGenre, int amount);

    Book assignCommentToBook(Long bookId, Long commentId);

    Optional<Book> findBookById(long id);

    List<Book> findAllBooks();

    List<Book> findBooksContainThisGenre(long genre);

    List<Book> findBooksContainThisAuthor(long author);

    void updateBookTitleById(long id, String title);

    void deleteBooksById(long id);

}
