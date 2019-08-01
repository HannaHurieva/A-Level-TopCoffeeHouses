CREATE TABLE `SPRING_SESSION`
(
    `PRIMARY_ID`            char(36)   NOT NULL,
    `SESSION_ID`            char(36)   NOT NULL,
    `CREATION_TIME`         bigint(20) NOT NULL,
    `LAST_ACCESS_TIME`      bigint(20) NOT NULL,
    `MAX_INACTIVE_INTERVAL` int(11)    NOT NULL,
    `EXPIRY_TIME`           bigint(20) NOT NULL,
    `PRINCIPAL_NAME`        varchar(100) DEFAULT NULL,
    PRIMARY KEY (`PRIMARY_ID`),
    UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
    KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
    KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = DYNAMIC