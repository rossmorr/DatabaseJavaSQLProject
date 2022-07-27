DROP TABLE IF EXISTS `order_item`;
DROP TABLE IF EXISTS `order_record`;
DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `items`;

CREATE TABLE IF NOT EXISTS `customers` (
    `customer_id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE IF NOT EXISTS items(
item_id int primary key auto_increment,
item_name varchar(50) not null,
price decimal(10,2) not null
);

create table if not exists order_record(
order_id int primary key auto_increment,
customer_id int not null,
foreign key (customer_id) references customers(customer_id)
);

create table if not exists order_item(
id int primary key auto_increment,
order_id int not null,
item_id int not null,
foreign key (order_id) references order_record(order_id),
foreign key (item_id) references items(item_id)
);