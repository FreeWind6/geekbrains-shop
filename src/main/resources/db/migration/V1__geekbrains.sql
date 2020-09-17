CREATE SCHEMA IF NOT EXISTS public;

DROP TABLE IF EXISTS image;

CREATE TABLE IF NOT EXISTS image (
    id uuid DEFAULT uuid_generate_v4() UNIQUE NOT NULL CONSTRAINT PK_image PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

INSERT INTO image (id, name) VALUES ('08b39bd2-5357-43f1-aa3e-d1b9ce028e5e', 'apple.png');
INSERT INTO image (id, name) VALUES ('290679fa-9812-44c6-aa22-df5689d99e14', 'bread.png');
INSERT INTO image (id, name) VALUES ('b85d8706-d663-4311-a15a-fea21e476ee9', 'cheese.png');
INSERT INTO image (id, name) VALUES ('90806402-753e-436b-85c4-cbe84e4644eb', 'donut.png');
INSERT INTO image (id, name) VALUES ('0c38d227-e573-451d-8360-1eb038fd7472', 'juice.png');
INSERT INTO image (id, name) VALUES ('9ec9ea5a-f9ef-4159-9a9c-bfeedc77ca23', 'lemonade.png');
INSERT INTO image (id, name) VALUES ('90a1448a-2989-4882-aa70-7cd97f0bcbf6', 'milk.png');
INSERT INTO image (id, name) VALUES ('62b68ec4-7e33-4bce-a27e-cffb558040f1', 'tea.png');

DROP TABLE IF EXISTS product;

CREATE TABLE IF NOT EXISTS product (
    id uuid DEFAULT uuid_generate_v4() UNIQUE NOT NULL CONSTRAINT PK_product PRIMARY KEY,
    title VARCHAR(255) UNIQUE NOT NULL,
    description VARCHAR(255),
    price DOUBLE PRECISION DEFAULT 0 NOT NULL,
    added TIMESTAMP DEFAULT now() NOT NULL,
    available BOOLEAN DEFAULT FALSE,
    image uuid CONSTRAINT FK_product_image REFERENCES image ON UPDATE CASCADE ON DELETE CASCADE,
    category INTEGER NOT NULL
);

INSERT INTO product (id, title, description, price, added, available, image, category) VALUES ('6d0870b1-e60a-4199-acee-b9e14cb373fe', 'Лимонад', null, 80, '2020-03-16 10:19:29.622000', true, '9ec9ea5a-f9ef-4159-9a9c-bfeedc77ca23', 0);
INSERT INTO product (id, title, description, price, added, available, image, category) VALUES ('304e03f4-ce8c-4f20-9214-3e077f76bb91', 'Хлеб', null, 50, '2020-03-15 22:54:21.184000', true, '290679fa-9812-44c6-aa22-df5689d99e14', 1);
INSERT INTO product (id, title, description, price, added, available, image, category) VALUES ('babc9f03-931e-4b46-97bd-bf8a6a788619', 'Яблоки', null, 150, '2020-03-15 22:54:21.184000', true, '08b39bd2-5357-43f1-aa3e-d1b9ce028e5e', 1);
INSERT INTO product (id, title, description, price, added, available, image, category) VALUES ('a7a2fc2d-060a-434c-b680-cfe7cbd74b0f', 'Молоко', null, 100, '2020-03-15 22:54:21.184000', true, '90a1448a-2989-4882-aa70-7cd97f0bcbf6', 0);
INSERT INTO product (id, title, description, price, added, available, image, category) VALUES ('f19034c6-040a-47c3-ae58-5ba3049b2f20', 'Сок', null, 120, '2020-03-16 10:19:29.622000', true, '0c38d227-e573-451d-8360-1eb038fd7472', 0);
INSERT INTO product (id, title, description, price, added, available, image, category) VALUES ('403ae2c8-8df0-4393-87b7-1928a2289c46', 'Чай', null, 50, '2020-03-16 10:19:29.622000', true, '62b68ec4-7e33-4bce-a27e-cffb558040f1', 0);
INSERT INTO product (id, title, description, price, added, available, image, category) VALUES ('b584b51d-2aa6-4c95-a17b-775a2c7918e9', 'Пончики', null, 10, '2020-03-16 10:19:29.622000', true, '90806402-753e-436b-85c4-cbe84e4644eb', 1);
INSERT INTO product (id, title, description, price, added, available, image, category) VALUES ('71806436-ce5c-49ea-aea0-7ad01c14015e', 'Сыр', null, 200, '2020-03-15 22:54:21.184000', true, 'b85d8706-d663-4311-a15a-fea21e476ee9', 1);

DROP TABLE IF EXISTS shopuser;

CREATE TABLE IF NOT EXISTS shopuser (
    id uuid DEFAULT uuid_generate_v4() UNIQUE NOT NULL CONSTRAINT PK_shopuser PRIMARY KEY,
    phone VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) default NULL::character varying,
    role VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255)
);

INSERT INTO shopuser (id, phone, password, email, first_name, last_name, role) VALUES ('6b718067-e1e4-4202-a7e2-7339ea0d6cb4', 'anonymous', 'anonymous', 'anonymous@supershop.com', 'anonymous', 'anonymous', 'ROLE_CUSTOMER');
INSERT INTO shopuser (id, phone, password, email, first_name, last_name, role) VALUES ('fbe5a8e7-8555-4ee8-bff2-c572447e5f25', '11111111', '$2a$10$38Kk/bXPH19tGktmizpDRulWSREwnBv4pcXhSVkgOD6.esGereVMK', 'admin@supershop.com', 'Admin', 'Admin','ROLE_ADMIN');
INSERT INTO shopuser (id, phone, password, email, first_name, last_name, role) VALUES ('04c8bd30-ba4e-4e82-b996-db907e37a2c6', '22222222', '$2a$10$7kFV55CKBIQb8EYwaHqcKO52X8LB8d/0kaesWZENPOGyBtt3xP/Xi', 'user@supershop.com', 'User', 'User', 'ROLE_CUSTOMER');

DROP TABLE IF EXISTS purchase;

CREATE TABLE IF NOT EXISTS purchase (
    id uuid DEFAULT uuid_generate_v4() UNIQUE NOT NULL CONSTRAINT PK_purchase PRIMARY KEY,
    price DOUBLE PRECISION DEFAULT 0.0 NOT NULL,
    address VARCHAR(255) NOT NULL,
    shopuser uuid NOT NULL CONSTRAINT FK_purchase_users REFERENCES shopuser
);

DROP TABLE IF EXISTS cart_record;

CREATE TABLE IF NOT EXISTS cart_record (
    id uuid DEFAULT uuid_generate_v4() UNIQUE NOT NULL CONSTRAINT PK_cart_record PRIMARY KEY,
    quantity INTEGER DEFAULT 0 NOT NULL,
    price DOUBLE PRECISION DEFAULT 0.0 NOT NULL,
    product uuid NOT NULL CONSTRAINT FK_cart_product REFERENCES product ON UPDATE CASCADE ON DELETE CASCADE,
    purchase uuid NOT NULL CONSTRAINT FK_cart_purchase REFERENCES purchase ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS review;

CREATE TABLE IF NOT EXISTS review (
    id uuid DEFAULT uuid_generate_v4() UNIQUE NOT NULL CONSTRAINT PK_review PRIMARY KEY,
    commentary TEXT NOT NULL,
    shopuser uuid NOT NULL CONSTRAINT FK_review_shopuser REFERENCES shopuser,
    product uuid NOT NULL CONSTRAINT FK_review_product REFERENCES product,
    approved BOOLEAN NOT NULL
);