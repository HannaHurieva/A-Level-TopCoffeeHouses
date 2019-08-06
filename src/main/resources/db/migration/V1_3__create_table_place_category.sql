CREATE TABLE place_category (
  fk_place_id       bigint not null,
  place_categories  integer
  )
  ENGINE = InnoDB;

ALTER TABLE place_category
    add constraint category_place_fk
    foreign key (fk_place_id) references places (place_id);
