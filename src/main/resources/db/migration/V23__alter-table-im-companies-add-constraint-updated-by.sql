alter table im_companies
add constraint fk_companies_users_updated
foreign key (updated_by) references im_user(id);