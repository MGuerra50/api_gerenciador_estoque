create table im_addresses (
	id serial primary key,
	street VARCHAR(255) not null,
	number VARCHAR(20),
	complement VARCHAR(255),
	neighborhood VARCHAR(255) not null,
	city VARCHAR(150) not null,
	state VARCHAR(150) not null,
	postal_code VARCHAR(20) not null,
	created_at TIMESTAMP default CURRENT_TIMESTAMP,
	updated_at TIMESTAMP,
	created_by INT,
	updated_by INT
);

alter table im_addresses
add constraint fk_addresses_users_created
foreign key (created_by) references im_user(id);

alter table im_addresses
add constraint fk_addresses_users_updated
foreign key (updated_by) references im_user(id);

alter table im_warehouses
add column addresses_id INT;

alter table im_warehouses
drop column endereco;

alter table im_warehouses
add constraint fk_warehouses_addresses
foreign key (addresses_id) references im_addresses(id);