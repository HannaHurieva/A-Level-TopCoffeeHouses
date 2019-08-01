CREATE TABLE `ratings`
(
    `rating_id`              int(11) NOT NULL AUTO_INCREMENT,
    `rating_atmosphere`      int(11) DEFAULT NULL,
    `rating_food_quality`    int(11) DEFAULT NULL,
    `rating_price_quality`   int(11) DEFAULT NULL,
    `rating_service_quality` int(11) DEFAULT NULL,
    `fk_coffee_house_id`     int(11) DEFAULT NULL,
    PRIMARY KEY (`rating_id`),
    KEY `FK6fhgspyo967rmlr8kfmsby4an` (`fk_coffee_house_id`)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci