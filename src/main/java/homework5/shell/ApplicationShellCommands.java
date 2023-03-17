package homework5.shell;

import homework5.domain.Book;
import homework5.service.AuthorService;
import homework5.service.BookService;
import homework5.service.CommentService;
import homework5.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellCommands {
    private final AuthorService authorService;
    private final BookService bookService;
    private final CommentService commentService;
    private final GenreService genreService;

    @ShellMethod(value = "Find all books", key = {"find-all-books", "AB"})
    public void findAllBooks() {
        bookService.findAllBooks().forEach(book ->
                System.out.printf("%s название: %s, автор: %s, жанр: %s, количество: %s\n\t, комментарий: %s \n",
                        book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getAmount(), book.getComments()));

    }

    @ShellMethod(value = "Find all authors", key = {"find-all-authors", "AA"})
    public void findAllAuthors() {
        authorService.findAllAuthors().forEach(author ->
                System.out.printf("%s , автор: %s\n",
                        author.getId(), author.getName()));
    }

    @ShellMethod(value = "Find all genres", key = {"find-all-genre", "AG"})
    public void findAllGenres() {
        genreService.findAllGenres().forEach(genre ->
                System.out.printf("%s , жанр: %s\n",
                        genre.getId(), genre.getGenre()));
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

    @ShellMethod(value = "Find comment by id", key = {"find-comment-by-id", "C"})
    public void findCommentById(@ShellOption long commentId) {
        String comment = commentService.findCommentById(commentId).get().getComment();
        System.out.printf("%s комментарий: %s\n", commentId, comment);
    }

    @ShellMethod(value = "Find book by id", key = {"find-book-by-id", "B"})
    public void findBookById(@ShellOption long bookId) {
        Book book = bookService.findBookById(bookId).get();
        System.out.printf("%s название: %s, автор: %s, жанр: %s, количество: %s\n",
                book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getGenre(), book.getAmount());
    }

    @ShellMethod(value = "Find comments by book id", key = {"find-comments-by-book-id", "CB"})
    public void findCommentBookById(@ShellOption long bookId) {
        commentService.findCommentsByBookId(bookId);
    }

    @ShellMethod(value = "Insert book", key = {"insert-book", "IB"})
    public void insertBook(@ShellOption String title,
                           @ShellOption long idAuthor,
                           @ShellOption long idGenre,
                           @ShellOption int amount) {
        bookService.insertBook(title, idAuthor, idGenre, amount);
    }

    @ShellMethod(value = "Insert comment", key = {"insert-comment", "IC"})
    public void insertComment(@ShellOption String comment) {
        commentService.insertComment(comment);
    }

    @ShellMethod(value = "Assign-comment-to-book", key = {"assign-comment-to-book", "ACB"})
    public void assignCommentByBook(@ShellOption long bookId,
                                    @ShellOption long commentId) {
        bookService.assignCommentToBook(bookId, commentId);
    }

    @ShellMethod(value = "Update title book by id", key = {"update-title-book", "UB"})
    public void updateBookById(@ShellOption long id,
                               @ShellOption String title) {
        bookService.updateBookTitleById(id, title);
    }

    @ShellMethod(value = "Delete book by id", key = {"delete-book", "DB"})
    public void deleteBookById(@ShellOption Long id) {
        bookService.deleteBooksById(id);
    }

    @ShellMethod(value = "Update comment by id", key = {"update-comment", "UC"})
    public void updateCommentById(@ShellOption long id,
                                  @ShellOption String title) {
        commentService.updateCommentById(id, title);
    }

    @ShellMethod(value = "Delete comment by id", key = {"delete-comment", "DC"})
    public void deleteCommentById(@ShellOption Long id) {
        commentService.deleteCommentById(id);
    }
}
