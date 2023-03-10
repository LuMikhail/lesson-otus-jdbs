package homework5.service;

import homework5.dao.BookDao;
import homework5.dao.CommentDao;
import homework5.domain.Author;
import homework5.domain.Book;
import homework5.domain.Comment;
import homework5.domain.Genre;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final CommentDao commentDao;

    @Override
    @Transactional
    public Book assignCommentToBook(Long bookId, Long commentId) {
        List<Comment> commentList;
        Book book = bookDao.findById(bookId).get();
        Comment comment = commentDao.findById(commentId).get();
        commentList = book.getComments();
        commentList.add(comment);
        book.setComments(commentList);
        return bookDao.insert(book);
    }

    @Override
    @Transactional
    public void insertBook(String title, long idAuthor, long idGenre, int amount) {
        bookDao.insert(new Book(title, new Author(idAuthor), new Genre(idGenre), amount));
    }

    @Override
    @Transactional
    public Optional<Book> findBookById(long id) {
        return bookDao.findById(id);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public List<Book> findBooksContainThisGenre(long genre) {
        return bookDao.findByGenre(new Genre(genre));
    }

    @Override
    public List<Book> findBooksContainThisAuthor(long author) {
        return bookDao.findByAuthor(new Author(author));
    }

    @Override
    @Transactional
    public void updateBookTitleById(long id, String title) {
        bookDao.updateTitleById(id, title);
    }

    @Override
    @Transactional
    public void deleteBooksById(long id) {
        bookDao.deleteById(id);
    }
}
