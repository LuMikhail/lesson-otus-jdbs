package homework5.dao;

import homework5.domain.Author;
import homework5.domain.Book;
import homework5.domain.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class BookDaoJdbs implements BookDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    public BookDaoJdbs(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Book insert(Book book) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValues(Map.of("title", book.getTitle(),
                "author_id", book.getAuthor().getId(),
                "genre_id", book.getGenre().getId(),
                "amount", book.getAmount()));
        KeyHolder kh = new GeneratedKeyHolder();
        jdbcOperations.update("insert into book (title, author_id, genre_id, amount) " +
                              "values (:title, :author_id, :genre_id, :amount)", parameters, kh, new String[]{"book_id"});

        return (kh.getKey() != null) ? findById(kh.getKey().longValue()) : null;
    }

    @Override
    public Book findById(long id) {
        final Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbcOperations.queryForObject(
                "select b.book_id, b.title, a.name_author, a.author_id, g.name_genre, g.genre_id, b.amount " +
                "from book b join genre g using (genre_id) join author a using (author_id) where book_id = :id",
                params, new BookMapper()
        );
    }

    @Override
    public List<Book> findAll() {
        return jdbcOperations.query(
                "select b.book_id, b.title, a.name_author, a.author_id, g.name_genre, g.genre_id, b.amount "
                + "from book b join genre g using (genre_id) join author a using (author_id)", new BookMapper());
    }

    @Override
    public void updateById(long id, String title, long authorId, long genreId, int amount) {
        final Map<String, Object> params = Map.of("id", id,
                "title", title, "author_id", authorId, "genre_id", genreId, "amount", amount);
        jdbcOperations.update("update book set title = :title, author_id = :author_id," +
                              " genre_id = :genre_id, amount = :amount where book_id = :id", params);
    }

    @Override
    public void deleteById(long id) {
        final Map<String, Object> params = Collections.singletonMap("id", id);
        jdbcOperations.update("delete from book where book_id = :id", params);
    }

    @Override
    public List<Book> findBooksByGenreId(long genreId) {
        final Map<String, Object> params = Collections.singletonMap("id", genreId);
        return jdbcOperations.query(
                "select b.book_id, b.title, a.name_author, a.author_id, g.name_genre, g.genre_id, b.amount "
                + "from book b join genre g using (genre_id) join author a using (author_id) where g.genre_id = :id",
                params, new BookMapper());
    }

    @Override
    public List<Book> findBooksByAuthorId(long authorId) {
        final Map<String, Object> params = Collections.singletonMap("id", authorId);
        return jdbcOperations.query(
                "select b.book_id, b.title, a.name_author, a.author_id, g.name_genre, g.genre_id, b.amount "
                + "from book b join genre g using (genre_id) join author a using (author_id) where a.author_id = :id",
                params, new BookMapper());
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            final long id = rs.getLong("book_id");
            final String title = rs.getString("title");
            final Author author = new Author(rs.getLong("author_id"), rs.getString("name_author"));
            final Genre genre = new Genre(rs.getLong("genre_id"), rs.getString("name_genre"));
            final int amount = rs.getInt("amount");
            return new Book(id, title, author, genre, amount);
        }
    }
}
