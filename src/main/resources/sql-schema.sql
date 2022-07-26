drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `customer_id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS products(
product_id int primary key auto_increment,
product_name varchar(50) not null,
price decimal(10,2) not null
);

create table if not exists order_record(
order_id int primary key auto_increment,
customer_id int not null,
foreign key (customer_id) references customers(id)
);

create table if not exists order_item(
id int primary key auto_increment,
order_id int not null,
product_id int not null,
foreign key (order_id) references order_record(order_id),
foreign key (product_id) references products(product_id)
);


