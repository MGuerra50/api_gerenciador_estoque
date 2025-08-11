alter table im_purchase_order
add constraint fk_purchase_order_users_approvet
foreign key (approvet_by) references im_user(id);