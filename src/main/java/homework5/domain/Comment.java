package homework5.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "comment_book")
    private String comment;

    @ManyToMany(mappedBy = "comments")
    private List<Book> books;

    public Comment(String comment) {
        this.comment = comment;
    }

    public Comment(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Comment(id=%s ,comment=%s)",
                this.id,
                this.comment);
    }
}
