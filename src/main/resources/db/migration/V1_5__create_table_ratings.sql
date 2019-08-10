CREATE TABLE ratings (
  rating_id               bigint not null auto_increment,
  rating_food_quality     integer,
  rating_service_quality  integer,
  rating_price_quality    integer,
  rating_atmosphere       integer,
  fk_place_id             bigint,
  primary key (rating_id)
  )
  ENGINE = InnoDB;

ALTER TABLE ratings
    add constraint ratings_place_fk
    foreign key (fk_place_id) references places (place_id);
