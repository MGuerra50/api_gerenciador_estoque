package com.inventory.manager.services;

import com.inventory.manager.domain.product.Product;
import com.inventory.manager.domain.product.ProductDTORequest;
import com.inventory.manager.domain.product.ProductDTOResponse;
import com.inventory.manager.domain.product.ProductRepository;
import com.inventory.manager.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto \"" + id + "\" não encontrado")
        );
    }

    public List<ProductDTOResponse> findAll() {
        List<Product> list = productRepository.findAll();
        return list.stream().map(ProductDTOResponse::new).collect(Collectors.toList());
    }

    public ProductDTOResponse createProduct(ProductDTORequest productDTORequest) {
        Product product = new Product();
        product.setCategory(productDTORequest.category());
        product.setName(productDTORequest.name());
        product.setSku(productDTORequest.sku());
        product.setDescription(productDTORequest.description());
        product.setCost_price(productDTORequest.cost_price());
        product.setSelling_price(productDTORequest.selling_price());

        Product savedProduct = productRepository.save(product);

        return new ProductDTOResponse(savedProduct);
    }

    @Transactional
    public ProductDTOResponse updateProduct(Long id, ProductDTORequest dtoRequest) {
        Product product = findById(id);

        product.setCategory(dtoRequest.category());
        product.setName(dtoRequest.name());
        product.setSku(dtoRequest.sku());
        product.setDescription(dtoRequest.description());
        product.setCost_price(dtoRequest.cost_price());
        product.setSelling_price(dtoRequest.selling_price());

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
