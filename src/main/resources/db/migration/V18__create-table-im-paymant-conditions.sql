create table im_paymant_conditions (
	id serial primary key,
	code varchar(100) not null unique,
	description varchar(255) not null,
	days_to_maturity INT,
	is_active boolean default true,
	created_by INT,
	updated_by INT,
	created_at timestamp default current_timestamp,
	updated_at timestamp
);