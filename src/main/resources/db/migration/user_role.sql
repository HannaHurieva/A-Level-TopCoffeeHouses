CREATE TABLE `user_role`
(
    `fk_user_id` bigint(20) NOT NULL,
    `roles`      varchar(255) DEFAULT NULL,
    KEY `FKfh5cd5dto0lin09k3niq509ur` (`fk_user_id`)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci