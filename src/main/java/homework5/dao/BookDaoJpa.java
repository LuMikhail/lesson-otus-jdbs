package homework5.dao;

import homework5.domain.Author;
import homework5.domain.Book;
import homework5.domain.Genre;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private final EntityManager em;

    public BookDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book insert(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = em.createEntityGraph("book-comment-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.author" +
                                                " join fetch b.genre" +
                                                " left join fetch b.comments", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }


    @Override
    public void updateTitleById(long id, String title) {
        Query query = em.createQuery("update Book b set b.title = :title where b.id = :id");
        query.setParameter("id", id);
        query.setParameter("title", title);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Book book = em.find(Book.class, id);
        em.remove(book);
    }

    @Override
    public List<Book> findByGenre(Genre genre) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b join fetch b.author join fetch b.genre where b.genre = :genre", Book.class);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b join fetch b.author join fetch b.genre where b.author = :author", Book.class);
        query.setParameter("author", author);
        return query.getResultList();
    }
}
