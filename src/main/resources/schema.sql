create table IF NOT EXISTS author(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
	name_author VARCHAR(255)
);

create table IF NOT EXISTS genre(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
	name_genre VARCHAR(255)
);

create table IF NOT EXISTS book(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
	title     VARCHAR(255),
	author_id BIGINT,
	genre_id  BIGINT,
	amount    int,
	foreign key(author_id) references author(id) ON DELETE CASCADE,
	foreign key(genre_id) references genre(id) ON DELETE CASCADE
);

create table IF NOT EXISTS comments(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
	comment_book VARCHAR(255)
);

create table book_comments(
    book_id BIGINT,
    comment_id BIGINT,
    foreign key(book_id) references book(id) ON DELETE CASCADE,
    foreign key(comment_id) references comments(id) ON DELETE CASCADE
);

