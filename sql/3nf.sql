drop database if exists paradise;
CREATE DATABASE  IF NOT EXISTS `paradise`;
USE `paradise`;
-- pw all asd
CREATE TABLE customer (
  `customer_id` int PRIMARY KEY auto_increment,
  `first_name` varchar(255),
  `last_name` varchar(255),
  password char(80),
  active tinyint,
  `customer_email` varchar(100) unique NOT NULL
);

CREATE TABLE address (
  `address_id` int PRIMARY KEY auto_increment,
  `customer_id` int,
  `line_1` varchar(255),
  `line_2` varchar(255),
  `city` varchar(255),
  `state` varchar(255),
  `zipcode` varchar(255)
);

CREATE TABLE orders (
  `order_id` int PRIMARY KEY auto_increment,
  `customer_id` int,
  `order_date` date,
  `order_total_price` decimal
);

CREATE TABLE product (
  `product_id` int PRIMARY KEY auto_increment,
  `product_name` varchar(255),
  `product_description` varchar(255),
  `product_price` decimal,
  `product_SKU` varchar(255),
  `manufacturer_id` int
);

CREATE TABLE shipment (
  `shipment_id` int PRIMARY KEY auto_increment,
  `address_id` int,
  `order_id` int,
  `shipment_date` date,
  `delivered` bool
);

CREATE TABLE product_image (
  `product_id` int,
  `image_id` int,
  PRIMARY KEY (`product_id`, `image_id`)
);

CREATE TABLE image (
  `image_id` int PRIMARY KEY auto_increment,
  `image_location` varchar(255)
);

CREATE TABLE manufacturer (
  `manufacturer_id` int PRIMARY KEY auto_increment,
  `manufacturer_name` varchar(255)
);

CREATE TABLE order_items (
  `order_id` int,
  `product_id` int,
  quantity int,
  `price_at_checkout` decimal,
  PRIMARY KEY (`order_id`, `product_id`)
);

CREATE TABLE tag (
  `tag_id` int PRIMARY KEY auto_increment,
  `tag_name` varchar(255),
  image_id int,
  foreign key (image_id) references image(image_id)
);

CREATE TABLE product_tag (
  `product_id` int,
  `tag_id` int,
  PRIMARY KEY (`product_id`, `tag_id`)
);
CREATE TABLE roles (
  customer_email varchar(100) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `authorities5_idx_1` (customer_email,`role`),
  CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (customer_email) REFERENCES customer (`customer_email`)
);



ALTER TABLE address ADD FOREIGN KEY (`customer_id`) REFERENCES customer (`customer_id`);

ALTER TABLE shipment ADD FOREIGN KEY (`address_id`) REFERENCES address (`address_id`);

ALTER TABLE orders ADD FOREIGN KEY (`customer_id`) REFERENCES customer (`customer_id`);

ALTER TABLE order_items ADD FOREIGN KEY (`order_id`) REFERENCES orders (`order_id`);

ALTER TABLE shipment ADD FOREIGN KEY (`order_id`) REFERENCES orders (`order_id`);

ALTER TABLE order_items ADD FOREIGN KEY (`product_id`) REFERENCES product (`product_id`);

ALTER TABLE product_image ADD FOREIGN KEY (`product_id`) REFERENCES product (`product_id`);

ALTER TABLE product_image ADD FOREIGN KEY (`image_id`) REFERENCES image (`image_id`);

ALTER TABLE product ADD FOREIGN KEY (`manufacturer_id`) REFERENCES manufacturer (`manufacturer_id`);

ALTER TABLE product_tag ADD FOREIGN KEY (`product_id`) REFERENCES product (`product_id`);

ALTER TABLE product_tag ADD FOREIGN KEY (`tag_id`) REFERENCES tag (`tag_id`);

INSERT INTO customer(first_name, last_name, customer_email, password, active)
    VALUES ('Luke', 'Hanrahan', 'lah5544@psu.edu', '{bcrypt}$2a$10$JlElRfMnrjr/SixCavSTEOBW9Y0I8g57uclBoQXPknDcmqiMyjbha',1 );

INSERT INTO customer(first_name, last_name, customer_email, password, active)
    VALUES ('John', 'Doe', 'jod1234@psu.edu' , '{bcrypt}$2a$10$JlElRfMnrjr/SixCavSTEOBW9Y0I8g57uclBoQXPknDcmqiMyjbha' ,1 );

INSERT INTO customer(first_name, last_name, customer_email, password, active)
    VALUES ('Jane', 'Doe', 'jad4231@psu.edu' , '{bcrypt}$2a$10$JlElRfMnrjr/SixCavSTEOBW9Y0I8g57uclBoQXPknDcmqiMyjbha' ,1 );
    
INSERT INTO `roles`
VALUES
('lah5544@psu.edu','ROLE_ADMIN'),
('jod1234@psu.edu','ROLE_CUSTOMER'),
('jad4231@psu.edu','ROLE_CUSTOMER');
    insert into address(customer_id, line_1, line_2, city, state, zipcode)
    values(1, '15 Example Lane', '', 'Media', 'PA', '14823');

insert into address(customer_id, line_1, line_2, city, state, zipcode)
    values(2, '23 Test Road', '', 'State College', 'PA', '16353');

insert into address(customer_id, line_1, line_2, city, state, zipcode)
    values(3, '45 Midterm Lane', '202B', 'Bellefonte', 'PA', '14952');
    
insert into orders(customer_id, order_date, order_total_price)
    values(1, '2024-03-09', 60.34);
insert into orders(customer_id, order_date, order_total_price)
    values(2, '2024-02-09', 15.99);
insert into orders(customer_id, order_date, order_total_price)
    values(3, '2023-01-23', 40.24);
    
insert into manufacturer(manufacturer_name) values('Paradise Arcade');
insert into manufacturer(manufacturer_name) values('CRAFTYMECH');
insert into manufacturer(manufacturer_name) values('SANWA');

insert into product(product_name, product_description, product_price, product_SKU, manufacturer_id)
    values('Sanwa OBSF-30 Snap-in Button - Red', 'Three words describe these amazing high quality arcade buttons: Smooth, Sensitive and quiet. These snap in based buttons are for metal control panels with 30mm holes.',
    2.95, 'OBSF-30-R', 3);

insert into product(product_name, product_description, product_price, product_SKU, manufacturer_id)
    values('mini-SLG', 'The mini-SLG is an inexpensive, and compact scanline generator that is compatible with devices that output a standard VGA signal @ 640×480 (preferred) & 1024×768 resolutions.', 20, 'cm-slg-mini-slg', 2);

insert into product(product_name, product_description, product_price, product_SKU, manufacturer_id)
    values('MPress V2 Fighting Case', 'The MPress is 12.5" x 9" and less than 1 inch thick but does NOT sacrifice on performance. Brook Fusion, MX switches standard, and a tethered USB C connection.  This fits easily into a laptop bag, designed for portability and competition level play.  ', 295, 'MpressV2', 1);
insert into shipment(address_id, order_id, shipment_date, delivered)
    values(1, 1, '2024-03-10', false);

insert into shipment(address_id, order_id, shipment_date, delivered)
    values(2, 2, '2024-02-11', true);

insert into shipment(address_id, order_id, shipment_date, delivered)
    values(3, 3, '2023-01-25', true);
insert into tag(tag_name) values('Buttons');
insert into tag(tag_name) values('Scan Line Generators');
insert into tag(tag_name) values('Retail Arcade Sticks & Custom Cases');


insert into image(image_location) values('example/path');
insert into image(image_location) values('test/path');
insert into image(image_location) values('s3bucket metadata');
insert into image(image_location) values('/images/collections/fightStickPCB.webp');
insert into tag(tag_name, image_id) values('Fight Stick PCB', 4);

insert into order_items(order_id, product_id, quantity, price_at_checkout) values(1,1,3,2.65);
insert into order_items(order_id, product_id, quantity, price_at_checkout) values(2,2,1,20);
insert into order_items(order_id, product_id, quantity, price_at_checkout) values(3,1,5,2.85);
insert into order_items(order_id, product_id, quantity, price_at_checkout) values(3,3,1,275);

insert into product_image(product_id, image_id) values(1,1);
insert into product_image(product_id, image_id) values(2,2);
insert into product_image(product_id, image_id) values(3,3);

insert into product_tag(product_id, tag_id) values(1,1);
insert into product_tag(product_id, tag_id) values(2,2);
insert into product_tag(product_id, tag_id) values(3,3);

SELECT  product_name, product_price, tag_name from product p
    inner join product_tag pt on pt.product_id=p.product_id
    inner join tag t on t.tag_id=pt.tag_id
    ;

select * from product;
select * from tag where not isnull(tag.image_id);