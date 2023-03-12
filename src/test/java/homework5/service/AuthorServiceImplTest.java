package homework5.service;

import homework5.dao.AuthorDao;
import homework5.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.verify;

@SpringBootTest
class AuthorServiceImplTest {

    @Configuration
    @Import(AuthorServiceImpl.class)
    static class NestedTestConfiguration {
    }

    @MockBean
    private AuthorDao authorDao;

    @Autowired
    AuthorService authorService;

    @Test
    void calledCorrectlyInsertAuthor() {
        Author author = new Author("Джош Блох");
        authorService.insertAuthor("Джош Блох");
        verify(authorDao).insert(author);
    }

    @Test
    void calledCorrectlyFindAllAuthors() {
        authorService.findAllAuthors();
        verify(authorDao).findAll();
    }
}