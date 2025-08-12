package com.inventory.manager.services;

import com.inventory.manager.domain.category.Category;
import com.inventory.manager.domain.product.*;
import com.inventory.manager.domain.supplier.Supplier;
import com.inventory.manager.domain.users.Users;
import com.inventory.manager.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    UsersServices usersServices;

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto \"" + id + "\" não encontrado")
        );
    }

    public List<ProductDTOResponse> findAll() {
        List<Product> list = productRepository.findAll();
        return list.stream().map(ProductDTOResponse::new).collect(Collectors.toList());
    }

    public ProductDTOResponse createProduct(ProductDTORequest dto) {
        Users user = usersServices.findById(dto.createdBy());
        Supplier firstSupplier = supplierService.findById(dto.idSupplier());
        Supplier secondSupplier = null;
        if(dto.idSecondSupplier() != null){
            secondSupplier = supplierService.findById(dto.idSecondSupplier());
        }

        Product product = new Product();
        Category category = categoryService.findById(dto.categoryId());
        product.setCategory(category);
        product.setName(dto.name());
        product.setSku(dto.sku());
        product.setDescription(dto.description());
        product.setCost_price(dto.cost_price());
        product.setSelling_price(dto.selling_price());
        product.setIs_active(true);
        product.setBrand(dto.brand());
        product.setModel(dto.model());
        product.setVersion(dto.version());
        product.setSupplier(firstSupplier);
        product.setSecondSupplier(secondSupplier);
        product.setColor(dto.color());
        product.setDimensions(dto.dimensions());
        product.setGrossWeight(dto.grossWeight());
        product.setNetWeight(dto.netWeight());
        product.setProductDetails(dto.productDetails());
        product.setUnitOfMeasurement(dto.unitOfMeasurement());
        product.setUnitOfMeasurementDescription(dto.unitOfMeasurementDescription());
        product.setAverageUnitPrice(dto.averageUnitPrice());
        product.setCreatedByUser(user);
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);

        return new ProductDTOResponse(savedProduct);
    }

    @Transactional
    public ProductDTOResponse updateProduct(Long id, ProductDTORequestUpdate dto) {
        Supplier firstSupplier = supplierService.findById(dto.idSupplier());
        Supplier secondSupplier = null;
        if(dto.idSecondSupplier() != null){
            secondSupplier = supplierService.findById(dto.idSecondSupplier());
        }
        Users user = usersServices.findById(dto.updatedBy());
        Product product = findById(id);
        Category category = categoryService.findById(dto.categoryId());
        product.setCategory(category);
        product.setName(dto.name());
        product.setSku(dto.sku());
        product.setDescription(dto.description());
        product.setCost_price(dto.cost_price());
        product.setSelling_price(dto.selling_price());
        product.setBrand(dto.brand());
        product.setModel(dto.model());
        product.setVersion(dto.version());
        product.setSupplier(firstSupplier);
        product.setSecondSupplier(secondSupplier);
        product.setColor(dto.color());
        product.setDimensions(dto.dimensions());
        product.setGrossWeight(dto.grossWeight());
        product.setNetWeight(dto.netWeight());
        product.setProductDetails(dto.productDetails());
        product.setUnitOfMeasurement(dto.unitOfMeasurement());
        product.setUnitOfMeasurementDescription(dto.unitOfMeasurementDescription());
        product.setAverageUnitPrice(dto.averageUnitPrice());
        product.setUpdatedByUser(user);
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);

        return new ProductDTOResponse(savedProduct);
    }

    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produto \"" + id + "\" não encontrado");
        }
        productRepository.deleteById(id);
    }
}
