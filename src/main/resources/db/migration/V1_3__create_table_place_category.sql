CREATE TABLE place_categories (
  place_category_id   integer not null auto_increment,
  place_category      varchar(255),
  primary key (place_category_id)
  )
  ENGINE = InnoDB;

CREATE TABLE place_to_place_category (
  place_id            bigint not null,
  place_category_id   integer not null,
  primary key (place_id, place_category_id)
  )
  ENGINE = InnoDB;

ALTER TABLE place_to_place_category
    add constraint place_fk
    foreign key (place_id) references places (place_id);

ALTER TABLE place_to_place_category
    add constraint place_category_fk
    foreign key (place_category_id) references place_categories (place_category_id);
