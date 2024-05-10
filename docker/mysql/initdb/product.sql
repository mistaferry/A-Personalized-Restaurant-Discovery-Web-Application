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


create table if not exists user(
    user_id bigint,
    username varchar(256),
    email varchar(256),
    primary key (user_id)
);

create table if not exists review
(
    review_id bigint,
    text text,
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
    type_id   bigint auto_increment,
    name varchar(256),
    primary key (type_id)
);

create table if not exists dish
(
    dish_id      bigint auto_increment,
    name         varchar(256),
    price        double,
    dish_type    bigint,
    cuisine_type varchar(256),
    primary key (dish_id),
    foreign key (dish_type) references dish_type (type_id)
);

create table if not exists ingredient
(
    ingredient_id bigint auto_increment,
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

INSERT INTO dish_type (name)
VALUES ('Салат'),
       ('Суп'),
       ('Піца'),
       ('Основна страва'),
       ('Десерт'),
       ('Напій'),
       ('Закуска'),
       ('Паста'),
       ('Рисова страва'),
       ('Локшина');

INSERT INTO dish (name, price, dish_type, cuisine_type)
VALUES
-- Українська кухня
('Вареники з картоплею', 80.0, 4, 'Українська'),
('Голубці з м’ясом', 90.0, 4, 'Українська'),

-- Американська кухня
('Гамбургер', 150.0, 4, 'Американська'),
('Хот-дог', 70.0, 7, 'Американська'),

-- Італійська кухня
('Карбонара', 160.0, 8, 'Італійська'),
('Капрезе', 120.0, 1, 'Італійська'),

-- Французька кухня
('Круасан', 60.0, 7, 'Французька'),
('Рататуй', 140.0, 4, 'Французька'),

-- Німецька кухня
('Братвурст', 110.0, 7, 'Німецька'),
('Шницель', 170.0, 4, 'Німецька'),

-- Азійська кухня
('Суші', 200.0, 7, 'Азійська'),
('Тайський зелений карі', 190.0, 2, 'Азійська'),

-- Грецька кухня
('Грецький салат', 110.0, 1, 'Грецька'),
('Мусака', 150.0, 4, 'Грецька'),

-- Мексиканська кухня
('Тако', 80.0, 7, 'Мексиканська'),
('Енчілада', 130.0, 4, 'Мексиканська'),

-- Веганська кухня
('Веганський бургер', 90.0, 4, 'Веган'),
('Веганський салат', 70.0, 1, 'Веган');

INSERT INTO ingredient (name)
VALUES
    ('Картопля'),
    ('М\'ясо'),
('Тісто'),
('Капуста'),
('Гірчиця'),
('Кетчуп'),
('Курка'),
('Сир Пармезан'),
('Сало'),
('Оливки'),
('Суші рис'),
('Морепродукти'),
('Тофу'),
('Салатний мікс'),
('Тортилья'),
('Авакадо'),
('Томат'),
('Перець'),
('Лайм'),
('Чилі'),
('Борошно'),
('Капуста броколі'),
('Гриби'),
('Баклажан'),
('Кабачок'),
('Петрушка'),
('Часник'),
('Зелена цибуля'),
('Соєвий соус'),
('Суміш спецій');
INSERT INTO dish_ingredient (dish_id, ingredient_id)
VALUES
-- Українська кухня
(1, 1),  -- Вареники з картоплею - Картопля
(2, 2),  -- Голубці з м’ясом - М'ясо
(2, 4),  -- Голубці з м’ясом - Капуста

-- Американська кухня
(3, 2),  -- Гамбургер - М'ясо
(3, 3),  -- Гамбургер - Тісто
(4, 2),  -- Хот-дог - М'ясо
(4, 3),  -- Хот-дог - Тісто
(4, 5),  -- Хот-дог - Гірчиця
(4, 6),  -- Хот-дог - Кетчуп

-- Італійська кухня
(5, 3),  -- Карбонара - Тісто
(5, 8),  -- Карбонара - Сир Пармезан
(6, 3),  -- Капрезе - Тісто
(6, 17), -- Капрезе - Томат

-- Французька кухня
(7, 3),  -- Круасан - Тісто
(8, 24), -- Рататуй - Баклажан
(8, 25), -- Рататуй - Кабачок
(8, 23), -- Рататуй - Гриби

-- Німецька кухня
(9, 2),  -- Братвурст - М'ясо
(10, 2),  -- Шницель - М'ясо
(10, 21), -- Шницель - Борошно

-- Азійська кухня
(11, 11), -- Суші - Суші рис
(11, 12), -- Суші - Морепродукти
(12, 13), -- Тайський зелений карі - Тофу
(12, 21), -- Тайський зелений карі - Борошно

-- Грецька кухня
(13, 14), -- Грецький салат - Салатний мікс
(13, 10), -- Грецький салат - Оливки
(14, 26), -- Мусака - Петрушка

-- Мексиканська кухня
(15, 16), -- Тако - Тортилья
(15, 18), -- Тако - Перець
(16, 14), -- Енчілада - Салатний мікс
(16, 17), -- Енчілада - Томат

-- Веганська кухня
(17, 2),  -- Веганський бургер - М'ясо
(17, 13), -- Веганський бургер - Тофу
(18, 14), -- Веганський салат - Салатний мікс
(18, 10); -- Веганський салат - Оливки
