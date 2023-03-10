package homework5.dao;

import homework5.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

    Comment insert(Comment comment);

    Optional<Comment> findById(long id);

    List<Comment> findAll();

    void updateCommentById(long id, String comment);

    void deleteById(long id);
}
