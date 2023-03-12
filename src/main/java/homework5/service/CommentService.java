package homework5.service;

import homework5.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    void insertComment(String comment);

    List<Comment> findCommentsByBookId(long id);

    Optional<Comment> findCommentById(long id);

    void updateCommentById(long id, String comment);

    void deleteCommentById(long id);
}
