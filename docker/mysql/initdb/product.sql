ALTER DATABASE db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

create table if not exists category
(
    category_id bigint,
    name        varchar(256),
    PRIMARY KEY (category_id)
);

create table if not exists restaurant
(
    restaurant_id bigint,
    place_id      varchar(256),
    name          varchar(256),
    address       varchar(256),
    latitude      float,
    longitude     float,
    rating        float,
    price_level   int,
    website       varchar(256),
    photo_ref     varchar(256),
    cuisine_type  varchar(256),
    PRIMARY KEY (restaurant_id)
);

CREATE TABLE IF NOT EXISTS restaurant_info_en (
    info_id        BIGINT,
    restaurant_id  BIGINT,
    name_en        VARCHAR(256),
    address_en     VARCHAR(256),
    PRIMARY KEY (info_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id)
);

create table if not exists role(
    role_id bigint auto_increment,
    name varchar(256),
    primary key (role_id)
);

INSERT INTO db.role (role_id, name) VALUES (1, 'user');
INSERT INTO db.role (role_id, name) VALUES (2, 'admin');


create table if not exists user(
    user_id bigint,
    username varchar(256),
    email varchar(256),
    picture varchar(256),
    role_id bigint,
    primary key (user_id)
);

ALTER TABLE user
    ADD CONSTRAINT fk_role
        FOREIGN KEY (role_id) REFERENCES role (role_id);

create table if not exists review
(
    review_id bigint,
    text longtext,
    time timestamp,
    restaurant_id bigint,
    user_id bigint,
    primary key (review_id),
    foreign key (restaurant_id) references restaurant (restaurant_id),
    foreign key (user_id) references user (user_id)
);

create table if not exists restaurant_category
(
    category_id   bigint,
    restaurant_id bigint,
    foreign key (category_id) references category (category_id),
    foreign key (restaurant_id) references restaurant (restaurant_id)
);

create table if not exists dish_type
(
    type_id   bigint AUTO_INCREMENT,
    name varchar(256),
    primary key (type_id)
);

create table if not exists dish
(
    dish_id      bigint AUTO_INCREMENT,
    name         varchar(256),
    price        double,
    dish_type    bigint,
    cuisine_type varchar(256),
    primary key (dish_id),
    foreign key (dish_type) references dish_type (type_id)
);

create table if not exists ingredient
(
    ingredient_id bigint AUTO_INCREMENT,
    name          varchar(256),
    primary key (ingredient_id)
);

create table if not exists dish_ingredient
(
    dish_id       bigint,
    ingredient_id bigint,
    foreign key (dish_id) references dish (dish_id),
    foreign key (ingredient_id) references ingredient (ingredient_id)
);

create table if not exists restaurant_dish
(
    dish_id       bigint,
    restaurant_id bigint,
    foreign key (dish_id) references dish (dish_id),
    foreign key (restaurant_id) references restaurant (restaurant_id)
);
#
# INSERT INTO dish_type (type_id, name)
# VALUES (1, 'Салат'),
#        (2, 'Суп'),
#        (3, 'Піца'),
#        (4, 'Основна страва'),
#        (5, 'Десерт'),
#        (6, 'Напій'),
#        (7, 'Закуска'),
#        (8, 'Паста'),
#        (9, 'Рисова страва'),
#        (10, 'Локшина');
#
# INSERT INTO dish (name, price, dish_type, cuisine_type)
# VALUES
# ('Вареники з картоплею', 80.0, 4, 'Українська'),
# ('Голубці з м’ясом', 90.0, 4, 'Українська'),
# ('Гамбургер', 150.0, 4, 'Американська'),
# ('Хот-дог', 70.0, 7, 'Американська'),
# ('Карбонара', 160.0, 8, 'Італійська'),
# ('Капрезе', 120.0, 1, 'Італійська'),
# ('Круасан', 60.0, 7, 'Французька'),
# ('Рататуй', 140.0, 4, 'Французька'),
# ('Братвурст', 110.0, 7, 'Німецька'),
# ('Шницель', 170.0, 4, 'Німецька'),
# ('Суші', 200.0, 7, 'Азійська'),
# ('Тайський зелений карі', 190.0, 2, 'Азійська'),
# ('Грецький салат', 110.0, 1, 'Грецька'),
# ('Мусака', 150.0, 4, 'Грецька'),
# ('Тако', 80.0, 7, 'Мексиканська'),
# ('Енчілада', 130.0, 4, 'Мексиканська'),
# ('Веганський бургер', 90.0, 4, 'Веганська'),
# ('Веганський салат', 70.0, 1, 'Веганська'),
# ('Гречаний салат з куркою', 90.0, 1, 'Українська'),
# ('Беконовий сендвіч', 70.0, 7, 'Американська'),
# ('Різотто з грибами', 120.0, 8, 'Італійська'),
# ('Французький рагу', 140.0, 4, 'Французька'),
# ('Німецькі ковбаски', 110.0, 7, 'Німецька'),
# ('Тайський суп Том Ям', 100.0, 2, 'Азійська'),
# ('Грецький мусака', 150.0, 4, 'Грецька'),
# ('Мексиканські фахітас', 130.0, 7, 'Мексиканська'),
# ('Веганське суші', 90.0, 7, 'Веганська'),
# ('Вареники з капустою', 90.0, 4, 'Українська'),
# ('Салат Олів’є', 70.0, 1, 'Українська'),
# ('Лазанья', 250.0, 4, 'Італійська'),
# ('Пад тай', 180.0, 10, 'Азійська');
#
# INSERT INTO ingredient (name)
# VALUES
#     ('Картопля'),
#     ('М’ясо'),
#     ('Тісто'),
#     ('Капуста'),
#     ('Гірчиця'),
#     ('Кетчуп'),
#     ('Курка'),
#     ('Сир Пармезан'),
#     ('Сало'),
#     ('Оливки'),
#     ('Суші рис'),
#     ('Морепродукти'),
#     ('Тофу'),
#     ('Салатний мікс'),
#     ('Тортилья'),
#     ('Авакадо'),
#     ('Томат'),
#     ('Перець'),
#     ('Лайм'),
#     ('Чилі'),
#     ('Борошно'),
#     ('Капуста броколі'),
#     ('Гриби'),
#     ('Баклажан'),
#     ('Кабачок'),
#     ('Петрушка'),
#     ('Часник'),
#     ('Зелена цибуля'),
#     ('Соєвий соус'),
#     ('Суміш спецій'),
#     ('Яйце'),
#     ('Глютен'),
#     ('Молоко'),
#     ('Горіхи'),
#     ('Сир Фета'),
#     ('Риба'),
#     ('Креветки'),
#     ('Мигдаль'),
#     ('Салат'),
#     ('Куряче філе'),
#     ('Бекон'),
#     ('Сир'),
#     ('Томатний соус'),
#     ('Рис'),
#     ('Локшина'),
#     ('Цукор'),
#     ('Какао');
#
# INSERT INTO dish_ingredient (dish_id, ingredient_id)
# VALUES (1, 1),
# (1, 3),
# (2, 2),
# (2, 4),
# (2, 6),
# (3, 2),
# (3, 3),
# (3, 6),
# (3, 39),
# (3, 41),
# (3, 42),
# (3, 43),
# (3, 17),
# (4, 2),
# (4, 3),
# (4, 5),
# (4, 6),
# (5, 3),
# (5, 8),
# (5, 32),
# (6, 3),
# (6, 17),
# (6, 35),
# (7, 3),
# (7, 32),
# (7, 31),
# (8, 24),
# (8, 25),
# (8, 23),
# (8, 32),
# (9, 2),
# (10, 2),
# (10, 21),
# (20, 32),
# (11, 11),
# (11, 36),
# (11, 12),
# (11, 16),
# (11, 29),
# (11, 35),
# (12, 13),
# (12, 21),
# (13, 14),
# (13, 10),
# (13, 35),
# (14, 26),
# (14, 2),
# (14, 32),
# (14, 3),
# (15, 16),
# (15, 15),
# (15, 18),
# (15, 2),
# (15, 3),
# (15, 32),
# (16, 14),
# (16, 15),
# (16, 17),
# (16, 32),
# (17, 2),
# (17, 13),
# (18, 14),
# (18, 10),
# (19, 1),
# (19, 2),
# (20, 3),
# (20, 13),
# (21, 33),
# (21, 6),
# (22, 14),
# (22, 35),
# (22, 37),
# (23, 19),
# (23, 2),
# (23, 9),
# (24, 22),
# (22, 39),
# (25, 44),
# (25, 2),
# (25, 3),
# (26, 25),
# (26, 28),
# (26, 27),
# (27, 21),
# (27, 24),
# (27, 16),
# (28, 4),
# (28, 3),
# (29,1),
# (29,2),
# (29,4),
# (29,31),
# (29,33),
# (29,28),
# (29,32),
# (29,30),
# (30, 2),
# (30, 3),
# (30, 17),
# (30, 20),
# (31,45),
# (31,7),
# (31,40),
# (31,2),
# (31,45),
# (31,30);
#
# insert into user values (0, 'googleUser', 'google.user@gmail.com', null ,1)
