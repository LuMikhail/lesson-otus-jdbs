package homework5.service;

import homework5.dao.AuthorDao;
import homework5.dao.BookDao;
import homework5.dao.GenreDao;
import homework5.domain.Author;
import homework5.domain.Book;
import homework5.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final AuthorDao authorDao;

    @Override
    public void insertBook(String title, long idAuthor, long idGenre, int amount) {
        bookDao.insert(new Book(title, new Author(idAuthor), new Genre(idGenre), amount));
    }

    @Override
    public Book findBookById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    public List<Book> findBooksContainThisGenre(long id) {
        return bookDao.findBooksByGenreId(id);
    }

    @Override
    public List<Book> findBooksContainThisAuthor(long id) {
        return bookDao.findBooksByAuthorId(id);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public void updateBookById(long bookId, String title, long authorId, long genreId, int amount) {
        Book book = bookDao.findById(bookId);
        Author author = authorDao.findById(authorId);
        Genre genre = genreDao.findById(genreId);
        bookDao.updateById(book.getId(), title, author.getId(), genre.getId(), amount);
    }

    @Override
    public void deleteBooksById(long id) {
        Book book = bookDao.findById(id);
        bookDao.deleteById(book.getId());
    }
}
