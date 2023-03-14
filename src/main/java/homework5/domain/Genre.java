package homework5.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Genre {
    private long id;
    private String genre;

    public Genre(long id) {
        this.id = id;
    }

    public Genre(String genre) {
        this.genre = genre;
    }
}
