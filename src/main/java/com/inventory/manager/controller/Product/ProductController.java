package com.inventory.manager.controller.Product;

import com.inventory.manager.domain.product.ProductDTORequest;
import com.inventory.manager.domain.product.ProductDTOResponse;
import com.inventory.manager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTOResponse>> findAll (){
        List<ProductDTOResponse> findAllProducts = productService.findAll();
        return ResponseEntity.ok(findAllProducts);
    }

}
