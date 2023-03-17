package homework5.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
@NamedEntityGraph(name = "book-comment-entity-graph",
        attributeNodes = {@NamedAttributeNode("comments")})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    @ManyToMany(targetEntity = Comment.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "book_comments", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id"))
    private List<Comment> comments;

    public Book(String title, Author author, Genre genre, int amount) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.amount = amount;
    }
}
