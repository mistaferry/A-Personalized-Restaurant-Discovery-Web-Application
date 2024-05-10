ALTER DATABASE db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

create table if not exists product
(
    product_id bigint,
    name       varchar(256),
    price      decimal(10, 2),
    brand      varchar(256),
    category   varchar(100)
);
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (1, 'Awesome Widget', 19.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (2, 'Super Gear', 12.50, 'ABC Company', 'Appliances');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (3, 'Mega Gadget', 39.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (4, 'Turbo Gizmo', 29.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (5, 'Power Device', 24.99, 'PQR Inc.', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (6, 'Ultimate Appliance', 99.99, 'ABC Company', 'Appliances');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (7, 'Lightning Widget', 14.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (8, 'Smart Gizmo', 49.99, 'PQR Inc.', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (9, 'Fantastic Tool', 9.99, 'ABC Company', 'Tools');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (10, 'Deluxe Gadget', 59.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (11, 'Premium Appliance', 149.99, 'ABC Company', 'Appliances');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (12, 'Mega Widget', 34.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (13, 'Pro Device', 79.99, 'PQR Inc.', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (14, 'Handy Tool', 12.99, 'ABC Company', 'Tools');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (15, 'Turbo Gadget', 69.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (16, 'Super Appliance', 199.99, 'ABC Company', 'Appliances');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (17, 'Giga Gizmo', 44.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (18, 'Smart Device', 89.99, 'PQR Inc.', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (19, 'Amazing Tool', 14.99, 'ABC Company', 'Tools');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (20, 'Ultra Widget', 79.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (21, 'Pro Appliance', 249.99, 'ABC Company', 'Appliances');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (22, 'Power Gizmo', 59.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (23, 'Advanced Device', 129.99, 'PQR Inc.', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (24, 'Master Tool', 17.99, 'ABC Company', 'Tools');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (25, 'Thunder Widget', 94.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (26, 'Elite Appliance', 299.99, 'ABC Company', 'Appliances');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (27, 'Lightning Gizmo', 74.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (28, 'Smart Widget', 149.99, 'PQR Inc.', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (29, 'Ultimate Tool', 19.99, 'ABC Company', 'Tools');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (30, 'Mega Gadget', 109.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (31, 'Premium Appliance', 349.99, 'ABC Company', 'Appliances');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (32, 'Turbo Gizmo', 89.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (33, 'Super Device', 179.99, 'PQR Inc.', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (34, 'Fantastic Tool', 24.99, 'ABC Company', 'Tools');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (35, 'Deluxe Widget', 124.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (36, 'Pro Appliance', 399.99, 'ABC Company', 'Appliances');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (37, 'Power Gizmo', 99.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (38, 'Smart Device', 209.99, 'PQR Inc.', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (39, 'Handy Tool', 29.99, 'ABC Company', 'Tools');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (40, 'Thunder Widget', 139.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (41, 'Elite Appliance', 499.99, 'ABC Company', 'Appliances');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (42, 'Lightning Gizmo', 119.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (43, 'Smart Widget', 259.99, 'PQR Inc.', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (44, 'Ultimate Tool', 34.99, 'ABC Company', 'Tools');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (45, 'Mega Gadget', 159.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (46, 'Premium Appliance', 599.99, 'ABC Company', 'Appliances');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (47, 'Turbo Gizmo', 149.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (48, 'Super Device', 249.99, 'PQR Inc.', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (49, 'Fantastic Tool', 39.99, 'ABC Company', 'Tools');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (50, 'Deluxe Widget', 199.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (51, 'Pro Appliance', 699.99, 'ABC Company', 'Appliances');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (52, 'Power Gizmo', 179.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (53, 'Smart Device', 309.99, 'PQR Inc.', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (54, 'Handy Tool', 49.99, 'ABC Company', 'Tools');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (55, 'Thunder Widget', 229.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (56, 'Elite Appliance', 799.99, 'ABC Company', 'Appliances');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (57, 'Lightning Gizmo', 199.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (58, 'Smart Widget', 359.99, 'PQR Inc.', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (59, 'Ultimate Tool', 49.99, 'ABC Company', 'Tools');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (60, 'Mega Gadget', 259.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (61, 'Premium Appliance', 899.99, 'ABC Company', 'Appliances');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (62, 'Turbo Gizmo', 199.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (63, 'Super Device', 369.99, 'PQR Inc.', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (64, 'Fantastic Tool', 59.99, 'ABC Company', 'Tools');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (65, 'Deluxe Widget', 249.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (66, 'Pro Appliance', 999.99, 'ABC Company', 'Appliances');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (67, 'Power Gizmo', 229.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (68, 'Smart Device', 409.99, 'PQR Inc.', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (69, 'Handy Tool', 69.99, 'ABC Company', 'Tools');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (70, 'Thunder Widget', 299.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (71, 'Elite Appliance', 199.99, 'ABC Company', 'Appliances');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (72, 'Lightning Gizmo', 249.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (73, 'Smart Widget', 459.99, 'PQR Inc.', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (74, 'Ultimate Tool', 69.99, 'ABC Company', 'Tools');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (75, 'Mega Gadget', 299.99, 'XYZ Corporation', 'Electronics');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (76, 'Premium Appliance', 199.99, 'ABC Company', 'Appliances');
INSERT INTO db.product (product_id, name, price, brand, category)
VALUES (77, 'Turbo Gizmo', 249.99, 'XYZ Corporation', 'Electronics');

create table if not exists category
(
    category_id bigint,
    name        varchar(256),
    PRIMARY KEY (category_id)
);

create table if not exists restaurant
(
    restaurant_id bigint,
    place_id varchar(256),
    name          varchar(256),
    address       varchar(256),
    latitude      float,
    longitude     float,
    rating       float,
    price_level int,
    website varchar(256),
    photo_ref    varchar(256),
    cuisine_type varchar(256),
    PRIMARY KEY (restaurant_id)
);

create table if not exists restaurant_category
(
    category_id   bigint,
    restaurant_id bigint,
    foreign key (category_id) references category (category_id),
    foreign key (restaurant_id) references restaurant (restaurant_id)
);

create table if not exists dish_type(
    id int primary key auto_increment,
    name varchar(256)
);

create table if not exists dish(
    dish_id int primary key auto_increment,
    name varchar(256),
    price double,
    dish_type int,
    cuisine_type varchar(256),
    foreign key (dish_type) references dish_type (id)
);

create table if not exists ingredient(
   ingredient_id int primary key auto_increment,
    name varchar(256)
);

create table if not exists dish_ingredient
(
    dish_id   int,
    ingredient_id int,
    foreign key (dish_id) references dish (dish_id),
    foreign key (ingredient_id) references ingredient (ingredient_id)
);

INSERT INTO dish_type (name) VALUES ('Soup'), ('Main Course'), ('Side Dish'), ('Dessert'), ('Appetizer'), ('Beverage'),('Burger'), ('Sandwich'), ('Salad'), ('Steak'), ('Pasta');

INSERT INTO dish (name, price, dish_type, cuisine_type)
VALUES
    ('Borscht', 8.50, 1, 'Ukrainian'),
    ('Holubtsi', 12.00, 2, 'Ukrainian'),
    ('Varenyky', 10.00, 2, 'Ukrainian'),
    ('Syrniki', 7.50, 4, 'Ukrainian'),
    ('Kutia', 6.00, 4, 'Ukrainian'),
    ('Olivier Salad', 9.00, 3, 'Ukrainian'),
    ('Kholodets', 8.00, 1, 'Ukrainian'),
    ('Chicken Kiev', 14.00, 2, 'Ukrainian'),
    ('Kasha', 6.50, 3, 'Ukrainian'),
    ('Medivnyk', 8.50, 4, 'Ukrainian'),
    ('Kompot', 5.00, 6, 'Ukrainian'),
    ('Cheeseburger', 12.00, 7, 'American'),
    ('Philly Cheesesteak', 13.00, 8, 'American'),
    ('Caesar Salad', 9.50, 9, 'American'),
    ('Mac and Cheese', 8.00, 11, 'American'),
    ('Grilled Chicken Sandwich', 11.50, 8, 'American'),
    ('Ribeye Steak', 22.00, 10, 'American'),
    ('BBQ Ribs', 18.00, 10, 'American'),
    ('Buffalo Wings', 10.50, 7, 'American'),
    ('Pancakes', 7.50, 4, 'American'),
    ('Apple Pie', 6.50, 4, 'American'),
    ('Spaghetti Carbonara', 12.50, 11, 'Italian'),  -- Pasta
    ('Lasagna', 13.50, 11, 'Italian'),  -- Pasta
    ('Risotto', 14.00, 2, 'Italian'),  -- Main Course
    ('Margherita Pizza', 11.00, 2, 'Italian'),  -- Main Course
    ('Minestrone Soup', 8.50, 1, 'Italian'),  -- Soup
    ('Bruschetta', 7.50, 5, 'Italian'),  -- Appetizer
    ('Tiramisu', 6.50, 4, 'Italian'),  -- Dessert
    ('Panna Cotta', 6.00, 4, 'Italian'),  -- Dessert
    ('Prosciutto e Melone', 9.00, 5, 'Italian'),  -- Appetizer
    ('Caprese Salad', 8.50, 9, 'Italian'),
    ('Sushi', 15.00, 8, 'Asian'),  -- Main Course
    ('Pad Thai', 12.50, 11, 'Asian'),  -- Pasta/Main Course
    ('Tom Yum Soup', 9.00, 1, 'Asian'),  -- Soup
    ('Spring Rolls', 7.00, 5, 'Asian'),  -- Appetizer
    ('Dumplings', 8.50, 7, 'Asian'),  -- Side Dish/Main Course
    ('Miso Soup', 5.50, 1, 'Asian'),  -- Soup
    ('Teriyaki Chicken', 13.50, 2, 'Asian'),  -- Main Course
    ('Tempura', 10.00, 5, 'Asian'),  -- Appetizer
    ('Matcha Ice Cream', 6.00, 4, 'Asian'),  -- Dessert
    ('Bubble Tea', 4.50, 6, 'Asian');  -- Salad;

INSERT INTO ingredient (name) VALUES
  ('Beets'), ('Cabbage'), ('Potatoes'), ('Poppy Seeds'), ('Honey'),
  ('Meat'), ('Rice'), ('Vegetables'), ('Sugar'), ('Garlic'), ('Bread Crumbs'), ('Buckwheat'),
  ('Fruit'), ('Gelatin'), ('Cheese'), ('Lettuce'), ('Tomato'), ('Beef'), ('Onions'), ('Parmesan'),
  ('Croutons'), ('Butter'), ('Cream'), ('Pasta'), ('Pork'), ('BBQ Sauce'),
  ('Flour'), ('Baking Powder'), ('Milk'), ('Syrup'), ('Apples'), ('Basil'), ('Wine'), ('Nori'), ('Seaweed'), ('Fish'), ('Soy Sauce'), ('Vinegar'),
  ('Shrimp'), ('Tofu'), ('Noodles'), ('Peanuts'), ('Lime'), ('Chicken'),
  ('Mushrooms'), ('Lemongrass'), ('Cilantro'), ('Chili'), ('Mint'), ('Carrots'),
  ('Green Onions'), ('Eggs'), ('Matcha'), ('Green Tea'), ('Milk Tea'),
  ('Tapioca Pearls'), ('Tempura Batter');

-- Insert dish-ingredient relationships
INSERT INTO dish_ingredient (dish_id, ingredient_id)
VALUES
((SELECT dish_id FROM dish WHERE name = 'Borscht'), (SELECT ingredient_id FROM ingredient WHERE name = 'Beets')),
((SELECT dish_id FROM dish WHERE name = 'Borscht'), (SELECT ingredient_id FROM ingredient WHERE name = 'Meat')),
((SELECT dish_id FROM dish WHERE name = 'Borscht'), (SELECT ingredient_id FROM ingredient WHERE name = 'Vegetables')),
((SELECT dish_id FROM dish WHERE name = 'Holubtsi'), (SELECT ingredient_id FROM ingredient WHERE name = 'Cabbage')),
((SELECT dish_id FROM dish WHERE name = 'Holubtsi'), (SELECT ingredient_id FROM ingredient WHERE name = 'Meat')),
((SELECT dish_id FROM dish WHERE name = 'Holubtsi'), (SELECT ingredient_id FROM ingredient WHERE name = 'Rice')),
((SELECT dish_id FROM dish WHERE name = 'Varenyky'), (SELECT ingredient_id FROM ingredient WHERE name = 'Potatoes')),
((SELECT dish_id FROM dish WHERE name = 'Varenyky'), (SELECT ingredient_id FROM ingredient WHERE name = 'Cheese')),
((SELECT dish_id FROM dish WHERE name = 'Syrniki'), (SELECT ingredient_id FROM ingredient WHERE name = 'Cheese')),
((SELECT dish_id FROM dish WHERE name = 'Syrniki'), (SELECT ingredient_id FROM ingredient WHERE name = 'Flour')),
((SELECT dish_id FROM dish WHERE name = 'Syrniki'), (SELECT ingredient_id FROM ingredient WHERE name = 'Sugar')),
((SELECT dish_id FROM dish WHERE name = 'Syrniki'), (SELECT ingredient_id FROM ingredient WHERE name = 'Eggs')),
((SELECT dish_id FROM dish WHERE name = 'Kutia'), (SELECT ingredient_id FROM ingredient WHERE name = 'Poppy Seeds')),
((SELECT dish_id FROM dish WHERE name = 'Kutia'), (SELECT ingredient_id FROM ingredient WHERE name = 'Honey')),
((SELECT dish_id FROM dish WHERE name = 'Olivier Salad'), (SELECT ingredient_id FROM ingredient WHERE name = 'Vegetables')),
((SELECT dish_id FROM dish WHERE name = 'Olivier Salad'), (SELECT ingredient_id FROM ingredient WHERE name = 'Meat')),
((SELECT dish_id FROM dish WHERE name = 'Kholodets'), (SELECT ingredient_id FROM ingredient WHERE name = 'Meat')),
((SELECT dish_id FROM dish WHERE name = 'Kholodets'), (SELECT ingredient_id FROM ingredient WHERE name = 'Garlic')),
((SELECT dish_id FROM dish WHERE name = 'Kholodets'), (SELECT ingredient_id FROM ingredient WHERE name = 'Carrots')),
((SELECT dish_id FROM dish WHERE name = 'Kholodets'), (SELECT ingredient_id FROM ingredient WHERE name = 'Onions')),
((SELECT dish_id FROM dish WHERE name = 'Kholodets'), (SELECT ingredient_id FROM ingredient WHERE name = 'Gelatin')),
((SELECT dish_id FROM dish WHERE name = 'Chicken Kiev'), (SELECT ingredient_id FROM ingredient WHERE name = 'Chicken')),
((SELECT dish_id FROM dish WHERE name = 'Chicken Kiev'), (SELECT ingredient_id FROM ingredient WHERE name = 'Butter')),
((SELECT dish_id FROM dish WHERE name = 'Chicken Kiev'), (SELECT ingredient_id FROM ingredient WHERE name = 'Bread Crumbs')),
((SELECT dish_id FROM dish WHERE name = 'Kasha'), (SELECT ingredient_id FROM ingredient WHERE name = 'Buckwheat')),
((SELECT dish_id FROM dish WHERE name = 'Medivnyk'), (SELECT ingredient_id FROM ingredient WHERE name = 'Honey')),
((SELECT dish_id FROM dish WHERE name = 'Medivnyk'), (SELECT ingredient_id FROM ingredient WHERE name = 'Flour')),
((SELECT dish_id FROM dish WHERE name = 'Medivnyk'), (SELECT ingredient_id FROM ingredient WHERE name = 'Sugar')),
((SELECT dish_id FROM dish WHERE name = 'Medivnyk'), (SELECT ingredient_id FROM ingredient WHERE name = 'Eggs')),
((SELECT dish_id FROM dish WHERE name = 'Kompot'), (SELECT ingredient_id FROM ingredient WHERE name = 'Fruit')),
((SELECT dish_id FROM dish WHERE name = 'Cheeseburger'), (SELECT ingredient_id FROM ingredient WHERE name = 'Beef')),
((SELECT dish_id FROM dish WHERE name = 'Cheeseburger'), (SELECT ingredient_id FROM ingredient WHERE name = 'Cheese')),
((SELECT dish_id FROM dish WHERE name = 'Cheeseburger'), (SELECT ingredient_id FROM ingredient WHERE name = 'Lettuce')),
((SELECT dish_id FROM dish WHERE name = 'Cheeseburger'), (SELECT ingredient_id FROM ingredient WHERE name = 'Tomato')),
((SELECT dish_id FROM dish WHERE name = 'Philly Cheesesteak'), (SELECT ingredient_id FROM ingredient WHERE name = 'Beef')),
((SELECT dish_id FROM dish WHERE name = 'Philly Cheesesteak'), (SELECT ingredient_id FROM ingredient WHERE name = 'Cheese')),
((SELECT dish_id FROM dish WHERE name = 'Philly Cheesesteak'), (SELECT ingredient_id FROM ingredient WHERE name = 'Onions')),
((SELECT dish_id FROM dish WHERE name = 'Caesar Salad'), (SELECT ingredient_id FROM ingredient WHERE name = 'Lettuce')),
((SELECT dish_id FROM dish WHERE name = 'Caesar Salad'), (SELECT ingredient_id FROM ingredient WHERE name = 'Parmesan')),
((SELECT dish_id FROM dish WHERE name = 'Caesar Salad'), (SELECT ingredient_id FROM ingredient WHERE name = 'Croutons')),
((SELECT dish_id FROM dish WHERE name = 'Mac and Cheese'), (SELECT ingredient_id FROM ingredient WHERE name = 'Pasta')),
((SELECT dish_id FROM dish WHERE name = 'Mac and Cheese'), (SELECT ingredient_id FROM ingredient WHERE name = 'Cheese')),
((SELECT dish_id FROM dish WHERE name = 'Grilled Chicken Sandwich'), (SELECT ingredient_id FROM ingredient WHERE name = 'Chicken')),
((SELECT dish_id FROM dish WHERE name = 'Grilled Chicken Sandwich'), (SELECT ingredient_id FROM ingredient WHERE name = 'Lettuce')),
((SELECT dish_id FROM dish WHERE name = 'Grilled Chicken Sandwich'), (SELECT ingredient_id FROM ingredient WHERE name = 'Tomato')),
((SELECT dish_id FROM dish WHERE name = 'Ribeye Steak'), (SELECT ingredient_id FROM ingredient WHERE name = 'Beef')),
((SELECT dish_id FROM dish WHERE name = 'BBQ Ribs'), (SELECT ingredient_id FROM ingredient WHERE name = 'Pork')),
((SELECT dish_id FROM dish WHERE name = 'BBQ Ribs'), (SELECT ingredient_id FROM ingredient WHERE name = 'BBQ Sauce')),
((SELECT dish_id FROM dish WHERE name = 'Buffalo Wings'), (SELECT ingredient_id FROM ingredient WHERE name = 'Chicken')),
((SELECT dish_id FROM dish WHERE name = 'Pancakes'), (SELECT ingredient_id FROM ingredient WHERE name = 'Flour')),
((SELECT dish_id FROM dish WHERE name = 'Pancakes'), (SELECT ingredient_id FROM ingredient WHERE name = 'Eggs')),
((SELECT dish_id FROM dish WHERE name = 'Pancakes'), (SELECT ingredient_id FROM ingredient WHERE name = 'Milk')),
((SELECT dish_id FROM dish WHERE name = 'Pancakes'), (SELECT ingredient_id FROM ingredient WHERE name = 'Syrup')),
((SELECT dish_id FROM dish WHERE name = 'Apple Pie'), (SELECT ingredient_id FROM ingredient WHERE name = 'Apples')),
((SELECT dish_id FROM dish WHERE name = 'Apple Pie'), (SELECT ingredient_id FROM ingredient WHERE name = 'Flour')),
((SELECT dish_id FROM dish WHERE name = 'Apple Pie'), (SELECT ingredient_id FROM ingredient WHERE name = 'Sugar')),
((SELECT dish_id FROM dish WHERE name = 'Apple Pie'), (SELECT ingredient_id FROM ingredient WHERE name = 'Butter')),
((SELECT dish_id FROM dish WHERE name = 'Spaghetti Carbonara'), (SELECT ingredient_id FROM ingredient WHERE name = 'Pasta')),
((SELECT dish_id FROM dish WHERE name = 'Spaghetti Carbonara'), (SELECT ingredient_id FROM ingredient WHERE name = 'Cheese')),
((SELECT dish_id FROM dish WHERE name = 'Spaghetti Carbonara'), (SELECT ingredient_id FROM ingredient WHERE name = 'Eggs')),
((SELECT dish_id FROM dish WHERE name = 'Spaghetti Carbonara'), (SELECT ingredient_id FROM ingredient WHERE name = 'Bacon')),
((SELECT dish_id FROM dish WHERE name = 'Lasagna'), (SELECT ingredient_id FROM ingredient WHERE name = 'Pasta')),
((SELECT dish_id FROM dish WHERE name = 'Lasagna'), (SELECT ingredient_id FROM ingredient WHERE name = 'Cheese')),
((SELECT dish_id FROM dish WHERE name = 'Lasagna'), (SELECT ingredient_id FROM ingredient WHERE name = 'Meat')),
((SELECT dish_id FROM dish WHERE name = 'Risotto'), (SELECT ingredient_id FROM ingredient WHERE name = 'Rice')),
((SELECT dish_id FROM dish WHERE name = 'Risotto'), (SELECT ingredient_id FROM ingredient WHERE name = 'Cheese')),
((SELECT dish_id FROM dish WHERE name = 'Risotto'), (SELECT ingredient_id FROM ingredient WHERE name = 'Butter')),
((SELECT dish_id FROM dish WHERE name = 'Margherita Pizza'), (SELECT ingredient_id FROM ingredient WHERE name = 'Flour')),
((SELECT dish_id FROM dish WHERE name = 'Margherita Pizza'), (SELECT ingredient_id FROM ingredient WHERE name = 'Tomato')),
((SELECT dish_id FROM dish WHERE name = 'Margherita Pizza'), (SELECT ingredient_id FROM ingredient WHERE name = 'Cheese')),
((SELECT dish_id FROM dish WHERE name = 'Minestrone Soup'), (SELECT ingredient_id FROM ingredient WHERE name = 'Vegetables')),
((SELECT dish_id FROM dish WHERE name = 'Minestrone Soup'), (SELECT ingredient_id FROM ingredient WHERE name = 'Onions')),
((SELECT dish_id FROM dish WHERE name = 'Minestrone Soup'), (SELECT ingredient_id FROM ingredient WHERE name = 'Garlic')),
((SELECT dish_id FROM dish WHERE name = 'Bruschetta'), (SELECT ingredient_id FROM ingredient WHERE name = 'Bread Crumbs')),
((SELECT dish_id FROM dish WHERE name = 'Bruschetta'), (SELECT ingredient_id FROM ingredient WHERE name = 'Tomato')),
((SELECT dish_id FROM dish WHERE name = 'Bruschetta'), (SELECT ingredient_id FROM ingredient WHERE name = 'Garlic')),
((SELECT dish_id FROM dish WHERE name = 'Tiramisu'), (SELECT ingredient_id FROM ingredient WHERE name = 'Cheese')),
((SELECT dish_id FROM dish WHERE name = 'Tiramisu'), (SELECT ingredient_id FROM ingredient WHERE name = 'Sugar')),
((SELECT dish_id FROM dish WHERE name = 'Tiramisu'), (SELECT ingredient_id FROM ingredient WHERE name = 'Eggs')),
((SELECT dish_id FROM dish WHERE name = 'Panna Cotta'), (SELECT ingredient_id FROM ingredient WHERE name = 'Cream')),
((SELECT dish_id FROM dish WHERE name = 'Panna Cotta'), (SELECT ingredient_id FROM ingredient WHERE name = 'Sugar')),
((SELECT dish_id FROM dish WHERE name = 'Panna Cotta'), (SELECT ingredient_id FROM ingredient WHERE name = 'Gelatin')),
((SELECT dish_id FROM dish WHERE name = 'Prosciutto e Melone'), (SELECT ingredient_id FROM ingredient WHERE name = 'Meat')),
((SELECT dish_id FROM dish WHERE name = 'Prosciutto e Melone'), (SELECT ingredient_id FROM ingredient WHERE name = 'Fruit')),
((SELECT dish_id FROM dish WHERE name = 'Caprese Salad'), (SELECT ingredient_id FROM ingredient WHERE name = 'Tomato')),
((SELECT dish_id FROM dish WHERE name = 'Caprese Salad'), (SELECT ingredient_id FROM ingredient WHERE name = 'Cheese')),
((SELECT dish_id FROM dish WHERE name = 'Caprese Salad'), (SELECT ingredient_id FROM ingredient WHERE name = 'Basil')),
((SELECT dish_id FROM dish WHERE name = 'Bouillabaisse'), (SELECT ingredient_id FROM ingredient WHERE name = 'Fish')),
((SELECT dish_id FROM dish WHERE name = 'Bouillabaisse'), (SELECT ingredient_id FROM ingredient WHERE name = 'Tomato')),
((SELECT dish_id FROM dish WHERE name = 'Bouillabaisse'), (SELECT ingredient_id FROM ingredient WHERE name = 'Garlic')),
((SELECT dish_id FROM dish WHERE name = 'Coq au Vin'), (SELECT ingredient_id FROM ingredient WHERE name = 'Chicken')),
((SELECT dish_id FROM dish WHERE name = 'Coq au Vin'), (SELECT ingredient_id FROM ingredient WHERE name = 'Onions')),
((SELECT dish_id FROM dish WHERE name = 'Coq au Vin'), (SELECT ingredient_id FROM ingredient WHERE name = 'Wine')),
((SELECT dish_id FROM dish WHERE name = 'Ratatouille'), (SELECT ingredient_id FROM ingredient WHERE name = 'Tomato')),
((SELECT dish_id FROM dish WHERE name = 'Ratatouille'), (SELECT ingredient_id FROM ingredient WHERE name = 'Zucchini')),
((SELECT dish_id FROM dish WHERE name = 'Ratatouille'), (SELECT ingredient_id FROM ingredient WHERE name = 'Eggplant')),
((SELECT dish_id FROM dish WHERE name = 'Tarte Tatin'), (SELECT ingredient_id FROM ingredient WHERE name = 'Apples')),
((SELECT dish_id FROM dish WHERE name = 'Tarte Tatin'), (SELECT ingredient_id FROM ingredient WHERE name = 'Sugar')),
((SELECT dish_id FROM dish WHERE name = 'Tarte Tatin'), (SELECT ingredient_id FROM ingredient WHERE name = 'Butter')),
((SELECT dish_id FROM dish WHERE name = 'Escargots'), (SELECT ingredient_id FROM ingredient WHERE name = 'Snails')),
((SELECT dish_id FROM dish WHERE name = 'Escargots'), (SELECT ingredient_id FROM ingredient WHERE name = 'Garlic')),
((SELECT dish_id FROM dish WHERE name = 'Crêpes Suzette'), (SELECT ingredient_id FROM ingredient WHERE name = 'Flour')),
((SELECT dish_id FROM dish WHERE name = 'Crêpes Suzette'), (SELECT ingredient_id FROM ingredient WHERE name = 'Butter')),
((SELECT dish_id FROM dish WHERE name = 'Crêpes Suzette'), (SELECT ingredient_id FROM ingredient WHERE name = 'Sugar')),
((SELECT dish_id FROM dish WHERE name = 'Niçoise Salad'), (SELECT ingredient_id FROM ingredient WHERE name = 'Tomato')),
((SELECT dish_id FROM dish WHERE name = 'Niçoise Salad'), (SELECT ingredient_id FROM ingredient WHERE name = 'Tuna')),
((SELECT dish_id FROM dish WHERE name = 'Niçoise Salad'), (SELECT ingredient_id FROM ingredient WHERE name = 'Eggs')),
((SELECT dish_id FROM dish WHERE name = 'Cassoulet'), (SELECT ingredient_id FROM ingredient WHERE name = 'Pork')),
((SELECT dish_id FROM dish WHERE name = 'Cassoulet'), (SELECT ingredient_id FROM ingredient WHERE name = 'Beans')),
((SELECT dish_id FROM dish WHERE name = 'Cassoulet'), (SELECT ingredient_id FROM ingredient WHERE name = 'Sausage')),
((SELECT dish_id FROM dish WHERE name = 'Quiche Lorraine'), (SELECT ingredient_id FROM ingredient WHERE name = 'Eggs')),
((SELECT dish_id FROM dish WHERE name = 'Quiche Lorraine'), (SELECT ingredient_id FROM ingredient WHERE name = 'Bacon')),
((SELECT dish_id FROM dish WHERE name = 'Quiche Lorraine'), (SELECT ingredient_id FROM ingredient WHERE name = 'Cheese')),
((SELECT dish_id FROM dish WHERE name = 'Soupe à l\'Oignon'), (SELECT ingredient_id FROM ingredient WHERE name = 'Onions')),
((SELECT dish_id FROM dish WHERE name = 'Soupe à l\'Oignon'), (SELECT ingredient_id FROM ingredient WHERE name = 'Beef Broth')),
((SELECT dish_id FROM dish WHERE name = 'Soupe à l\'Oignon'), (SELECT ingredient_id FROM ingredient WHERE name = 'Cheese')),
((SELECT dish_id FROM dish WHERE name = 'Sushi'), (SELECT ingredient_id FROM ingredient WHERE name = 'Rice')),
((SELECT dish_id FROM dish WHERE name = 'Sushi'), (SELECT ingredient_id FROM ingredient WHERE name = 'Nori')),
((SELECT dish_id FROM dish WHERE name = 'Sushi'), (SELECT ingredient_id FROM ingredient WHERE name = 'Fish')),
((SELECT dish_id FROM dish WHERE name = 'Pad Thai'), (SELECT ingredient_id FROM ingredient WHERE name = 'Noodles')),
((SELECT dish_id FROM dish WHERE name = 'Pad Thai'), (SELECT ingredient_id FROM ingredient WHERE name = 'Peanuts')),
((SELECT dish_id FROM dish WHERE name = 'Pad Thai'), (SELECT ingredient_id FROM ingredient WHERE name = 'Chicken')),
((SELECT dish_id FROM dish WHERE name = 'Pad Thai'), (SELECT ingredient_id FROM ingredient WHERE name = 'Lime')),
((SELECT dish_id FROM dish WHERE name = 'Tom Yum Soup'), (SELECT ingredient_id FROM ingredient WHERE name = 'Shrimp')),
((SELECT dish_id FROM dish WHERE name = 'Tom Yum Soup'), (SELECT ingredient_id FROM ingredient WHERE name = 'Mushrooms')),
((SELECT dish_id FROM dish WHERE name = 'Tom Yum Soup'), (SELECT ingredient_id FROM ingredient WHERE name = 'Lemongrass')),
((SELECT dish_id FROM dish WHERE name = 'Tom Yum Soup'), (SELECT ingredient_id FROM ingredient WHERE name = 'Chili')),
((SELECT dish_id FROM dish WHERE name = 'Spring Rolls'), (SELECT ingredient_id FROM ingredient WHERE name = 'Carrots')),
((SELECT dish_id FROM dish WHERE name = 'Spring Rolls'), (SELECT ingredient_id FROM ingredient WHERE name = 'Cilantro')),
((SELECT dish_id FROM dish WHERE name = 'Spring Rolls'), (SELECT ingredient_id FROM ingredient WHERE name = 'Rice')),
((SELECT dish_id FROM dish WHERE name = 'Dumplings'), (SELECT ingredient_id FROM ingredient WHERE name = 'Flour')),
((SELECT dish_id FROM dish WHERE name = 'Dumplings'), (SELECT ingredient_id FROM ingredient WHERE name = 'Meat')),
((SELECT dish_id FROM dish WHERE name = 'Miso Soup'), (SELECT ingredient_id FROM ingredient WHERE name = 'Tofu')),
((SELECT dish_id FROM dish WHERE name = 'Miso Soup'), (SELECT ingredient_id FROM ingredient WHERE name = 'Seaweed')),
((SELECT dish_id FROM dish WHERE name = 'Teriyaki Chicken'), (SELECT ingredient_id FROM ingredient WHERE name = 'Chicken')),
((SELECT dish_id FROM dish WHERE name = 'Teriyaki Chicken'), (SELECT ingredient_id FROM ingredient WHERE name = 'Soy Sauce')),
((SELECT dish_id FROM dish WHERE name = 'Tempura'), (SELECT ingredient_id FROM ingredient WHERE name = 'Tempura Batter')),
((SELECT dish_id FROM dish WHERE name = 'Matcha Ice Cream'), (SELECT ingredient_id FROM ingredient WHERE name = 'Matcha')),
((SELECT dish_id FROM dish WHERE name = 'Matcha Ice Cream'), (SELECT ingredient_id FROM ingredient WHERE name = 'Cream')),
((SELECT dish_id FROM dish WHERE name = 'Bubble Tea'), (SELECT ingredient_id FROM ingredient WHERE name = 'Milk Tea')),
((SELECT dish_id FROM dish WHERE name = 'Bubble Tea'), (SELECT ingredient_id FROM ingredient WHERE name = 'Tapioca Pearls'));

create table if not exists restaurant_dish
(
    dish_id   bigint,
    restaurant_id bigint,
    foreign key (dish_id) references dish (dish_id),
    foreign key (restaurant_id) references restaurant (restaurant_id)
);


INSERT INTO dish_type (name)
VALUES
    ('Салат'),
    ('Суп'),
    ('Піца'),
    ('Основна страв'),
    ('Десерт'),
    ('Напій'),
    ('Закуска'),
    ('Паста'),
    ('Рисова страва'),
    ('Локшина');

create table if not exists restaurant_dish
(
    restaurant_id   bigint,
    dish_id bigint,
    foreign key (restaurant_id) references restaurant (restaurant_id),
    foreign key (dish_id) references dish (dish_id)
);