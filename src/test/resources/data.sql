
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