alter table im_products
add constraint fk_produtc_users_updated
foreign key (updated_by) references im_user(id);