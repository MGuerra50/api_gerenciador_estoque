alter table im_paymant_conditions
add constraint fk_paymant_conditions_users_updated
foreign key (updated_by) references im_user(id);