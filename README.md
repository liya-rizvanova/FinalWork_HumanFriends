# Итоговая аттестация
## Информация о проекте
Необходимо организовать систему учета для питомника, в котором живут
домашние и вьючные животные.

#### 1 Используя команду cat в терминале операционной системы Linux, создать два файла Домашние животные (заполнив файл собаками, кошками, хомяками) и Вьючные животными заполнив файл Лошадьми, верблюдами и ослы), а затем объединить их. Просмотреть содержимое созданного файла. Переименовать файл, дав ему новое имя (Друзья человека).
Создание файла "Домашние животные" (Pets)
```
lr@lr-VirtualBox:~$ cat > Pets.txt
Dog
Cat
Hamster  
```
Создание файла "Вьючные животные" (PackAnimals)
```
lr@lr-VirtualBox:~$ cat > PackAnimals.txt
Horse
Camel
Donkey
```
Объединение файлов в новый файл Animals.txt
```
lr@lr-VirtualBox:~$ cat Pets.txt PackAnimals.txt > Animals.txt
```
Проверка содержимого объединенного файла
```
lr@lr-VirtualBox:~$ cat Animals.txt
Dog
Cat
Hamster
Horse
Camel
Donkey
```
Переименование файла в "Друзья человека" (HumanFriends)
```
lr@lr-VirtualBox:~$ mv Animals.txt HumanFriends.txt
```

#### 2 Создать директорию, переместить файл туда.
Создание директории
```
lr@lr-VirtualBox:~$ mkdir AnimalsDir
```
Перемещение файла в новую директорию
```
lr@lr-VirtualBox:~$ mv HumanFriends.txt AnimalsDir/
```
Проверка перемещения файла в директорию AnimalsDir
```
lr@lr-VirtualBox:~$ ls AnimalsDir
HumanFriends.txt
```

#### 3 Подключить дополнительный репозиторий MySQL. Установить любой пакет из этого репозитория.
Подключение репозитория MySQL:
```
lr@lr-VirtualBox:~$ sudo wget https://dev.mysql.com/get/mysql-apt-config_0.8.22-1_all.deb
lr@lr-VirtualBox:~$ sudo dpkg -i mysql-apt-config_0.8.22-1_all.deb
```
Установка пакета из репозитория MySQL и проверка статуса установленного пакета:
```
lr@lr-VirtualBox:~$ sudo apt update
lr@lr-VirtualBox:~$ sudo apt install mysql-server
lr@lr-VirtualBox:~$ sudo systemctl status mysql
```

#### 4 Установить и удалить deb-пакет с помощью dpkg.
Загрузка .deb пакета cowsay:
```
lr@lr-VirtualBox:~$ sudo wget http://mirrors.kernel.org/ubuntu/pool/universe/c/cowsay/cowsay_3.03+dfsg2-7_all.deb
```
Установка пакета с помощью dpkg:
```
lr@lr-VirtualBox:~$ sudo dpkg -i cowsay_3.03+dfsg2-7_all.deb
```
Проверка установки пакета cowsay:
```
lr@lr-VirtualBox:~$ cowsay "Hello, world!"

 _______________

< Hello, world! >

 ---------------

        \   ^__^

         \  (oo)\_______

            (__)\       )\/\

                ||----w |

                ||     ||

```
Удаление пакета cowsay с помощью dpkg:
```
lr@lr-VirtualBox:~$ sudo dpkg -r cowsay
```

#### 5 Выложить историю команд в терминале ubuntu
```
  1 sudo apt update
  2  cat > Pets.txt
  3  cat Pets.txt
  4  cat > PackAnimals.txt
  5  cat PackAnimals.txt
  6  cat Pets.txt PackAnimals.txt > Animals.txt
  7  cat Animals.txt
  8  mv Animals.txt HumanFriends.txt
  9  cat HumanFriends.txt
  10  ls
  11  mkdir AnimalsDir
  12  mv HumanFriends.txt AnimalsDir/
  13  ls AnimalsDir
  14  sudo apt update
  15  sudo wget https://dev.mysql.com/get/mysql-apt-config_0.8.22-1_all.deb
  16  sudo dpkg -i mysql-apt-config_0.8.22-1_all.deb
  17  sudo apt update
  18  sudo apt install mysql-server
  19  sudo systemctl status mysql
  20  sudo wget http://mirrors.kernel.org/ubuntu/pool/universe/c/cowsay/cowsay_3.03+dfsg2-7_all.deb
  21  sudo dpkg -i cowsay_3.03+dfsg2-7_all.deb
  22  cowsay "Hello, world!"
  23  sudo dpkg -r cowsay
  24  history | tail -n 24
```

#### 6 Нарисовать диаграмму, в которой есть класс родительский класс, домашние животные и вьючные животные, в составы которых в случае домашних животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные войдут: лошади, верблюды и ослы).
![ДиаграммаНаследования](ДиаграммаНаследования.png)

#### 7 В подключенном MySQL создать базу данных с названием "Human Friends".
Вход в MySQL
```
lr@lr-VirtualBox:~$ sudo mysql -u root -p
```
Создание базы данных
```
mysql> CREATE DATABASE `Human Friends`;
mysql> USE `Human Friends`;
```

#### 8 Создать таблицы с иерархией из диаграммы в БД
Создание таблицы класса Animals
```
mysql> CREATE TABLE Animals (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     type VARCHAR(20)
    -> );

mysql> INSERT INTO Animals (type)
    -> VALUES ('Pets'),
    ->        ('PackAnimals');
```
Создание таблицы Pets
```
mysql> CREATE TABLE Pets (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     genus_name VARCHAR(20),
    ->     class_id INT,
    ->     FOREIGN KEY (class_id) REFERENCES Animals (id) ON DELETE CASCADE ON UPDATE CASCADE
    -> );

mysql> INSERT INTO Pets (genus_name, class_id)
    -> VALUES ('Dog', 1),
    ->        ('Cat', 1),
    ->        ('Hamster', 1);
```
Создание таблиц Dog, Cat, Hamster, которые ссылаются на таблицу Pets и содержат информацию о каждом виде домашних животных.
```
mysql> CREATE TABLE Dog (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     name VARCHAR(20),
    ->     birth_date DATE,
    ->     commands VARCHAR(50),
    ->     genus_id INT,
    ->     FOREIGN KEY (genus_id) REFERENCES Pets (id) ON DELETE CASCADE ON UPDATE CASCADE
    -> );

mysql> CREATE TABLE Cat (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     name VARCHAR(20),
    ->     birth_date DATE,
    ->     commands VARCHAR(50),
    ->     genus_id INT,
    ->     FOREIGN KEY (genus_id) REFERENCES Pets (id) ON DELETE CASCADE ON UPDATE CASCADE
    -> );

mysql> CREATE TABLE Hamster (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     name VARCHAR(20),
    ->     birth_date DATE,
    ->     commands VARCHAR(50),
    ->     genus_id INT,
    ->     FOREIGN KEY (genus_id) REFERENCES Pets (id) ON DELETE CASCADE ON UPDATE CASCADE
    -> );
```
Создание таблицы PackAnimals
```
mysql> CREATE TABLE PackAnimals (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     genus_name VARCHAR(20),
    ->     class_id INT,
    ->     FOREIGN KEY (class_id) REFERENCES Animals (id) ON DELETE CASCADE ON UPDATE CASCADE
    -> );

mysql> INSERT INTO PackAnimals (genus_name, class_id)
    -> VALUES ('Horse', 2),
    ->        ('Camel', 2),
    ->        ('Donkey', 2);
```
Создание таблиц Horse, Camel, Donkey, которые ссылаются на таблицу PackAnimals и содержат информацию о каждом виде вьючных животных.
```
mysql> CREATE TABLE Horse (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     name VARCHAR(20),
    ->     birth_date DATE,
    ->     commands VARCHAR(50),
    ->     genus_id INT,
    ->     FOREIGN KEY (genus_id) REFERENCES PackAnimals (id) ON DELETE CASCADE ON UPDATE CASCADE
    -> );

mysql> CREATE TABLE Camel (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     name VARCHAR(20),
    ->     birth_date DATE,
    ->     commands VARCHAR(50),
    ->     genus_id INT,
    ->     FOREIGN KEY (genus_id) REFERENCES PackAnimals (id) ON DELETE CASCADE ON UPDATE CASCADE
    -> );

mysql> CREATE TABLE Donkey (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     name VARCHAR(20),
    ->     birth_date DATE,
    ->     commands VARCHAR(50),
    ->     genus_id INT,
    ->     FOREIGN KEY (genus_id) REFERENCES PackAnimals (id) ON DELETE CASCADE ON UPDATE CASCADE
    -> );
```

#### 9. Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения
Заполнение таблиц Dog, Cat, Hamster
```
mysql> INSERT INTO Dog (name, birth_date, commands, genus_id)
    -> VALUES ('Fido', '2011-10-01', 'Sit, Stay, Fetch', 1),
    ->        ('Buddy', '2021-04-01', 'Sit, Paw, Bark', 1),
    ->        ('Bella', '2017-01-01', 'Sit, Stay, Roll', 1);

mysql> INSERT INTO Cat (name, birth_date, commands, genus_id)
    -> VALUES ('Whiskers', '2022-02-01', 'Sit, Pounce', 2),
    ->        ('Smudge', '2020-02-20', 'Sit, Pounce, Scratch', 2),
    ->        ('Oliver', '2020-06-30', 'Meow, Scratch, Jump', 2);

mysql> INSERT INTO Hamster (name, birth_date, commands, genus_id)
    -> VALUES ('Hammy', '2021-05-10', 'Roll, Hide', 3),
    ->        ('Peanut', '2021-08-01', 'Roll, Spin', 3);
```
Заполнение таблиц Horse, Camel, Donkey
```
mysql> INSERT INTO Horse (name, birth_date, commands, genus_id)
    -> VALUES ('Thunder', '2021-12-01', 'Gallop, Jump', 1),
    ->        ('Storm', '2014-05-05', 'Trot, Canter', 1),
    ->        ('Blaze', '2016-02-29', 'Trot, Jump, Gallop', 1);

mysql> INSERT INTO Camel (name, birth_date, commands, genus_id)
    -> VALUES ('Sandy', '2016-11-03', 'Walk, Carry Load', 2),
    ->        ('Coco', '2020-05-05', 'Walk, Run', 2),
    ->        ('Dune', '2018-12-12', 'Walk, Sit', 2),
    ->        ('Sahara', '2015-08-14', 'Walk, Run', 2);

mysql> INSERT INTO Donkey (name, birth_date, commands, genus_id)
    -> VALUES ('Daisy', '2022-01-01', 'Bray, Carry', 3),
    ->        ('Eeyore', '2017-09-18', 'Walk, Carry Load, Bray', 3),
    ->        ('Burro', '2022-01-23', 'Stop, Bray, Kick', 3);
```

#### 10 Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.
Удаление записей из таблицы Camel
```
mysql> DELETE FROM Camel;
```
Создание объединенной таблицы HorsesAndDonkeys
```
mysql> CREATE TABLE HorsesAndDonkeys (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     name VARCHAR(20),
    ->     birth_date DATE,
    ->     commands VARCHAR(50),
    ->     genus_name VARCHAR(20),
    ->     genus_id INT
    -> );

mysql> INSERT INTO HorsesAndDonkeys (name, birth_date, commands, genus_name, genus_id)
    -> SELECT name, birth_date, commands, 'Horse' AS genus_name, genus_id
    -> FROM Horse;

mysql> INSERT INTO HorsesAndDonkeys (name, birth_date, commands, genus_name, genus_id)
    -> SELECT name, birth_date, commands, 'Donkey' AS genus_name, genus_id
    -> FROM Donkey;

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
```

#### 11 Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице
```
mysql> -- Создание таблицы молодых животных

mysql> CREATE TABLE YoungAnimals AS
    -> SELECT name, birth_date, commands, genus, TIMESTAMPDIFF(MONTH, birth_date, CURDATE()) AS Age_in_month
    -> FROM AnimalsTemp
    -> WHERE birth_date BETWEEN ADDDATE(curdate(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);

mysql> SELECT * FROM YoungAnimals;
+----------+------------+------------------+--------+--------------+
| name     | birth_date | commands         | genus  | Age_in_month |
+----------+------------+------------------+--------+--------------+
| Whiskers | 2022-02-01 | Sit, Pounce      | Cat    |           32 |
| Thunder  | 2021-12-01 | Gallop, Jump     | Horse  |           34 |
| Daisy    | 2022-01-01 | Bray, Carry      | Donkey |           33 |
| Burro    | 2022-01-23 | Stop, Bray, Kick | Donkey |           33 |
+----------+------------+------------------+--------+--------------+
```

#### 12 Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам.
```
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
```
### Далее был создан проект HumanFriends на Java, в котором был реализован функционал, требуемый в пунктах 13-15
#### 13 Создать класс с инкапсуляцией методов и наследованием по диаграмме.
#### 14 Написать программу, имитирующую работу реестра домашних животных.
В программе должен быть реализован следующий функционал:

14.1 Завести новое животное

14.2 определять животное в правильный класс

14.3 увидеть список команд, которое выполняет животное

14.4 обучить животное новым командам

14.5 Реализовать навигацию по меню

#### 15 Создайте класс Счетчик, у которого есть метод add(), увеличивающий значение внутренней int переменной на 1 при нажатии “Завести новое животное” Сделайте так, чтобы с объектом такого типа можно было работать в блоке try-with-resources. Нужно бросить исключение, если работа с объектом типа счетчик была не в ресурсном try и/или ресурс остался открыт. Значение считать в ресурсе try, если при заведении животного заполнены все поля.