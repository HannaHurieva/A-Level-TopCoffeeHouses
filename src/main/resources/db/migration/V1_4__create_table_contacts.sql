CREATE TABLE contacts (
  contact_id    bigint not null auto_increment,
  address       varchar(255) not null,
  location      varchar(255),
  phone         varchar(255),
  website       varchar(255),
  fk_place_id   bigint,
  primary key (contact_id)
)
  ENGINE = InnoDB;

ALTER TABLE contacts
    add constraint contacts_place_fk
    foreign key (fk_place_id) references places (place_id);
