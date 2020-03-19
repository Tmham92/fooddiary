<<<<<<< HEAD
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
=======
-- Copy all the code underneath this comment and put it into a sql console to make the fooddiary schema.

CREATE SCHEMA IF NOT EXISTS fooddiary;

DROP TABLE IF EXISTS user_project;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS product_entry;
DROP TABLE IF EXISTS unverified_product_entry;
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS product_nutrient;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS nutrient;

CREATE TABLE IF NOT EXISTS user
(
    id        int(10) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_code varchar(25)                        NOT NULL UNIQUE,
    password  varchar(255)                       NOT NULL,
    role      varchar(25)                        NOT NULL,
    enabled   TINYINT
);

CREATE TABLE IF NOT EXISTS project
(
    id             int(10) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name           varchar(50)                        NOT NULL,
    project_leader varchar(50)                        NOT NULL,
    creation_date  date                               NOT NULL,
    closing_date   date                               NOT NULL
);

CREATE TABLE IF NOT EXISTS user_project
(
    id         int(10) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_id    int(10),
    project_id int(10),

    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (project_id) REFERENCES project (id)
);

CREATE TABLE IF NOT EXISTS product
(
    id                     int(25) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    code                   int(25)                            NOT NULL,
    group_code             int(3)                             NOT NULL,
    group_code_description varchar(255)                       NOT NULL,
    description_dutch      varchar(255)                       NOT NULL,
    description_english    varchar(255)                       NOT NULL,
    synonymous             varchar(255)                       NULL,
    measurement_unit       varchar(10)                        NOT NULL,
    measurement_quantity   int(5)                             NOT NULL,
    measurement_comment    varchar(255)                       NULL,
    enriched_with          varchar(255)                       NULL,
    traces_of              varchar(255)                       NULL
);

CREATE TABLE IF NOT EXISTS product_entry
(
    id          int(25) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_id     int(10),
    product_id  int(25),
    date        date                               NOT NULL,
    time_of_day datetime                           NOT NULL,
    mealtime    varchar(25)                        NOT NULL,
    description varchar(255)                       NULL,

    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE IF NOT EXISTS unverified_product_entry
(
    id          int(10) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_id     int(10),
    date        date                               NOT NULL,
    time_of_day datetime                           NOT NULL,
    mealtime    varchar(25)                        NOT NULL,
    description varchar(255)                       NOT NULL,

    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS recipe
(
    id           int(10) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_id      int(10),
    product_id   int(25),
    recipe_group int(10)                            NOT NULL,
    verified     boolean                            NOT NULL,

    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE IF NOT EXISTS nutrient
(
    id               int(5) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nutrient_code    varchar(25)                       NOT NULL UNIQUE,
    name_dutch       varchar(50)                       NOT NULL UNIQUE,
    name_english     varchar(50)                       NOT NULL UNIQUE,
    measurement_unit varchar(10)                       NOT NULL
);

CREATE TABLE IF NOT EXISTS product_nutrient
(
    id             int(25) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    product_id     int(25),
    nutrient_id    int(5),
    nutrient_value int(10)                            NOT NULL,

    FOREIGN KEY (product_id) REFERENCES product (id),
    FOREIGN KEY (nutrient_id) REFERENCES nutrient (id)
);
>>>>>>> 7eaf715b9f6af0a70cc142240392fecf662949f5
