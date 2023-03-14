package homework5.dao;

import homework5.domain.Author;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AuthorDaoJdbs implements AuthorDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public Author insert(Author author) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValues(Map.of("name", author.getName()));
        KeyHolder kh = new GeneratedKeyHolder();
        jdbcOperations.update("insert into author(name_author) values (:name)", parameters, kh, new String[]{"author_id"});

        return (kh.getKey() != null) ? findById(kh.getKey().longValue()) : null;
    }

    @Override
    public Author findById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbcOperations.queryForObject(
                "select author_id, name_author from author where author_id = :id", params, new AuthorMapper()
        );
    }

    @Override
    public List<Author> findAll() {
        return jdbcOperations.query("select author_id, name_author from author", new AuthorMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbcOperations.update("delete from author where author_id = :id", params);
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("author_id");
            String name = rs.getString("name_author");
            return new Author(id, name);
        }
    }
}
