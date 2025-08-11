create table im_purchase_order_item (
	id serial primary key,
	id_product INT not null,
	id_purchase_order INT not null,
	quantity decimal(15, 2) not null,
	unit_price decimal(25, 2) not null,
	line_total decimal(25, 2) not null,
	created_by INT,
	updated_by INT,
	created_at timestamp default current_timestamp,
	updated_at timestamp
);