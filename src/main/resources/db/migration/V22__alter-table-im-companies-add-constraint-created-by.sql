alter table im_companies
add constraint fk_companies_users_created
foreign key (created_by) references im_user(id);