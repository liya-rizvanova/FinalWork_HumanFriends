```
--Запросы с ответами из терминала Linux

lr@lr-VirtualBox:~$ sudo mysql -u root -p
[sudo] password for lr: 
Enter password: 
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 13
Server version: 8.0.37-0ubuntu0.22.04.3 (Ubuntu)

Copyright (c) 2000, 2024, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> SET NAMES 'utf8';
Query OK, 0 rows affected, 1 warning (0,04 sec)

mysql> DROP DATABASE IF EXISTS HumanFriends;
Query OK, 11 rows affected (0,17 sec)

mysql> CREATE DATABASE HumanFriends;
Query OK, 1 row affected (0,00 sec)

mysql> USE HumanFriends;
Database changed
mysql> 

mysql> -- Удаление существующих таблиц (если они есть)
mysql> DROP TABLE IF EXISTS YoungAnimals;
Query OK, 0 rows affected, 1 warning (0,00 sec)

mysql> DROP TABLE IF EXISTS HorsesAndDonkeys;
Query OK, 0 rows affected, 1 warning (0,00 sec)

mysql> DROP TABLE IF EXISTS Dog;
Query OK, 0 rows affected, 1 warning (0,00 sec)

mysql> DROP TABLE IF EXISTS Cat;
INCREMENT PRIMARY KEY,
Query OK, 0 rows affected, 1 warning (0,00 sec)

mysql> DROP TABLE IF EXISTS Hamster;
Query OK, 0 rows affected, 1 warning (0,00 sec)

mysql> DROP TABLE IF EXISTS PackAnimals;
Query OK, 0 rows affected, 1 warning (0,01 sec)

mysql> DROP TABLE IF EXISTS Horse;
Query OK, 0 rows affected, 1 warning (0,00 sec)

mysql> DROP TABLE IF EXISTS Camel;
Query OK, 0 rows affected, 1 warning (0,00 sec)

mysql> DROP TABLE IF EXISTS Donkey;
Query OK, 0 rows affected, 1 warning (0,00 sec)

mysql> DROP TABLE IF EXISTS Pets;
Query OK, 0 rows affected, 1 warning (0,01 sec)

mysql> DROP TABLE IF EXISTS AnimalsTemp;
Query OK, 0 rows affected, 1 warning (0,00 sec)

mysql> DROP TABLE IF EXISTS Animals;
Query OK, 0 rows affected, 1 warning (0,00 sec)
mysql> 

mysql> -- Создание таблиц и вставка данных
mysql> CREATE TABLE Animals (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     type VARCHAR(20)
    -> );
Query OK, 0 rows affected (0,04 sec)
mysql> 

mysql> INSERT INTO Animals (type)
    -> VALUES ('Pets'), ('PackAnimals');
Query OK, 2 rows affected (0,01 sec)
Records: 2  Duplicates: 0  Warnings: 0
mysql> 

mysql> CREATE TABLE Pets (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     genus_name VARCHAR(20),
    ->     class_id INT,
    ->     FOREIGN KEY (class_id) REFERENCES Animals (id) ON DELETE CASCADE ON UPDATE CASCADE
    -> );
Query OK, 0 rows affected (0,04 sec)
mysql> 

mysql> INSERT INTO Pets (genus_name, class_id)
    -> VALUES ('Dog', 1), ('Cat', 1), ('Hamster', 1);
Query OK, 3 rows affected (0,00 sec)
Records: 3  Duplicates: 0  Warnings: 0
mysql> 

mysql> CREATE TABLE PackAnimals (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     genus_name VARCHAR(20),
    ->     class_id INT,
    ->     FOREIGN KEY (class_id) REFERENCES Animals (id) ON DELETE CASCADE ON UPDATE CASCADE
    -> );
Query OK, 0 rows affected (0,05 sec)
mysql> 

mysql> INSERT INTO PackAnimals (genus_name, class_id)
    -> VALUES ('Horse', 2), ('Camel', 2), ('Donkey', 2);
Query OK, 3 rows affected (0,01 sec)
Records: 3  Duplicates: 0  Warnings: 0
mysql> 

mysql> CREATE TABLE Dog (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     name VARCHAR(20),
    ->     birth_date DATE,
    ->     commands VARCHAR(50),
    ->     genus_id INT,
    ->     FOREIGN KEY (genus_id) REFERENCES Pets (id) ON DELETE CASCADE ON UPDATE CASCADE
    -> );
Query OK, 0 rows affected (0,03 sec)
mysql> 

mysql> INSERT INTO Dog (name, birth_date, commands, genus_id)
    -> VALUES ('Fido', '2011-10-01', 'Sit, Stay, Fetch', 1),
    ->        ('Buddy', '2021-04-01', 'Sit, Paw, Bark', 1),
    ->        ('Bella', '2017-01-01', 'Sit, Stay, Roll', 1);
Query OK, 3 rows affected (0,00 sec)
Records: 3  Duplicates: 0  Warnings: 0
mysql> 

mysql> CREATE TABLE Cat (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     name VARCHAR(20),
    ->     birth_date DATE,
    ->     commands VARCHAR(50),
    ->     genus_id INT,
    ->     FOREIGN KEY (genus_id) REFERENCES Pets (id) ON DELETE CASCADE ON UPDATE CASCADE
    -> );
Query OK, 0 rows affected (0,04 sec)
mysql> 

mysql> INSERT INTO Cat (name, birth_date, commands, genus_id)
    -> VALUES ('Whiskers', '2022-02-01', 'Sit, Pounce', 2),
    ->        ('Smudge', '2020-02-20', 'Sit, Pounce, Scratch', 2),
    ->        ('Oliver', '2020-06-30', 'Meow, Scratch, Jump', 2);
Query OK, 3 rows affected (0,00 sec)
Records: 3  Duplicates: 0  Warnings: 0
mysql> 

mysql> CREATE TABLE Hamster (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     name VARCHAR(20),
    ->     birth_date DATE,
    ->     commands VARCHAR(50),
    ->     genus_id INT,
    ->     FOREIGN KEY (genus_id) REFERENCES Pets (id) ON DELETE CASCADE ON UPDATE CASCADE
    -> );
Query OK, 0 rows affected (0,03 sec)
mysql> 

mysql> INSERT INTO Hamster (name, birth_date, commands, genus_id)
    -> VALUES ('Hammy', '2021-05-10', 'Roll, Hide', 3),
    ->        ('Peanut', '2021-08-01', 'Roll, Spin', 3);
Query OK, 2 rows affected (0,00 sec)
Records: 2  Duplicates: 0  Warnings: 0
mysql> 

mysql> CREATE TABLE Horse (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     name VARCHAR(20),
    ->     birth_date DATE,
    ->     commands VARCHAR(50),
    ->     genus_id INT,
    ->     FOREIGN KEY (genus_id) REFERENCES PackAnimals (id) ON DELETE CASCADE ON UPDATE CASCADE
    -> );
Query OK, 0 rows affected (0,03 sec)
mysql> 

mysql> INSERT INTO Horse (name, birth_date, commands, genus_id)
    -> VALUES ('Thunder', '2021-12-01', 'Gallop, Jump', 1),
    ->        ('Storm', '2014-05-05', 'Trot, Canter', 1),
    ->        ('Blaze', '2016-02-29', 'Trot, Jump, Gallop', 1);
Query OK, 3 rows affected (0,00 sec)
Records: 3  Duplicates: 0  Warnings: 0
mysql> 

mysql> CREATE TABLE Camel (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     name VARCHAR(20),
    ->     birth_date DATE,
    ->     commands VARCHAR(50),
    ->     genus_id INT,
    ->     FOREIGN KEY (genus_id) REFERENCES PackAnimals (id) ON DELETE CASCADE ON UPDATE CASCADE
    -> );
Query OK, 0 rows affected (0,03 sec)
mysql> 

mysql> INSERT INTO Camel (name, birth_date, commands, genus_id)
    -> VALUES ('Sandy', '2016-11-03', 'Walk, Carry Load', 2),
    ->        ('Coco', '2020-05-05', 'Walk, Run', 2),
    ->        ('Dune', '2018-12-12', 'Walk, Sit', 2),
    ->        ('Sahara', '2015-08-14', 'Walk, Run', 2);
Query OK, 4 rows affected (0,00 sec)
Records: 4  Duplicates: 0  Warnings: 0
mysql> 

mysql> CREATE TABLE Donkey (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     name VARCHAR(20),
    ->     birth_date DATE,
    ->     commands VARCHAR(50),
    ->     genus_id INT,
    ->     FOREIGN KEY (genus_id) REFERENCES PackAnimals (id) ON DELETE CASCADE ON UPDATE CASCADE
    -> );
Query OK, 0 rows affected (0,02 sec)
mysql> 

mysql> INSERT INTO Donkey (name, birth_date, commands, genus_id)
    -> VALUES ('Daisy', '2022-01-01', 'Bray, Carry', 3),
    ->        ('Eeyore', '2017-09-18', 'Walk, Carry Load, Bray', 3),
    ->        ('Burro', '2022-01-23', 'Stop, Bray, Kick', 3);
Query OK, 3 rows affected (0,00 sec)
Records: 3  Duplicates: 0  Warnings: 0
mysql> 

mysql> -- Отключение безопасных обновлений
mysql> SET SQL_SAFE_UPDATES = 0;
Query OK, 0 rows affected (0,00 sec)
mysql> 

mysql> -- Удаление данных из таблицы Camel
mysql> DELETE FROM Camel;
Query OK, 4 rows affected (0,00 sec)
mysql> 

mysql> -- Объединение таблиц Horse и Donkey в HorsesAndDonkeys
mysql> CREATE TABLE HorsesAndDonkeys (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     name VARCHAR(20),
    ->     birth_date DATE,
    ->     commands VARCHAR(50),
    ->     genus_name VARCHAR(20),
    ->     genus_id INT
    -> );
Query OK, 0 rows affected (0,05 sec)
mysql> 

mysql> INSERT INTO HorsesAndDonkeys (name, birth_date, commands, genus_name, genus_id)
    -> SELECT name, birth_date, commands, 'Horse' AS genus_name, genus_id
    -> FROM Horse
    -> UNION ALL
    -> SELECT name, birth_date, commands, 'Donkey' AS genus_name, genus_id
    -> FROM Donkey;
Query OK, 6 rows affected (0,01 sec)
Records: 6  Duplicates: 0  Warnings: 0
mysql> 

mysql> -- Проверка содержимого объединенной таблицы
mysql> SELECT * FROM HorsesAndDonkeys;
+----+---------+------------+------------------------+------------+----------+
| id | name    | birth_date | commands               | genus_name | genus_id |
+----+---------+------------+------------------------+------------+----------+
|  1 | Thunder | 2021-12-01 | Gallop, Jump           | Horse      |        1 |
|  2 | Storm   | 2014-05-05 | Trot, Canter           | Horse      |        1 |
|  3 | Blaze   | 2016-02-29 | Trot, Jump, Gallop     | Horse      |        1 |
|  4 | Daisy   | 2022-01-01 | Bray, Carry            | Donkey     |        3 |
|  5 | Eeyore  | 2017-09-18 | Walk, Carry Load, Bray | Donkey     |        3 |
|  6 | Burro   | 2022-01-23 | Stop, Bray, Kick       | Donkey     |        3 |
+----+---------+------------+------------------------+------------+----------+
6 rows in set (0,00 sec)
mysql> 

mysql> -- Создание временной таблицы AnimalsTemp для всех животных
mysql> CREATE TEMPORARY TABLE AnimalsTemp AS
    -> SELECT *, 'Dog' as genus FROM Dog
    -> UNION
    -> SELECT *, 'Cat' AS genus FROM Cat
    -> UNION
    -> SELECT *, 'Hamster' AS genus FROM Hamster
    -> UNION
    -> SELECT *, 'Horse' AS genus FROM Horse
    -> UNION
    -> SELECT *, 'Donkey' AS genus FROM Donkey;
Query OK, 14 rows affected (0,01 sec)
Records: 14  Duplicates: 0  Warnings: 0
mysql> 

mysql> -- Создание таблицы YoungAnimals для животных возрастом от 1 до 3 лет
mysql> CREATE TABLE YoungAnimals AS
    -> SELECT name, birth_date, commands, genus, TIMESTAMPDIFF(MONTH, birth_date, CURDATE()) AS Age_in_month
    -> FROM AnimalsTemp 
    -> WHERE TIMESTAMPDIFF(MONTH, birth_date, CURDATE()) BETWEEN 12 AND 36;
Query OK, 4 rows affected (0,04 sec)
Records: 4  Duplicates: 0  Warnings: 0
mysql> 

mysql> -- Запрос на выборку всех молодых животных
mysql> SELECT * FROM YoungAnimals;
+----------+------------+------------------+--------+--------------+
| name     | birth_date | commands         | genus  | Age_in_month |
+----------+------------+------------------+--------+--------------+
| Whiskers | 2022-02-01 | Sit, Pounce      | Cat    |           32 |
| Thunder  | 2021-12-01 | Gallop, Jump     | Horse  |           34 |
| Daisy    | 2022-01-01 | Bray, Carry      | Donkey |           33 |
| Burro    | 2022-01-23 | Stop, Bray, Kick | Donkey |           33 |
+----------+------------+------------------+--------+--------------+
4 rows in set (0,00 sec)
mysql> 

mysql> -- Объединение таблиц с дополнительными данными
mysql> SELECT d.name, d.birth_date, d.commands, ha.genus_name, ya.Age_in_month
    -> FROM Dog d
    -> LEFT JOIN YoungAnimals ya ON ya.name = d.name
    -> LEFT JOIN Pets ha ON ha.id = d.genus_id
    -> UNION
    -> SELECT c.name, c.birth_date, c.commands, ha.genus_name, ya.Age_in_month
    -> FROM Cat c
    -> LEFT JOIN YoungAnimals ya ON ya.name = c.name
    -> LEFT JOIN Pets ha ON ha.id = c.genus_id
    -> UNION
    -> SELECT h.name, h.birth_date, h.commands, ha.genus_name, ya.Age_in_month
    -> FROM Hamster h
    -> LEFT JOIN YoungAnimals ya ON ya.name = h.name
    -> LEFT JOIN Pets ha ON ha.id = h.genus_id
    -> UNION
    -> SELECT ho.name, ho.birth_date, ho.commands, pa.genus_name, ya.Age_in_month
    -> FROM Horse ho
    -> LEFT JOIN YoungAnimals ya ON ya.name = ho.name
    -> LEFT JOIN PackAnimals pa ON pa.id = ho.genus_id
    -> UNION
    -> SELECT do.name, do.birth_date, do.commands, pa.genus_name, ya.Age_in_month
    -> FROM Donkey do
    -> LEFT JOIN YoungAnimals ya ON ya.name = do.name
    -> LEFT JOIN PackAnimals pa ON pa.id = do.genus_id;
+----------+------------+------------------------+------------+--------------+
| name     | birth_date | commands               | genus_name | Age_in_month |
+----------+------------+------------------------+------------+--------------+
| Fido     | 2011-10-01 | Sit, Stay, Fetch       | Dog        |         NULL |
| Buddy    | 2021-04-01 | Sit, Paw, Bark         | Dog        |         NULL |
| Bella    | 2017-01-01 | Sit, Stay, Roll        | Dog        |         NULL |
| Whiskers | 2022-02-01 | Sit, Pounce            | Cat        |           32 |
| Smudge   | 2020-02-20 | Sit, Pounce, Scratch   | Cat        |         NULL |
| Oliver   | 2020-06-30 | Meow, Scratch, Jump    | Cat        |         NULL |
| Hammy    | 2021-05-10 | Roll, Hide             | Hamster    |         NULL |
| Peanut   | 2021-08-01 | Roll, Spin             | Hamster    |         NULL |
| Thunder  | 2021-12-01 | Gallop, Jump           | Horse      |           34 |
| Storm    | 2014-05-05 | Trot, Canter           | Horse      |         NULL |
| Blaze    | 2016-02-29 | Trot, Jump, Gallop     | Horse      |         NULL |
| Daisy    | 2022-01-01 | Bray, Carry            | Donkey     |           33 |
| Eeyore   | 2017-09-18 | Walk, Carry Load, Bray | Donkey     |         NULL |
| Burro    | 2022-01-23 | Stop, Bray, Kick       | Donkey     |           33 |
+----------+------------+------------------------+------------+--------------+
14 rows in set (0,00 sec)

```
```
--Запросы

DROP DATABASE IF EXISTS HumanFriends;
CREATE DATABASE HumanFriends;
USE HumanFriends;

-- Удаление существующих таблиц (если они есть)
DROP TABLE IF EXISTS YoungAnimals;
DROP TABLE IF EXISTS HorsesAndDonkeys;
DROP TABLE IF EXISTS Dog;
DROP TABLE IF EXISTS Cat;
DROP TABLE IF EXISTS Hamster;
DROP TABLE IF EXISTS PackAnimals;
DROP TABLE IF EXISTS Horse;
DROP TABLE IF EXISTS Camel;
DROP TABLE IF EXISTS Donkey;
DROP TABLE IF EXISTS Pets;
DROP TABLE IF EXISTS AnimalsTemp;
DROP TABLE IF EXISTS Animals;

-- Создание таблиц и вставка данных
CREATE TABLE Animals (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(20)
);

INSERT INTO Animals (type)
VALUES ('Pets'), ('PackAnimals');

CREATE TABLE Pets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    genus_name VARCHAR(20),
    class_id INT,
    FOREIGN KEY (class_id) REFERENCES Animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Pets (genus_name, class_id)
VALUES ('Dog', 1), ('Cat', 1), ('Hamster', 1);

CREATE TABLE PackAnimals (
    id INT AUTO_INCREMENT PRIMARY KEY,
    genus_name VARCHAR(20),
    class_id INT,
    FOREIGN KEY (class_id) REFERENCES Animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO PackAnimals (genus_name, class_id)
VALUES ('Horse', 2), ('Camel', 2), ('Donkey', 2);

CREATE TABLE Dog (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20),
    birth_date DATE,
    commands VARCHAR(50),
    genus_id INT,
    FOREIGN KEY (genus_id) REFERENCES Pets (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Dog (name, birth_date, commands, genus_id)
VALUES ('Fido', '2011-10-01', 'Sit, Stay, Fetch', 1),
       ('Buddy', '2021-04-01', 'Sit, Paw, Bark', 1),
       ('Bella', '2017-01-01', 'Sit, Stay, Roll', 1);

CREATE TABLE Cat (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20),
    birth_date DATE,
    commands VARCHAR(50),
    genus_id INT,
    FOREIGN KEY (genus_id) REFERENCES Pets (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Cat (name, birth_date, commands, genus_id)
VALUES ('Whiskers', '2022-02-01', 'Sit, Pounce', 2),
       ('Smudge', '2020-02-20', 'Sit, Pounce, Scratch', 2),
       ('Oliver', '2020-06-30', 'Meow, Scratch, Jump', 2);

CREATE TABLE Hamster (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20),
    birth_date DATE,
    commands VARCHAR(50),
    genus_id INT,
    FOREIGN KEY (genus_id) REFERENCES Pets (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Hamster (name, birth_date, commands, genus_id)
VALUES ('Hammy', '2021-05-10', 'Roll, Hide', 3),
       ('Peanut', '2021-08-01', 'Roll, Spin', 3);

CREATE TABLE Horse (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20),
    birth_date DATE,
    commands VARCHAR(50),
    genus_id INT,
    FOREIGN KEY (genus_id) REFERENCES PackAnimals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Horse (name, birth_date, commands, genus_id)
VALUES ('Thunder', '2021-12-01', 'Gallop, Jump', 1),
       ('Storm', '2014-05-05', 'Trot, Canter', 1),
       ('Blaze', '2016-02-29', 'Trot, Jump, Gallop', 1);

CREATE TABLE Camel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20),
    birth_date DATE,
    commands VARCHAR(50),
    genus_id INT,
    FOREIGN KEY (genus_id) REFERENCES PackAnimals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Camel (name, birth_date, commands, genus_id)
VALUES ('Sandy', '2016-11-03', 'Walk, Carry Load', 2),
       ('Coco', '2020-05-05', 'Walk, Run', 2),
       ('Dune', '2018-12-12', 'Walk, Sit', 2),
       ('Sahara', '2015-08-14', 'Walk, Run', 2);

CREATE TABLE Donkey (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20),
    birth_date DATE,
    commands VARCHAR(50),
    genus_id INT,
    FOREIGN KEY (genus_id) REFERENCES PackAnimals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Donkey (name, birth_date, commands, genus_id)
VALUES ('Daisy', '2022-01-01', 'Bray, Carry', 3),
       ('Eeyore', '2017-09-18', 'Walk, Carry Load, Bray', 3),
       ('Burro', '2022-01-23', 'Stop, Bray, Kick', 3);

-- Отключение безопасных обновлений
SET SQL_SAFE_UPDATES = 0;

-- Удаление данных из таблицы Camel
DELETE FROM Camel;

-- Объединение таблиц Horse и Donkey в HorsesAndDonkeys
CREATE TABLE HorsesAndDonkeys (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20),
    birth_date DATE,
    commands VARCHAR(50),
    genus_name VARCHAR(20),
    genus_id INT
);

INSERT INTO HorsesAndDonkeys (name, birth_date, commands, genus_name, genus_id)
SELECT name, birth_date, commands, 'Horse' AS genus_name, genus_id
FROM Horse
UNION ALL
SELECT name, birth_date, commands, 'Donkey' AS genus_name, genus_id
FROM Donkey;

-- Проверка содержимого объединенной таблицы
SELECT * FROM HorsesAndDonkeys;

-- Создание временной таблицы AnimalsTemp для всех животных
CREATE TEMPORARY TABLE AnimalsTemp AS
SELECT *, 'Dog' as genus FROM Dog
UNION
SELECT *, 'Cat' AS genus FROM Cat
UNION
SELECT *, 'Hamster' AS genus FROM Hamster
UNION
SELECT *, 'Horse' AS genus FROM Horse
UNION
SELECT *, 'Donkey' AS genus FROM Donkey;

-- Создание таблицы YoungAnimals для животных возрастом от 1 до 3 лет
CREATE TABLE YoungAnimals AS
SELECT name, birth_date, commands, genus, TIMESTAMPDIFF(MONTH, birth_date, CURDATE()) AS Age_in_month
FROM AnimalsTemp 
WHERE TIMESTAMPDIFF(MONTH, birth_date, CURDATE()) BETWEEN 12 AND 36;

-- Запрос на выборку всех молодых животных
SELECT * FROM YoungAnimals;

-- Объединение таблиц с дополнительными данными
SELECT d.name, d.birth_date, d.commands, ha.genus_name, ya.Age_in_month
FROM Dog d
LEFT JOIN YoungAnimals ya ON ya.name = d.name
LEFT JOIN Pets ha ON ha.id = d.genus_id
UNION
SELECT c.name, c.birth_date, c.commands, ha.genus_name, ya.Age_in_month
FROM Cat c
LEFT JOIN YoungAnimals ya ON ya.name = c.name
LEFT JOIN Pets ha ON ha.id = c.genus_id
UNION
SELECT h.name, h.birth_date, h.commands, ha.genus_name, ya.Age_in_month
FROM Hamster h
LEFT JOIN YoungAnimals ya ON ya.name = h.name
LEFT JOIN Pets ha ON ha.id = h.genus_id
UNION
SELECT ho.name, ho.birth_date, ho.commands, pa.genus_name, ya.Age_in_month
FROM Horse ho
LEFT JOIN YoungAnimals ya ON ya.name = ho.name
LEFT JOIN PackAnimals pa ON pa.id = ho.genus_id
UNION
SELECT do.name, do.birth_date, do.commands, pa.genus_name, ya.Age_in_month
FROM Donkey do
LEFT JOIN YoungAnimals ya ON ya.name = do.name
LEFT JOIN PackAnimals pa ON pa.id = do.genus_id;
```