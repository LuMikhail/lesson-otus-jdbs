package homework5.dao;

import homework5.domain.Author;
import homework5.domain.Book;
import homework5.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Book insert(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    void updateTitleById(long id, String title);

    void deleteById(long id);

    List<Book> findByGenre(Genre genre);

    List<Book> findByAuthor(Author author);

}
