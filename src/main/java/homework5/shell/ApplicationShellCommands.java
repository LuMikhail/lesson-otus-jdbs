package homework5.shell;

import homework5.service.AuthorService;
import homework5.service.BookService;
import homework5.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellCommands {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @ShellMethod(value = "Find all books", key = {"find-all-books", "AB"})
    public void findAllBooks() {
        bookService.findAllBooks().forEach(book ->
                System.out.printf("%s название: %s, автор: %s, жанр: %s, количество: %s\n",
                        book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getAmount()));
    }

    @ShellMethod(value = "Find all authors", key = {"find-all-authors", "AA"})
    public void findAllAuthors() {
        authorService.findAllAuthors().forEach(genre ->
                System.out.printf("%s , автор: %s\n",
                        genre.getId(), genre.getName()));
    }

    @ShellMethod(value = "Find all genre", key = {"find-all-genre", "AG"})
    public void findAllGenres() {
        genreService.findAllGenres().forEach(author ->
                System.out.printf("%s , жанр: %s\n",
                        author.getId(), author.getGenre()));
    }

    @ShellMethod(value = "Find book by author", key = {"find-books-by-author", "A"})
    public void findBookByAuthor(@ShellOption long authorId) {
        bookService.findBooksContainThisAuthor(authorId).forEach(book ->
                System.out.printf("%s название: %s, автор: %s, жанр: %s, количество: %s\n",
                        book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getGenre(), book.getAmount()));
    }

    @ShellMethod(value = "Find book by genre", key = {"find-books-by-genre", "G"})
    public void findBookByGenre(@ShellOption long genreId) {
        bookService.findBooksContainThisGenre(genreId).forEach(book ->
                System.out.printf("%s название: %s, автор: %s, жанр: %s, количество: %s\n",
                        book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getGenre(), book.getAmount()));
    }

    @ShellMethod(value = "Insert book", key = {"insert-book", "IB"})
    public void insertBook(@ShellOption String title,
                           @ShellOption long idAuthor,
                           @ShellOption long idGenre,
                           @ShellOption int amount) {
        bookService.insertBook(title, idAuthor, idGenre, amount);
    }

    @ShellMethod(value = "Insert author", key = {"insert-author", "IA"})
    public void insertAuthor(@ShellOption String author) {
        authorService.insertAuthor(author);
    }

    @ShellMethod(value = "Insert genre", key = {"insert-genre", "IG"})
    public void insertGenre(@ShellOption String genre) {
        genreService.insertGenre(genre);
    }

    @ShellMethod(value = "Update book by id", key = {"update-book", "UB"})
    public void updateBookById(@ShellOption long bookId,
                               @ShellOption String title,
                               @ShellOption long authorId,
                               @ShellOption long genreId,
                               @ShellOption int amount) {
        bookService.updateBookById(bookId, title, authorId, genreId, amount);
    }

    @ShellMethod(value = "Delete book by id", key = {"delete-book", "DB"})
    public void deleteBook(@ShellOption Long id) {
        bookService.deleteBooksById(id);
    }
}
