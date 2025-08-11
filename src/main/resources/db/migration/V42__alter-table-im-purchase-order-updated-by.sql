alter table im_purchase_order
add constraint fk_purchase_order_users_updated
foreign key (updated_by) references im_user(id);