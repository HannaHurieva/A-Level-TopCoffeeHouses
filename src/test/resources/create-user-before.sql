delete from user_role;
delete from users;

insert into users (id, username, password, email) values
(1, 'admin', '$2a$08$fhw1YWzqz5jQRYG1bHrT7OEn5/czNxIPwhjd005OhnRgP6s3nvAF2', 'admin@alevel.com');
/*password = admin*/

insert into users (id, username, first_name, last_name, password, email) values
(2, 'User1', 'Name', 'Surname', '$2a$08$U7OBQDI1cuJKVUi4krLt1eIU1V7A3isMe1HKTZBu8WlR1QEsq.k.S', 'user@alevel.com');
/*password = user*/

insert into user_role (user_id, roles) values
(1, 'ADMIN'), (1, 'USER'),
(2, 'USER');