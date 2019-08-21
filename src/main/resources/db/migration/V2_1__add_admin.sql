INSERT INTO users (user_id, username, first_name, last_name, email, password, status)
    VALUE (1, 'admin', 'Head', 'Administrator', 'admin@alevel.com', '$2a$08$JTFWXx.lAGNQ53zxGHPDNe0XIUC5NvPpfxosZIC5a6qORMkIH8VCK', 'ACTIVE');

INSERT INTO user_role (fk_user_id, roles)
    VALUES (1, 'ADMIN'), (1, 'USER');