alter table im_companies
add constraint fk_companies_address
foreign key (id_address) references im_addresses(id);