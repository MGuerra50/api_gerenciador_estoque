CREATE TABLE im_positions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

ALTER TABLE im_user
ADD COLUMN position_id INT;

ALTER TABLE im_user
ADD CONSTRAINT fk_position_user
FOREIGN KEY (position_id)
REFERENCES im_positions(id);


CREATE TABLE im_warehouses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    endereco VARCHAR(355) NOT NULL
);

CREATE TABLE im_locations (
    id SERIAL PRIMARY KEY,
    warehouses_id INT,
    CONSTRAINT fk_locations_warehouses FOREIGN KEY (warehouses_id) REFERENCES im_warehouses(id),
    location_code VARCHAR(255) NOT NULL
);

CREATE TABLE im_categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE im_products (
    id SERIAL PRIMARY KEY,
    categories_id INT,
    CONSTRAINT fk_categories_products FOREIGN KEY (categories_id) REFERENCES im_categories(id),
    sku VARCHAR(155) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    cost_price DECIMAL(10, 2) NOT NULL,
    selling_price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE im_stock (
    products_id INT,
    CONSTRAINT fk_products_stock FOREIGN KEY (products_id) REFERENCES im_products(id),
    locations_id INT,
    CONSTRAINT fk_locations_stock FOREIGN KEY (locations_id) REFERENCES im_locations(id),
    PRIMARY KEY (products_id, locations_id),
    amount INT NOT NULL
);

CREATE TABLE im_suppliers (
    id SERIAL PRIMARY KEY,
    fantasy_name VARCHAR(255) NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    cnpj VARCHAR(15) UNIQUE NOT NULL
);

CREATE TYPE moviment_type AS ENUM ('INPUT', 'OUTPUT','ADJUSTMENT', 'TRANSFER');

CREATE TABLE im_moviments (
    id SERIAL PRIMARY KEY,
    user_id INT,
    CONSTRAINT fk_user_moviments FOREIGN KEY (user_id) REFERENCES im_user(id),
    products_id INT,
    CONSTRAINT fk_products_moviments FOREIGN KEY (products_id) REFERENCES im_products(id),
    locations_id INT,
    CONSTRAINT fk_locations_moviments FOREIGN KEY (locations_id) REFERENCES im_locations(id),
    suppliers_id INT,
    CONSTRAINT fk_suppliers_moviments FOREIGN KEY (suppliers_id) REFERENCES im_suppliers(id),
    type moviment_type NOT NULL,
    number_of_items_in_transaction INT NOT NULL,
    date_time TIMESTAMP
);