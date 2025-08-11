alter table im_purchase_order_item
add constraint fk_purchase_order_item_users_created
foreign key (created_by) references im_user(id);