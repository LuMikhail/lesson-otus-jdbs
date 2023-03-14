package homework5.service;

import homework5.dao.AuthorDao;
import homework5.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public void insertAuthor(String name) {
        authorDao.insert(new Author(name));
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorDao.findAll();
    }
}
