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

create table category
(
    category_id bigint,
    name        varchar(256),
    PRIMARY KEY (category_id)
);

create table restaurant
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

create table restaurant_category
(
    category_id   bigint,
    restaurant_id bigint,
    foreign key (category_id) references category (category_id),
    foreign key (restaurant_id) references restaurant (restaurant_id)
);

create table dish_type(
    id int primary key auto_increment,
    name varchar(256)
);

create table dish(
    dish_id int primary key auto_increment,
    name varchar(256),
    price double,
    dish_type int,
    cuisine_type varchar(256),
    foreign key (dish_type) references dish_type (id)
);

create table ingredient(
   ingredient_id int primary key auto_increment,
    name varchar(256)
);

create table dish_ingredient
(
    dish_id   bigint,
    ingredient_id bigint,
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
  ('Beets'), ('Cabbage'), ('Potatoes'), ('Cheese'), ('Poppy Seeds'), ('Honey'),
  ('Meat'), ('Rice'), ('Vegetables'), ('Flour'), ('Sugar'), ('Eggs'), ('Garlic'), ('Chicken'), ('Butter'), ('Bread Crumbs'), ('Buckwheat'),
  ('Fruit'), ('Carrots'), ('Onions'), ('Gelatin'), ('Cheese'), ('Lettuce'), ('Tomato'), ('Beef'), ('Onions'), ('Parmesan'),
  ('Croutons'), ('Chicken'), ('Butter'), ('Cream'), ('Pasta'), ('Pork'), ('BBQ Sauce'),
  ('Flour'), ('Sugar'), ('Baking Powder'), ('Eggs'), ('Milk'), ('Syrup'), ('Apples'), ('Basil'), ('Wine'), ('Rice'), ('Nori'), ('Seaweed'), ('Fish'), ('Soy Sauce'), ('Vinegar'),
  ('Shrimp'), ('Tofu'), ('Noodles'), ('Peanuts'), ('Lime'), ('Chicken'),
  ('Mushrooms'), ('Lemongrass'), ('Cilantro'), ('Chili'), ('Mint'), ('Carrots'),
  ('Green Onions'), ('Flour'), ('Eggs'), ('Matcha'), ('Green Tea'), ('Milk Tea'),
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

create table restaurant_dish
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

INSERT INTO dish (name, description, price, dish_type, cuisine_type)
VALUES
    /* українська */
    ('Вареники з картоплею', 'Традиційні українські вареники з картоплею та цибулею', 80, 7, 'ukrainian'),
    ('Голубці', 'Капустяні голубці з м\'ясом та рисом', 120, 4, 'ukrainian'),
    ('Котлета по-київськи', 'Куряча котлета з маслом та зеленню', 150, 4, 'ukrainian'),
    ('Деруни', 'Картопляні оладки зі сметаною', 90, 7, 'ukrainian'),
    ('Пампушки з часником', 'М\'які пампушки з часником', 50, 7, 'ukrainian'),
    ('Банош', 'Традиційна страва з кукурудзяної каші з бринзою', 100, 4, 'ukrainian'),
    ('Сирники', 'Смажені сирники з варенням', 90, 5, 'ukrainian'),
    ('Паляниця', 'Український хліб з пшеничного борошна', 40, 7, 'ukrainian'),
    ('Буженина', 'Запечене м\'ясо з приправами', 140, 4, 'ukrainian'),
    ('Вареники з вишнями', 'Солодкі вареники з вишнями', 100, 7, 'ukrainian'),
    ('Салат "Олів\'є"', 'Традиційний салат з овочами та м\'ясом', 110, 1, 'ukrainian'),
    ('Квас', 'Традиційний український напій з хліба', 50, 6, 'ukrainian'),
    ('Капусняк', 'Капустяний суп з м\'ясом', 120, 2, 'ukrainian'),
    ('Печеня', 'Печене м\'ясо з овочами', 130, 4, 'ukrainian'),
    ('Сало', 'Традиційне українське сало', 70, 7, 'ukrainian'),
    ('Суп "Гуляш"', 'Густий м\'ясний суп', 130, 2, 'ukrainian'),
    ('Компот', 'Фруктовий напій', 60, 6, 'ukrainian'),
    ('Холодець', 'Страва з холодцем з м\'ясом', 110, 7, 'ukrainian'),
    ('Солянка', 'Суп з різними видами м\'яса', 120, 2, 'ukrainian'),
    ('Пшоняна каша з гарбузом', 'Традиційна пшоняна каша з гарбузом', 90, 8, 'ukrainian'),
    /* американська */
    ('Бургер', 'Класичний американський бургер з яловичини', 250, 4, 'american'),
    ('Бургер з куркою', 'Бургер з курячим філе', 220, 4, 'american'),
    ('Хот-дог', 'Ковбаска в булочці з гірчицею та кетчупом', 150, 4, 'american'),
    ('Французький тост', 'Тости з яйцем, молоком та корицею', 130, 5, 'american'),
    ('Панкейки', 'Класичні американські панкейки з сиропом', 140, 5, 'american'),
    ('Брауні', 'Шоколадний десерт', 160, 5, 'american'),
    ('Донатс', 'Американські пончики з глазур\'ю', 120, 5, 'american'),
    ('Рибний такос', 'Мексиканські такос з рибою та овочами', 170, 7, 'american'),
    ('Чикен такос', 'Мексиканські такос з куркою та овочами', 160, 7, 'american'),
    ('Креветки по-креольськи', 'Креветки з креольськими спеціями', 210, 6, 'american'),
    ('Мак-н-чиз', 'Макарони з сирним соусом', 130, 8, 'american'),
    ('Стейк Нью-Йорк', 'Яловичий стейк з Нью-Йорку', 420, 4, 'american'),
    ('Стейк Томагавк', 'Великий стейк на кістці', 520, 4, 'american'),
    ('Курячі нагетси', 'Курячі нагетси з соусом', 110, 7, 'american'),
    ('Смажена курка', 'Курка, обсмажена у фритюрі', 150, 7, 'american'),
    ('Клаб-сендвіч', 'Сендвіч з куркою, беконом і овочами', 180, 7, 'american'),
    ('Піца "Барбекю"', 'Піца з соусом барбекю, куркою та овочами', 290, 3, 'american'),
    ('Американський яблучний пиріг', 'Пиріг з яблуками та корицею', 180, 5, 'american'),
    ('Кукурудзяний хліб', 'Хліб з кукурудзяного борошна', 90, 7, 'american'),
    ('Омлет з сиром', 'Омлет з додаванням сиру', 120, 2, 'american'),
    ('Омлет з беконом', 'Омлет з беконом та овочами', 130, 2, 'american'),
    ('Піцца "Чикаго"', 'Піца з товстим тістом та багатим начинням', 320, 3, 'american'),
    ('Рібс', 'Свинина, запечена з барбекю соусом', 350, 4, 'american'),
    ('Південна качка', 'Качка з овочами по південному стилю', 390, 4, 'american'),
    ('Креветки коктейль', 'Креветки з коктейльним соусом', 150, 7, 'american'),
    ('Цибулеві кільця', 'Обсмажені цибульні кільця', 100, 7, 'american'),
    ('Картофель фрі', 'Картопля, обсмажена у фритюрі', 90, 7, 'american'),
    ('Банановий хліб', 'Солодкий хліб з бананів', 120, 5, 'american'),
    ('Чилі кон карне', 'Гострий м\'ясний рагу з квасолею', 180, 2, 'american'),
    ('Десерт "Флоат"', 'Лимонад з додаванням морозива', 130, 6, 'american'),
    ('Мілкшейк', 'Напій з молока та морозива', 140, 6, 'american'),
    ('Колеслоу', 'Салат з капусти та моркви', 80, 1, 'american'),
    ('Кукурудзяний качан', 'Смажена або варена кукурудза', 70, 7, 'american'),
    ('Піца "Квінта"', 'Піца з п\'ятьма видами сиру', 300, 3, 'american'),
    ('Пастрама', 'Маринована та запечена яловичина', 250, 4, 'american'),
    ('Кукурудзяна каша', 'Каша з кукурудзи', 110, 5, 'american'),
    ('Овочевий суп', 'Суп з різними овочами', 100, 2, 'american'),
    ('Тістечка з арахісовим маслом', 'Тістечка з арахісовим маслом', 130, 5, 'american'),
    ('Бостонський кремовий пиріг', 'Десерт з кремом та шоколадним глазур\'ю', 160, 5, 'american'),
    /* італійська */
    ('Брускета з базиліком', 'Підсмажений хліб з томатами, базиліком та оливковою олією', 130, 7, 'italian'),
    ('Фокачча', 'Традиційний італійський хліб з розмарином і оливковою олією', 90, 7, 'italian'),
    ('Спагеті "Болоньєзе"', 'Спагеті з м\'ясним соусом болоньєзе', 210, 8, 'italian'),
    ('Паста "Карбонара"', 'Паста з вершками, яйцем, пармезаном і беконом', 250, 8, 'italian'),
    ('Равіолі з сиром', 'Равіолі, наповнені сиром рікотта і шпинатом', 270, 8, 'italian'),
    ('Тірамісу', 'Італійський десерт з маскарпоне, бісквітом і кавою', 200, 5, 'italian'),
    ('Панна-котта', 'Десерт на основі вершків з желатином і ягодами', 180, 5, 'italian'),
    ('Мінестроне', 'Овочевий суп з пастою і бобами', 150, 2, 'italian'),
    ('Ризото з грибами', 'Рисове блюдо з грибами та пармезаном', 230, 9, 'italian'),
    ('Кальцоне', 'Закрита піца з шинкою, сиром і овочами', 280, 3, 'italian'),
    ('Піца "Діабола"', 'Піца з гострим салямі і червоним перцем', 300, 3, 'italian'),
    ('Піца "Чотири сири"', 'Піца з чотирма видами сиру: моцарела, гауда, пармезан, дорблю', 320, 3, 'italian'),
    ('Салат "Капрезе"', 'Салат з помідорів, моцарели, базиліка і оливкової олії', 170, 1, 'italian'),
    ('Фруктовий салат', 'Салат з міксом сезонних фруктів', 150, 1, 'italian'),
    ('Кава еспресо', 'Традиційна італійська кава еспресо', 80, 6, 'italian'),
    ('Кава капучино', 'Кава з молоком і пінкою', 100, 6, 'italian'),
    ('Кава латте', 'Кава з великою кількістю молока', 120, 6, 'italian'),
    ('Лимончелло', 'Традиційний італійський лимонний лікер', 150, 6, 'italian'),
    ('Брускета з баклажанами', 'Підсмажений хліб з баклажанами і оливковою олією', 140, 7, 'italian'),
    ('Спагеті "Качо е Пепе"', 'Спагеті з сиром пекоріно і чорним перцем', 220, 8, 'italian'),
/* французька */
    ('Салат Нісуаз', 'Салат з тунцем, оливками, анчоусами, помідорами та картоплею', 180, 1, 'french'),
    ('Салат "Ліонський"', 'Салат з беконом, курячим яйцем, цибулею та зеленою квасолею', 170, 1, 'french'),
    ('Салат "Шевр Шо"', 'Салат з козячим сиром, помідорами, та грінками', 200, 1, 'french'),
    ('Салат "Ландайс"', 'Салат з копченим качиним філе та лісовими грибами', 210, 1, 'french'),
    ('Суп "Буйабес"', 'Традиційний французький суп з морепродуктами', 250, 2, 'french'),
    ('Суп "Вишисуаз"', 'Крем-суп з картоплі та порею', 150, 2, 'french'),
    ('Французький цибулевий суп', 'Класичний суп з цибулі та бульйоном', 140, 2, 'french'),
    ('Крем-суп з гарбуза', 'Крем-суп з гарбуза з вершками', 130, 2, 'french'),
    ('Кіш Лорен', 'Французький пиріг з беконом та сиром', 190, 7, 'french'),
    ('Фуа-гра', 'Традиційний французький паштет з гусячої печінки', 300, 7, 'french'),
    ('Рататуй', 'Овочевий рататуй з баклажанів, кабачків, помідорів', 160, 7, 'french'),
    ('Камбала на грилі', 'Камбала, смажена на грилі', 220, 7, 'french'),
    ('Філе Міньйон', 'Соковите філе міньйон з яловичини', 420, 4, 'french'),
    ('Дорадо на грилі', 'Риба Дорадо, приготована на грилі', 350, 4, 'french'),
    ('Конфі з качки', 'Качка, приготована в качиному жирі', 380, 4, 'french'),
    ('Шукрут', 'Традиційна страва з квашеної капусти та м’яса', 240, 4, 'french'),
    ('Спагетті з рагу по-болоньєзе', 'Спагетті з м’ясним соусом рагу', 180, 8, 'french'),
    ('Пенне з куркою та грибами', 'Пенне з вершковим соусом, куркою та грибами', 200, 8, 'french'),
    ('Піца "Провенсаль"', 'Тісто, соус, сир, баклажани та оливки', 250, 3, 'french'),
    ('Піца "Франкфурт"', 'Тісто, соус, сир, сосиски та чилі', 260, 3, 'french'),
    ('Мус з шоколаду', 'Легкий шоколадний мус', 160, 5, 'french'),
    ('Тарт Татен', 'Традиційний яблучний тарт з карамеллю', 200, 5, 'french'),
    ('Еклер з ваніллю', 'Профітролі з ванільним кремом', 170, 5, 'french'),
    ('Макаарон з мигдалем', 'Десерт з мигдалем та кремом', 150, 5, 'french'),
    ('Французька кава', 'Кава з молоком', 90, 6, 'french'),
    ('Вино Шардоне', 'Біле французьке вино Шардоне', 200, 6, 'french'),
    ('Червоне Бордо', 'Червоне французьке вино Бордо', 250, 6, 'french'),
    ('Багет', 'Французький хліб-багет', 40, 7, 'french'),
    ('Круасан з маслом', 'Французький круасан з маслом', 50, 7, 'french'),
    ('Тартифлет', 'Картопляна запіканка з сиром та беконом', 220, 7, 'french'),
    ('Гратен', 'Картопля запечена з сиром', 190, 7, 'french'),
/* німецька */
    ('Шніцель', 'Традиційний німецький шніцель з телятини', 250, 4, 'german'),
    ('Брецель', 'Традиційний німецький солоний брецель', 50, 7, 'german'),
    ('Баварські ковбаски', 'Ковбаски, приготовані в баварському стилі', 180, 4, 'german'),
    ('Кисла капуста', 'Квашена капуста з приправами', 100, 7, 'german'),
    ('Яблучний штрудель', 'Традиційний німецький яблучний десерт', 170, 5, 'german'),
    ('Картофельний салат', 'Салат з картоплі, цибулі та майонезу', 140, 1, 'german'),
    ('Ербсензуппе', 'Традиційний німецький гороховий суп', 120, 2, 'german'),
    ('Баварська квашена капуста', 'Квашена капуста з додаванням баварських ковбасок', 110, 7, 'german'),
    ('Пфеффернуссе', 'Традиційне німецьке печиво з перцем', 80, 5, 'german'),
    ('Картофельні кльоци', 'Картопляні кльоци з соусом', 130, 7, 'german'),
    ('Фламкухен', 'Німецька піца з беконом та цибулею', 180, 3, 'german'),
    ('Шварцвальдський торт', 'Торт з вишнею та шоколадом', 200, 5, 'german'),
    ('Ролмопс', 'Маринований оселедець, загорнутий з цибулею', 120, 7, 'german'),
    ('Лейпцігський суп', 'Суп з морквою, горохом та пастернаком', 100, 2, 'german'),
    ('Цвібелекухен', 'Пиріг з цибулею та беконом', 160, 4, 'german'),
    ('Баварські міттельренген', 'Традиційні німецькі мариновані кільця цибулі', 70, 7, 'german'),
    ('Нюрнберзькі ковбаски', 'Традиційні нюрнберзькі ковбаски', 190, 4, 'german'),
    ('Смажена німецька картопля', 'Смажена картопля з цибулею та беконом', 100, 7, 'german'),
    ('Баварський хліб', 'Традиційний баварський хліб', 60, 7, 'german'),
    ('Пфаннітопф', 'Страва з м\'яса та картоплі, приготована в одному горщику', 150, 4, 'german'),
    /* азіатська */
    ('Салат "Сом Там"', 'Тайський салат з зеленої папаї, чилі та арахісом', 150, 1, 'asian'),
    ('Салат "Гадо-Гадо"', 'Індонезійський овочевий салат з арахісовим соусом', 160, 1, 'asian'),
    ('Суп "Том Ям"', 'Гострий тайський суп з креветками, грибами та кокосовим молоком', 200, 2, 'asian'),
    ('Суп "Місо"', 'Традиційний японський місо-суп з тофу та вакаме', 120, 2, 'asian'),
    ('Локшина "Пад Тай"', 'Тайська локшина з креветками, арахісом та овочами', 220, 9, 'asian'),
    ('Локшина "Удон"', 'Японська пшенична локшина з овочами', 180, 9, 'asian'),
    ('Локшина "Соба"', 'Гречана локшина з овочами та соєвим соусом', 190, 9, 'asian'),
    ('Смажений рис', 'Рис, смажений з яйцем, овочами та куркою', 170, 8, 'asian'),
    ('Смажений рис з ананасом', 'Рис, смажений з ананасом, куркою та горіхами', 190, 8, 'asian'),
    ('Смажений рис з морепродуктами', 'Рис, смажений з креветками та кальмарами', 210,8, 'asian'),
    ('Курка "Теріякі"', 'Курка з соусом теріякі', 250, 3, 'asian'),
    ('Суші "Філадельфія"', 'Суші з лососем та вершковим сиром', 300, 3, 'asian'),
    ('Суші "Каліфорнія"', 'Суші з крабом, авокадо та ікрою', 280, 3, 'asian'),
    ('Суші "Нігірі"', 'Суші з рисом та різними начинками', 270, 3, 'asian'),
    ('Смажена качка по-пекінськи', 'Качка, приготована за пекінським рецептом', 320, 3, 'asian'),
    ('Смажена риба по-тайськи', 'Риба, смажена з соусом чилі', 300, 3, 'asian'),
    ('Креветки в темпурі', 'Креветки, смажені в клярі', 220, 6, 'asian'),
    ('Спрінг-роли', 'В’єтнамські рулети з овочами та м’ясом', 180, 6, 'asian'),
    ('Гьодза', 'Японські пельмені з різними начинками', 200, 6, 'asian'),
    ('Люті курячі крильця', 'Курячі крильця з гострим соусом', 180, 6, 'asian'),
    ('Мочі', 'Японський десерт з рисового тіста з різними начинками', 150, 4, 'asian'),
    ('Доріякі', 'Японський десерт, схожий на млинці', 160, 4, 'asian'),
    ('Какао', 'Традиційний напій на основі какао', 100, 5, 'asian'),
    ('Молочний чай', 'Чай з додаванням молока', 110, 5, 'asian'),
    ('Личі з кокосовим молоком', 'Десерт з личі та кокосовим молоком', 120, 4, 'asian'),
    ('Манго-стики-райс', 'Тайський десерт з рису та манго', 130, 4, 'asian'),
    ('Курка в меді та кунжуті', 'Курка в медовому соусі з кунжутом', 270, 3, 'asian'),
    ('Тофу з овочами', 'Смажений тофу з овочами та соєвим соусом', 160, 3, 'asian'),
    ('Локшина "Лао Сю"', 'Локшина з різними овочами та м’ясом', 230, 9, 'asian'),
    ('Креветки в соусі чилі', 'Креветки з гострим соусом', 230, 6, 'asian'),
    ('Суп "Лакса"', 'Малайзійський гострий кокосовий суп з морепродуктами', 220, 2, 'asian'),
    ('Рис з куркою по-китайськи', 'Рис з куркою в соусі', 210, 8, 'asian'),
    ('Суп "Том Кха Гай"', 'Тайський кокосовий суп з куркою', 200, 2, 'asian'),
    ('Пиріг з бобів ацукі', 'Десерт на основі бобів ацукі', 140, 4, 'asian'),
    ('Чай матча', 'Японський зелений чай з матчею', 110, 5, 'asian'),
/* грецька */
    ('Мусака', 'Запіканка з баклажанів, картоплі та м\'ясного фаршу', 250, 4, 'greek'),
    ('Сувлакі', 'Кебаби з маринованого м\'яса', 180, 7, 'greek'),
    ('Пастіціо', 'Запіканка з макаронів та м\'яса', 240, 8, 'greek'),
    ('Тзадзикі', 'Грецький соус з йогурту, огірків та часнику', 100, 7, 'greek'),
    ('Саганаки', 'Обсмажений сир', 150, 7, 'greek'),
    ('Долма', 'Голубці з виноградного листя з начинкою', 170, 7, 'greek'),
    ('Спанакопіта', 'Пиріг зі шпинатом та сиром фета', 210, 7, 'greek'),
    ('Тарамасалата', 'Паста з рибної ікри', 130, 7, 'greek'),
    ('Авголемоно', 'Грецький курячий суп з лимонним соком', 140, 2, 'greek'),
    ('Салат "Хорьятики"', 'Традиційний грецький сільський салат', 170, 1, 'greek'),
    ('Фава', 'Пюре з жовтого гороху', 120, 7, 'greek'),
    ('Фета Саганакі', 'Обсмажений сир фета', 160, 7, 'greek'),
    ('Котопуло йемісто', 'Фарширована курка', 270, 4, 'greek'),
    ('Фрікасе з ягняти', 'Ягня з овочами та соусом', 310, 4, 'greek'),
    ('Гірос', 'М\'ясо в піті з овочами', 180, 7, 'greek'),
    ('Скорталія', 'Часниковий соус', 90, 7, 'greek'),
    ('Галіактора', 'Традиційний грецький десерт', 150, 5, 'greek'),
    ('Хортопіта', 'Пиріг із зелені та сиру фета', 210, 7, 'greek'),
    ('Тіропіта', 'Пиріг із сиром', 190, 7, 'greek'),
    ('Кокіністо', 'Тушкована яловичина в томатному соусі', 290, 4, 'greek'),
/* мексиканська */
    ('Такос з яловичиною', 'Тортілья з яловичиною, салатом та сальсою', 120, 7, 'mexican'),
    ('Чилі кон карне', 'Гостра страва з яловичини та квасолі', 150, 2, 'mexican'),
    ('Енчіладас з куркою', 'Тортілья, запечена з куркою та сиром', 160, 7, 'mexican'),
    ('Начос з сиром', 'Хрусткі чіпси з розплавленим сиром', 100, 7, 'mexican'),
    ('Кесадилья з овочами', 'Тортілья з овочами та сиром', 130, 7, 'mexican'),
    ('Буріто з рисом і квасолею', 'Тортілья з рисом, квасолею та овочами', 140, 7, 'mexican'),
    ('Сальса з манго', 'Свіжа сальса з манго, томатами та перцем', 80, 7, 'mexican'),
    ('Гуакамоле', 'Соус з авокадо, лимоном та зеленню', 90, 7, 'mexican'),
    ('Фахітас з яловичиною', 'Тортілья з яловичиною та овочами', 180, 7, 'mexican'),
    ('Тостада з куркою', 'Хрустка тортілья з куркою та авокадо', 150, 7, 'mexican'),
    ('Чуррос', 'Солодкий десерт з тіста, обсмаженого у фритюрі', 90, 5, 'mexican'),
    ('Тамале з свининою', 'Тамале з кукурудзяного борошна зі свининою', 170, 7, 'mexican'),
    ('Хуевос ранчерос', 'Яйця з тортільєю та сальсою', 130, 7, 'mexican'),
    ('Арроз кон полло', 'Рис з куркою та овочами', 140, 8, 'mexican'),
    ('Тортілья суп', 'Суп з тортільєю, куркою та овочами', 120, 2, 'mexican'),
    ('Кесо фондю', 'Розплавлений сир з гострим соусом', 110, 7, 'mexican'),
    ('Елоте (кукурудза на грилі)', 'Кукурудза на грилі з майонезом та сиром', 70, 7, 'mexican'),
    ('Піко де Галло', 'Свіжий салат з томатів, цибулі та перцю', 80, 7, 'mexican'),
    ('Молехас з яловичини', 'Смажені залози яловичини', 200, 7, 'mexican'),
    ('Коктейль з креветок', 'Креветки в гострому соусі', 150, 7, 'mexican'),
/* веганська */
    ('Салат з авокадо', 'Авокадо, огірки, помідори, лайм, кінза', 120, 1, 'vegan'),
    ('Салат з буряка', 'Буряк, горіхи, руккола, оливкова олія', 110, 1, 'vegan'),
    ('Суп з сочевиці', 'Сочевиця, морква, цибуля, часник, кмин', 130, 2, 'vegan'),
    ('Томатний суп', 'Помідори, базилік, овочі, часник', 100, 2, 'vegan'),
    ('Веганська піца', 'Тісто, томатний соус, овочі, оливки, веганський сир', 260, 3, 'vegan'),
    ('Піца з баклажанами', 'Тісто, томатний соус, баклажани, базилік, веганський сир', 270, 3, 'vegan'),
    ('Тофу стир-фрай', 'Тофу, перець, морква, броколі, соєвий соус', 180, 4, 'vegan'),
    ('Гречка з грибами', 'Гречка, гриби, цибуля, часник', 160, 4, 'vegan'),
    ('Паста з песто', 'Паста, базилік, горіхи, оливкова олія', 190, 8, 'vegan'),
    ('Рис з овочами', 'Рис, овочі, соєвий соус', 150, 9, 'vegan'),
    ('Салат з нутом', 'Нут, помідори, цибуля, кінза, лайм', 110, 1, 'vegan'),
    ('Суп з гороху', 'Сухий горох, морква, цибуля, кмин', 130, 2, 'vegan'),
    ('Грибний суп', 'Гриби, картопля, цибуля, овочевий бульйон', 140, 2, 'vegan'),
    ('Веганська лазанья', 'Лазанья з овочами та веганським сиром', 220, 8, 'vegan'),
    ('Ризотто з гарбузом', 'Рис, гарбуз, цибуля, веганський сир', 200, 9, 'vegan'),
    ('Суп із спаржею', 'Спаржа, картопля, овочевий бульйон', 120, 2, 'vegan'),
    ('Чіа-пудинг', 'Чіа-насіння, мигдальне молоко, ягоди', 90, 5, 'vegan'),
    ('Веганське морозиво', 'Заморожене кокосове молоко з фруктами', 100, 5, 'vegan'),
    ('Фруктовий десерт', 'Асорті з різних фруктів', 80, 5, 'vegan'),
    ('Веганський шоколадний пиріг', 'Шоколадний пиріг на основі веганських інгредієнтів', 150, 5, 'vegan');

create table restaurant_dish
(
    restaurant_id   bigint,
    dish_id bigint,
    foreign key (restaurant_id) references restaurant (restaurant_id),
    foreign key (dish_id) references dish (dish_id)
);