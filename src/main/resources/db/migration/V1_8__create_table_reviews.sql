CREATE TABLE reviews (
  review_id     bigint not null auto_increment,
  text          varchar(2048),
  fk_user_id    bigint,
  fk_place_id   bigint,
  primary key (review_id)
  )
  ENGINE = InnoDB;

ALTER TABLE reviews
    add constraint reviews_user_fk
    foreign key (fk_user_id) references users (user_id),

    add constraint reviews_place_fk
    foreign key (fk_place_id) references places (place_id);
