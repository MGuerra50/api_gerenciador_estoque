alter table im_purchase_order_item
add constraint fk_im_purchase_order_item_users_updated
foreign key (updated_by) references im_user(id);