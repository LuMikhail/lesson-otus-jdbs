package homework5.dao;

import homework5.domain.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DataJpaTest
@Import({CommentDaoJpa.class})
class CommentDaoJpaTest {

    private static final long FIRST_COMMENT_ID = 1;
    private static final String FIRST_COMMENT_FIRST_NAME = "comment_book_01";
    private static final int COMMENT_COUNT = 4;

    @Autowired
    private TestEntityManager em;

    @Autowired
    private CommentDaoJpa commentDaoJpa;

    @Test
    void shouldAddComment() {
        commentDaoJpa.insert(new Comment("Было интересно"));
        assertThat(commentDaoJpa.findAll()).hasSize(COMMENT_COUNT + 1);
    }

    @Test
    void shouldReturnCommentById() {
        Optional<Comment> comments = commentDaoJpa.findById(FIRST_COMMENT_ID);
        assertThat(comments).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("comment", FIRST_COMMENT_FIRST_NAME);
    }

    @Test
    void shouldFindAllComments() {
        List<Comment> comments = commentDaoJpa.findAll();
        assertThat(comments).hasSize(COMMENT_COUNT);
    }

    @Test
    void shouldCorrectDeleteCommentById() {
        assertThatCode(() -> commentDaoJpa.findById(FIRST_COMMENT_ID))
                .doesNotThrowAnyException();

        commentDaoJpa.deleteById(FIRST_COMMENT_ID);
        assertThat(commentDaoJpa.findAll()).hasSize(COMMENT_COUNT - 1);
    }
}
