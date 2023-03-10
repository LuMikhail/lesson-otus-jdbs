package homework5.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "genre")
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name_genre", nullable = false)
    private String genre;

    public Genre(long id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public Genre(long id) {
        this.id = id;
    }

    public Genre(String genre) {
        this.genre = genre;
    }
}
