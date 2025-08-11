alter table im_purchase_order
add constraint fk_purchase_order_users_created
foreign key (created_by) references im_user(id);