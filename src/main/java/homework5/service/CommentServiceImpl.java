package homework5.service;

import homework5.dao.BookDao;
import homework5.dao.CommentDao;
import homework5.domain.Book;
import homework5.domain.Comment;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final BookDao bookDao;

    @Override
    @Transactional
    public void insertComment(String comment) {
        commentDao.insert(new Comment(comment));
    }

    @Override
    public Optional<Comment> findCommentById(long id) {
        return commentDao.findById(id);
    }

    @Override
    @Transactional
    public List<Comment> findCommentsByBookId(long id) {
        Optional<Book> book = bookDao.findById(id);
        if (book.isPresent()) {
            Book findBook = book.get();
            List<Comment> comments = findBook.getComments();
            comments.forEach(System.out::println);
            return comments;
        }
        return null;
    }

    @Override
    @Transactional
    public void updateCommentById(long id, String comment) {
        commentDao.updateCommentById(id, comment);
    }

    @Override
    @Transactional
    public void deleteCommentById(long id) {
        commentDao.deleteById(id);
    }
}
