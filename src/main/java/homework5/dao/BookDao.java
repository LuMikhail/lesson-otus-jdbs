package homework5.dao;

import homework5.domain.Book;

import java.util.List;

public interface BookDao {

    Book insert(Book book);

    Book findById(long id);

    List<Book> findAll();

    void updateById(long id, String name, long authorId, long genreId, int amount);

    void deleteById(long id);

    List<Book> findBooksByGenreId(long genreId);

    List<Book> findBooksByAuthorId(long authorId);
}
