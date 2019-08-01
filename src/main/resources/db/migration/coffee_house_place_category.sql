CREATE TABLE `coffee_house_place_category`
(
    `coffee_house_id`   int(11) NOT NULL,
    `place_category_id` int(11) NOT NULL,
    PRIMARY KEY (`place_category_id`, `coffee_house_id`),
    KEY `FKbug7k11cp20c7txq7kpf8hmvp` (`coffee_house_id`)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci