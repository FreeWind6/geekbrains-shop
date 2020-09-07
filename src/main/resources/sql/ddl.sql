CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE IF NOT EXISTS image (
  id UUID NOT NULL UNIQUE CONSTRAINT PK_image PRIMARY KEY,
  name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS product (
  id UUID NOT NULL UNIQUE CONSTRAINT PK_product PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  price DOUBLE PRECISION NOT NULL,
  added TIMESTAMP DEFAULT now() NOT NULL,
  available BOOLEAN DEFAULT FALSE NOT NULL,
  image UUID CONSTRAINT FK_product_image REFERENCES image ON UPDATE CASCADE ON DELETE CASCADE,
  category INTEGER NOT NULL,
  description TEXT
);

CREATE TABLE IF NOT EXISTS shopuser (
  id UUID NOT NULL UNIQUE CONSTRAINT PK_shopuser PRIMARY KEY,
  phone VARCHAR(10) UNIQUE NOT NULL,
  password VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE,
  role VARCHAR(100) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS purchase (
  id UUID NOT NULL UNIQUE CONSTRAINT PK_purchase PRIMARY KEY,
  price DOUBLE PRECISION NOT NULL,
  shopuser UUID NOT NULL CONSTRAINT fk_purchase_shopuser REFERENCES shopuser ON UPDATE CASCADE ON DELETE CASCADE,
  address VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS cart_record (
  id UUID NOT NULL UNIQUE CONSTRAINT PK_cart_record PRIMARY KEY,
  quantity INTEGER NOT NULL,
  price DOUBLE PRECISION NOT NULL,
  product UUID NOT NULL CONSTRAINT FK_cart_record_product REFERENCES product,
  purchase UUID NOT NULL CONSTRAINT FK_cart_record_purchase REFERENCES purchase
);

INSERT INTO public.image (id, name) VALUES ('a15cfa1b-4d39-4da0-af0e-64a496d9cf7b', 'apple.png');
INSERT INTO public.image (id, name) VALUES ('66a0b825-71cd-449e-80df-64396d0185e3', 'bread.png');
INSERT INTO public.image (id, name) VALUES ('913037af-f306-4be2-bbb1-070b76da734b', 'cheese.png');
INSERT INTO public.image (id, name) VALUES ('6357c3a0-dca1-4c19-bfbb-c54607bbe1d5', 'donut.png');
INSERT INTO public.image (id, name) VALUES ('5232a3dc-2063-4747-879d-de3a4a2f10c5', 'juice.png');
INSERT INTO public.image (id, name) VALUES ('063ac95a-b18d-43a2-a494-0b716cbaca67', 'lemonade.png');
INSERT INTO public.image (id, name) VALUES ('4b176f5b-aa37-4eaf-9e16-52f99915d455', 'milk.png');
INSERT INTO public.image (id, name) VALUES ('e121cb29-002e-4151-8a33-9211b7ce6564', 'tea.png');

INSERT INTO public.product (id, title, price, added, available, image, category, description) VALUES ('723835ca-ccf3-4d04-8392-8bbe89420822', 'Кофе', 150, '2020-08-27 18:29:48.016592', true, 'e121cb29-002e-4151-8a33-9211b7ce6564', 0, null);
INSERT INTO public.product (id, title, price, added, available, image, category, description) VALUES ('00fb8fd6-8d1a-4a83-b76d-68c0ae00e897', 'Яблоки', 100, '2020-08-27 18:29:48.016592', true, 'a15cfa1b-4d39-4da0-af0e-64a496d9cf7b', 1, null);
INSERT INTO public.product (id, title, price, added, available, image, category, description) VALUES ('04770fe6-fc61-4bfb-9419-184357586732', 'Молоко', 200, '2020-08-27 18:29:48.016592', true, '4b176f5b-aa37-4eaf-9e16-52f99915d455', 0, null);
INSERT INTO public.product (id, title, price, added, available, image, category, description) VALUES ('25088ce6-2141-4855-8a1f-8e591bde49d4', 'Сыр', 500, '2020-08-27 18:29:48.016592', true, '913037af-f306-4be2-bbb1-070b76da734b', 1, null);