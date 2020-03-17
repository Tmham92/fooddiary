DROP TABLE IF EXISTS genes;
DROP TABLE IF EXISTS users;

CREATE TABLE `genes` (
  `id`           INT(11) NOT NULL AUTO_INCREMENT,
  `genbank_id`   VARCHAR(10)      DEFAULT NULL,
  `abbreviation` VARCHAR(100)     DEFAULT NULL,
  `name`         VARCHAR(100)     DEFAULT NULL,
  `process`      VARCHAR(100)     DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `genbank_id` (`genbank_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE IF NOT EXISTS users (
  id                 INT AUTO_INCREMENT,
  authority          VARCHAR(40)        NOT NULL,
  first_name         VARCHAR(45)        NOT NULL,
  last_name          VARCHAR(45)        NOT NULL,
  email              VARCHAR(45) UNIQUE,
  password           VARCHAR(80),
  enabled            TINYINT,
  created_date       DATETIME,
  password_token     VARCHAR(200),
  password_timestamp DATETIME,
  PRIMARY KEY (id)
);