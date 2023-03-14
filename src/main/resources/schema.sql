
---AUTHOR
drop table if exists author cascade;

---GENRE
drop table if exists genre cascade;

---BOOK
drop table if exists book cascade;

create table author(
	author_id   bigint primary key generated always as identity,
	name_author text
);

create table if not exists genre(
	genre_id   bigint primary key generated always as identity,
	name_genre text
);

create table if not exists book(
	book_id   bigint primary key generated always as identity,
	title     text,
	author_id bigint not null,
	genre_id  bigint,
	amount    int,
	constraint "Fk_book_author"
		foreign key (author_id) references author (author_id) on delete cascade,
	constraint "Fk_book_genre"
		foreign key (genre_id) references genre (genre_id) on delete set null
);
