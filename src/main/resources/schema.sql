-- @Author Tom Wagenaar
-- Copy all the code underneath this comment and put it into a sql console to make the fooddiary schema.

DROP TABLE IF EXISTS user_project;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS product_entry;
DROP TABLE IF EXISTS unverified_product_entry;
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS product_nutrient;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS nutrient;
DROP TABLE IF EXISTS unverified_product_picture_location;
DROP SCHEMA IF EXISTS fooddiary;

CREATE SCHEMA IF NOT EXISTS fooddiary;
use fooddiary;
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
    closing_date   date                               NULL
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
    code                   int(25)                            NOT NULL UNIQUE ,
    group_code             int(3)                             NOT NULL,
    group_code_description varchar(255)                       NOT NULL,
    description_dutch      varchar(255)                       NOT NULL,
    description_english    varchar(255)                       NOT NULL,
    synonymous             varchar(255)                       NULL,
    measurement_unit       varchar(10)                        NOT NULL,
    measurement_quantity   int(5)                             NOT NULL,
    measurement_comment    varchar(510)                       NULL,
    enriched_with          varchar(255)                       NULL,
    traces_of              varchar(255)                       NULL
);

CREATE TABLE IF NOT EXISTS product_entry
(
    id          int(25) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_id     int(10),
    product_id  int(25),
    quantity    varchar(255)                       NOT NULL,
    date        date                              NOT NULL,
    time_of_day varchar(25)                        NOT NULL,
    mealtime    varchar(25)                        NOT NULL,
    description varchar(255)                       NULL,

    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE IF NOT EXISTS unverified_product_entry
(
    id          int(10) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_id     int(10),
    quantity    varchar(255)                       NOT NULL,
    date        varchar(255)                       NOT NULL,
    time_of_day varchar(255)                       NOT NULL,
    mealtime    varchar(25)                        NOT NULL,
    description varchar(255)                       NOT NULL,

    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS unverified_product_picture_location
(
    id                                  int(10) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    unverified_product_id               int(10) NOT NULL,
    unverified_product_picture_location varchar(255) NOT NULL,

    FOREIGN Key (unverified_product_id) references unverified_product_entry(id)
);


CREATE TABLE IF NOT EXISTS recipe
(
    id           int(10) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_id      int(10),
    product_code int(25),
    recipe_group varchar(255)                       NOT NULL,
    quantity     int(5)                             NOT NULL,
    unit         varchar(10)                        NOT NULL,
    verified     boolean                            NOT NULL,

    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (product_code) REFERENCES product (code)
);

CREATE TABLE IF NOT EXISTS nutrient
(
    id               int(5) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nutrient_code    varchar(25)                       NOT NULL UNIQUE,
    name_dutch       varchar(255)                       NOT NULL,
    name_english     varchar(255)                       NOT NULL,
    measurement_unit varchar(255)                       NOT NULL
);

CREATE TABLE IF NOT EXISTS product_nutrient
(
    id             int(25) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    product_code   int(25),
    nutrient_code  varchar(25),
    nutrient_value varchar(10)                            NOT NULL,

    FOREIGN KEY (product_code) REFERENCES product (code),
    FOREIGN KEY (nutrient_code) REFERENCES nutrient (nutrient_code)
);
