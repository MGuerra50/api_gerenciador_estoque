alter table im_products
add constraint fk_produtc_second_suppliers
foreign key (id_second_supplier) references im_suppliers(id);