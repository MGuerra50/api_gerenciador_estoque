alter table im_purchase_order
add constraint fk_purchase_order_company
foreign key (id_company) references im_companies(id);