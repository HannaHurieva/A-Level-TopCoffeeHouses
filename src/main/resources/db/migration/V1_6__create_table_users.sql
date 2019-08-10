CREATE TABLE users (
  user_id     bigint not null auto_increment,
  username    varchar(255) not null,
  first_name  varchar(255),
  last_name   varchar(255),
  email       varchar(255) not null,
  password    varchar(60) not null,
  status      varchar(255),
  primary key (user_id)
  )
  ENGINE = InnoDB;

