package homework5.dao;

import homework5.domain.Genre;
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
public class GenreDaoJdbs implements GenreDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public Genre insert(Genre genre) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValues(Map.of("name", genre.getGenre()));
        KeyHolder kh = new GeneratedKeyHolder();
        jdbcOperations.update("insert into genre (name_genre) values (:name)", parameters, kh, new String[]{"genre_id"});

        return (kh.getKey() != null) ? findById(kh.getKey().longValue()) : null;
    }

    @Override
    public Genre findById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbcOperations.queryForObject(
                "select genre_id, name_genre from genre where genre_id = :id", params, new GenreMapper()
        );
    }

    @Override
    public List<Genre> findAll() {
        return jdbcOperations.query("select genre_id, name_genre from genre", new GenreMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbcOperations.update("delete from genre where genre_id = :id", params);
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("genre_id");
            String name = rs.getString("name_genre");
            return new Genre(id, name);
        }
    }
}
