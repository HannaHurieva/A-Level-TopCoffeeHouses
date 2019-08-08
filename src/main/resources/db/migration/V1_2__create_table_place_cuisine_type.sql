CREATE TABLE cuisine_types (
  cuisine_type_id   integer not null auto_increment,
  cuisine_type      varchar(255),
  primary key (cuisine_type_id)
  )
  ENGINE = InnoDB;

CREATE TABLE place_cuisine_type (
  place_id          bigint not null,
  cuisine_type_id   integer not null,
  primary key (place_id, cuisine_type_id)
  )
  ENGINE = InnoDB;

ALTER TABLE place_cuisine_type
    add constraint cuisine_type_place_fk
    foreign key (place_id) references places (place_id);

ALTER TABLE place_cuisine_type
    add constraint place_cuisine_type_fk
    foreign key (cuisine_type_id) references cuisine_types (cuisine_type_id);
