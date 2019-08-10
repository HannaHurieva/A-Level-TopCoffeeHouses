CREATE TABLE user_role (
  fk_user_id  bigint not null,
  roles       varchar(255)
)
  ENGINE = InnoDB;

ALTER TABLE user_role
    add constraint user_role_user_fk
    foreign key (fk_user_id) references users (user_id);
