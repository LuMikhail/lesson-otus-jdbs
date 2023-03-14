package homework5.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Book {
    private long id;
    private final String title;
    private final Author author;
    private final Genre genre;
    private final int amount;

}
