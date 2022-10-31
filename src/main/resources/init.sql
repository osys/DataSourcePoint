USE mysql;
-- 主数据源
CREATE SCHEMA IF NOT EXISTS point_connect_demo COLLATE utf8_general_ci;
-- 其它数据源 ...
CREATE SCHEMA IF NOT EXISTS dynamic_one_db COLLATE utf8_general_ci;
CREATE SCHEMA IF NOT EXISTS dynamic_two_db COLLATE utf8_general_ci;
CREATE SCHEMA IF NOT EXISTS dynamic_three_db COLLATE utf8_general_ci;
CREATE SCHEMA IF NOT EXISTS dynamic_four_db COLLATE utf8_general_ci;


USE point_connect_demo;
CREATE TABLE IF NOT EXISTS user
(
    `id`        INT,
    `user_name` VARCHAR(25),
    `sex`       VARCHAR(5),
    `birthday`  BIGINT,
    `address`   VARCHAR(255)
);
INSERT INTO user(`id`, `user_name`, `sex`, `birthday`, `address`)
VALUES (1, 'name1', '男', 974698564, '中国*****1'),
       (2, 'name2', '女', 989658124, '中国*****2'),
       (3, 'name3', '男', 984088979, '中国*****3'),
       (4, 'name4', '女', 1031007815, '中国*****4'),
       (5, 'name5', '男', 936551363, '中国*****5');
-- 数据库连接信息
CREATE TABLE IF NOT EXISTS connect_property
(
    `key`               VARCHAR(50) PRIMARY KEY,
    `data_source_name`  VARCHAR(100),
    `port`              VARCHAR(20),
    `host`              VARCHAR(50),
    `password`          VARCHAR(255),
    `user_name`         VARCHAR(100),
    `driver_class_name` VARCHAR(100)
);
INSERT INTO connect_property(`key`, `data_source_name`, `port`, `host`, `password`, `user_name`, `driver_class_name`)
VALUES ('db_one', 'dynamic_one_db', '3306', '127.0.0.1', '123456', 'root', 'com.mysql.cj.jdbc.Driver'),
       ('db_two', 'dynamic_two_db', '3306', '127.0.0.1', '123456', 'root', 'com.mysql.cj.jdbc.Driver'),
       ('db_three', 'dynamic_three_db', '3306', '127.0.0.1', '123456', 'root', 'com.mysql.cj.jdbc.Driver'),
       ('db_four', 'dynamic_four_db', '3306', '127.0.0.1', '123456', 'root', 'com.mysql.cj.jdbc.Driver');


USE dynamic_one_db;
CREATE TABLE IF NOT EXISTS user_shopping
(
    `user_id`      INT,
    `platform`     VARCHAR(255),
    `shop_name`    VARCHAR(255),
    `product_name` VARCHAR(255),
    `price`        FLOAT
);
INSERT INTO user_shopping(`user_id`, `platform`, `shop_name`, `product_name`, `price`)
VALUES (1, '淘宝', '**玩具店', '奥特曼', 20.99),
       (2, '京东', '**书店', '钢铁是怎么练废的', 19.49),
       (3, '拼多多', '**手机店', '华为 mate plus max 50', 6666.66),
       (4, '天猫', '**时装店', '旺仔套装', 199.9),
       (5, '闲鱼', '', '闲置男朋友', 99.99);


USE dynamic_two_db;
CREATE TABLE IF NOT EXISTS user_qq
(
    `user_id`     INT,
    `account`     VARCHAR(255),
    `password`    VARCHAR(255),
    `create_time` VARCHAR(50),
    `update_time` VARCHAR(50)
);
INSERT INTO user_qq(`user_id`, `account`, `password`, `create_time`, `update_time`)
VALUES (1, '1***1', 'password1', '2021-02-20 20:30:45', '2022-08-16 17:58:11'),
       (2, '1***2', 'password2', '2021-10-23 05:30:01', '2022-09-01 19:26:35'),
       (3, '1***3', 'password3', '2021-05-28 14:17:05', '2022-08-31 14:19:59'),
       (4, '1***4', 'password4', '2020-09-06 18:45:45', '2022-07-01 12:42:33'),
       (5, '1***5', 'password5', '2018-06-08 19:30:00', '2022-09-02 15:37:27');


USE dynamic_three_db;
CREATE TABLE IF NOT EXISTS user_love_game
(
    `user_id`   INT,
    `game_name` JSON
);
INSERT INTO user_love_game(`user_id`, `game_name`)
VALUES (1, '{"game1:": "lol", "game2": "dnf"}'),
       (2, '{"game1:": "cf", "game2": "dnf"}'),
       (3, '{"game1:": "lol", "game2": "cs"}'),
       (4, '{"game1:": "cs", "game2": "dnf"}'),
       (5, '{"game1:": "cs", "game2": "cf"}');

USE dynamic_four_db;
CREATE TABLE IF NOT EXISTS school
(
    `name`       VARCHAR(64) NOT NULL,
    `school_id`  INT         NOT NULL,
    `user_id`    INT         NOT NULL,
    `class_name` VARCHAR(64) NOT NULL,
    `course`     TEXT        NULL
);
INSERT INTO school(`name`, `school_id`, `user_id`, `class_name`, `course`)
VALUES ('第一中学', 10001, 1, '高一(1)班', '["Java","Python"]'),
       ('第一中学', 10001, 2, '高二(3)班', '["C","C++"]'),
       ('第二中学', 10001, 3, '高一(5)班', '["Linux","Scala"]'),
       ('第二中学', 10001, 4, '高二(7)班', '["语数英","数理化","德智体美劳"]'),
       ('第二中学', 10001, 5, '高三(9)班', '["大数据","人工智能"]');