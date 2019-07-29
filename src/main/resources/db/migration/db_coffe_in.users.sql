create table db_coffee_in.users (id bigint not null auto_increment,
email varchar(255) not null, first_name varchar(255), last_name varchar(255), password varchar(60) not null,
status varchar(255), username varchar(255) not null, primary key (id)) engine=MyISAM
