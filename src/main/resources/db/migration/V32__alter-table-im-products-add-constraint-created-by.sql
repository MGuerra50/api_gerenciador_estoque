alter table im_products
add constraint fk_produtc_users_created
foreign key (created_by) references im_user(id);