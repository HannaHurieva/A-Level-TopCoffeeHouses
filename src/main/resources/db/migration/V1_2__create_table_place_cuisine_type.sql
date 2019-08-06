CREATE TABLE place_cuisine_type (
  fk_place_id     bigint not null,
  cuisine_types   integer
  )
  ENGINE = InnoDB;

ALTER TABLE place_cuisine_type
    add constraint cuisine_type_place_fk
    foreign key (fk_place_id) references places (place_id);
