create table im_delivery_conditions (
	id serial primary key,
	code varchar(10) unique not null,
	description varchar (255) not null,
	is_active boolean default true,
	created_by INT,
	updated_by INT,
	created_at timestamp default current_timestamp,
	updated_at timestamp
);