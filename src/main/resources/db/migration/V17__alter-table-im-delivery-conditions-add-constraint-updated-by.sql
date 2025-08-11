alter table im_delivery_conditions
add constraint fk_delivery_conditions_users_updated
foreign key (updated_by) references im_user(id);