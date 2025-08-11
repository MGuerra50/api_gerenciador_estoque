alter table im_purchase_order_item
add constraint fk_purchase_order_item_products
foreign key (id_product) references im_products(id);