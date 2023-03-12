package homework5.service;

import homework5.domain.Author;

import java.util.List;

public interface AuthorService {

    void insertAuthor(String name);

    List<Author> findAllAuthors();
}
