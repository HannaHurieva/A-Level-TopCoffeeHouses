CREATE TABLE `coffee_houses`
(
    `coffee_house_id` int(11)      NOT NULL AUTO_INCREMENT,
    `description`     varchar(255) DEFAULT NULL,
    `time_closing`    int(11)      DEFAULT NULL,
    `time_opening`    int(11)      DEFAULT NULL,
    `title`           varchar(255) NOT NULL,
    PRIMARY KEY (`coffee_house_id`)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci