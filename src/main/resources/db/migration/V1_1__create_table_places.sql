CREATE TABLE places (
  place_id        bigint not null auto_increment,
  title           varchar(255) not null,
  description     varchar(2048),
  time_opening    integer,
  time_closing    integer,
  primary key (place_id)
  )
  ENGINE = InnoDB;
