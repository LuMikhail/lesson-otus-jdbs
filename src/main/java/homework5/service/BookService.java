package homework5.service;

import homework5.domain.Book;

import java.util.List;

public interface BookService {

    void insertBook(String title, long idAuthor, long idGenre, int amount);

    Book findBookById(Long id);

    List<Book> findBooksContainThisGenre(long id);

    List<Book> findBooksContainThisAuthor(long id);

    List<Book> findAllBooks();

    void updateBookById(long bookId, String title, long authorId, long genreId, int amount);

    void deleteBooksById(long id);

}
