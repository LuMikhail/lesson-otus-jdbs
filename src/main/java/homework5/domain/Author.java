package homework5.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {
    private long id;
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public Author(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Author(id=%s ,name=%s)",
                this.id,
                this.name);
    }
}
