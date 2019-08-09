INSERT INTO db_coffee_in.users (user_id, username, email, password, status)
    VALUE (1, 'admin', 'admin@alevel.com', '$2a$08$JTFWXx.lAGNQ53zxGHPDNe0XIUC5NvPpfxosZIC5a6qORMkIH8VCK', 'ACTIVE');

INSERT INTO db_coffee_in.user_role (fk_user_id, roles)
    VALUES (1, 'ADMIN'), (1, 'USER');