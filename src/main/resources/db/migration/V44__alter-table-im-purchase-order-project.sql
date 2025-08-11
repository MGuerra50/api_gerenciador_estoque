alter table im_purchase_order
add constraint fk_purchase_order_projects
foreign key (id_project) references im_projects(id);