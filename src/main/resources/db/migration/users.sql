CREATE TABLE `users`
(
    `user_id`    bigint(20)   NOT NULL AUTO_INCREMENT,
    `email`      varchar(255) NOT NULL,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name`  varchar(255) DEFAULT NULL,
    `password`   varchar(60)  NOT NULL,
    `status`     varchar(255) DEFAULT NULL,
    `username`   varchar(255) NOT NULL,
    PRIMARY KEY (`user_id`)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci