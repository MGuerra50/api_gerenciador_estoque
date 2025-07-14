package com.inventory.manager.controller.Product;

import com.inventory.manager.domain.product.ProductDTORequest;
import com.inventory.manager.domain.product.ProductDTOResponse;
import com.inventory.manager.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTOResponse> findProductById (@Valid @PathVariable Long id){
        return ResponseEntity.ok(new ProductDTOResponse(productService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ProductDTOResponse> createProduct (@Valid @RequestBody ProductDTORequest productRequest){
        ProductDTOResponse productDTOResponse = productService.createProduct(productRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(productDTOResponse.id())
                .toUri();
        return ResponseEntity.created(location).body(productDTOResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTOResponse> updateProduct (@PathVariable Long id, @Valid @RequestBody ProductDTORequest productDTORequest){
        return ResponseEntity.ok(productService.updateProduct(id, productDTORequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById (@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
