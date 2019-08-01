CREATE TABLE `reviews`
(
    `review_id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `text`               varchar(2048) DEFAULT NULL,
    `fk_user_id`         bigint(20)    DEFAULT NULL,
    `fk_coffee_house_id` int(11)       DEFAULT NULL,
    PRIMARY KEY (`review_id`),
    KEY `FK4dt9h2wixag9j0n8en3y48v1c` (`fk_user_id`),
    KEY `FKqsu4onxnjkuy7fb2owxl9e08s` (`fk_coffee_house_id`)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci