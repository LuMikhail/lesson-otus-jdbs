create table IF NOT EXISTS author(
    author_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	name_author VARCHAR(255)
);

create table IF NOT EXISTS genre(
    genre_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	name_genre VARCHAR(255)
);

create table IF NOT EXISTS book(
    book_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	title     VARCHAR(255),
	author_id BIGINT,
	genre_id  BIGINT,
	amount    int,
	foreign key(author_id) references author(author_id) ON DELETE CASCADE,
	foreign key(genre_id) references genre(genre_id) ON DELETE CASCADE
);
