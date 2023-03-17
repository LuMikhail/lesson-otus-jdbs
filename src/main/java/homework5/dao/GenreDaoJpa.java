package homework5.dao;

import homework5.domain.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GenreDaoJpa implements GenreDao {

    @PersistenceContext
    private final EntityManager em;

    public GenreDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Genre insert(Genre genre) {
        if (genre.getId() <= 0) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
       Genre genre = em.find(Genre.class, id);
       em.remove(genre);
    }
}
