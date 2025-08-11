create table im_projects (
	id serial primary key,
	code varchar (100) unique not null,
	name varchar (255) not null,
	description text,
	start_date timestamp not null,
	end_date timestamp not null,
	budget decimal(20, 2),
	status status_project,
	created_by INT,
	updated_by INT,
	created_at timestamp default current_timestamp,
	updated_at timestamp
);