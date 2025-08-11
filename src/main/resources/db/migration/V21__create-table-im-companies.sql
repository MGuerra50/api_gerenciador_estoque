create table im_companies (
	id serial primary key,
	company_name varchar(255) not null,
	fantasy_name varchar(255) not null,
	cnpj varchar(14) not null,
	state_registration varchar(9) not null,
	municipal_registration varchar(15) not null,
	email VARCHAR(200) unique not null,
	phone varchar(15),
	id_address INT,
	created_by INT,
	updated_by INT,
	created_at timestamp default current_timestamp,
	updated_at timestamp
);