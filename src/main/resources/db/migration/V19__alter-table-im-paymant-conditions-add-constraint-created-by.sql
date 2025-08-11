alter table im_paymant_conditions
add constraint fk_paymant_conditions_users_created
foreign key (created_by) references im_user(id);