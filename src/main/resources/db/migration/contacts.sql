CREATE TABLE `contacts`
(
    `contact_id`         int(11)      NOT NULL AUTO_INCREMENT,
    `address`            varchar(255) NOT NULL,
    `location`           varchar(255) DEFAULT NULL,
    `phone`              varchar(255) DEFAULT NULL,
    `website`            varchar(255) DEFAULT NULL,
    `fk_coffee_house_id` int(11)      DEFAULT NULL,
    PRIMARY KEY (`contact_id`),
    KEY `FKqxy3u45kjhk0dg3gh0gp7o54x` (`fk_coffee_house_id`)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci