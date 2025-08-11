alter table im_products
add constraint fk_produtc_suppliers
foreign key (id_supplier) references im_suppliers(id);