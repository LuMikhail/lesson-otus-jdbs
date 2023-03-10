delete from author;
delete from genre;
delete from book;
delete from comments;

insert into author(name_author)
values  ('Крейг Уоллс'),
        ('Джон Лонг'),
        ('Антонио Гарридо'),
        ('Джесс Уолтер');

insert into  genre(name_genre)
values  ('Программирование'),
        ('Детектив');

insert  into book(title, author_id, genre_id, amount)
values ('Над осевшими могилами', 4, 2, 3),
('Читающий по телам', 3, 2, 2),
('Spring в действии', 1, 1, 5),
('Java в облаке', 2, 1, 3);

insert into comments(comment_book)
values ('comment_book_01'), ('comment_book_02'), ('comment_book_03'), ('comment_book_04');

insert into book_comments(book_id, comment_id)
values (1, 1),   (1, 2),   (1, 3),
       (2, 1),   (2, 2),   (2, 4),
       (3, 3),   (3, 2),   (3, 1),
       (4, 3),   (4, 1),   (4, 4);
