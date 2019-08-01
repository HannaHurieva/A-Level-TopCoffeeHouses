CREATE TABLE `coffee_house_cuisine_type`
(
    `cuisine_type_id` int(11) NOT NULL,
    `coffee_house_id` int(11) NOT NULL,
    PRIMARY KEY (`coffee_house_id`, `cuisine_type_id`),
    KEY `FKpo1x0e7tdts9ewjr0dk1a6v3l` (`cuisine_type_id`)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci