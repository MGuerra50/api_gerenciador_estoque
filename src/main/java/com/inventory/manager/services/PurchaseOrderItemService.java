package com.inventory.manager.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.manager.domain.product.Product;
import com.inventory.manager.domain.purchaseOrder.PurchaseOrder;
import com.inventory.manager.domain.purchaseOrderItem.PurchaseOrderItem;
import com.inventory.manager.domain.purchaseOrderItem.PurchaseOrderItemDTO;
import com.inventory.manager.domain.purchaseOrderItem.PurchaseOrderItemRequestDTO;
import com.inventory.manager.domain.users.Users;
import com.inventory.manager.domain.purchaseOrderItem.PurchaseOrderItemRepository;

@Service
public class PurchaseOrderItemService {
    @Autowired
    private PurchaseOrderItemRepository purchaseOrderItemRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UsersServices usersService;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    public PurchaseOrderItem findById(Long id) {
        return purchaseOrderItemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item de pedido de compra com id \"" + id + "\" não encontrado."));
    }
    
    public List<PurchaseOrderItemDTO> findAll() {
        return purchaseOrderItemRepository.findAll().stream().map(PurchaseOrderItemDTO::new)
            .collect(Collectors.toList());
    }

    public PurchaseOrderItemDTO createPurchaseOrderItem(PurchaseOrderItemRequestDTO dto) {
        Product product = productService.findById(dto.productId());
        Users user = usersService.findById(dto.user());
        PurchaseOrder purchaseOrder = purchaseOrderService.findById(dto.purchaseOrderId());

        PurchaseOrderItem item = new PurchaseOrderItem();

        item.setQuantity(dto.quantity());
        item.setUnitPrice(dto.unitPrice());
        item.setLineTotal(dto.lineTotal());
        item.setProduct(product);
        item.setCreatedByUser(user);
        item.setPurchaseOrder(purchaseOrder);
        
        PurchaseOrderItem saved = purchaseOrderItemRepository.save(item);
        return new PurchaseOrderItemDTO(saved);
    }

    public PurchaseOrderItemDTO updatePurchaseOrderItem(Long id, PurchaseOrderItemRequestDTO purchaseOrderItem) {
        Product product = productService.findById(purchaseOrderItem.productId());
        Users user = usersService.findById(purchaseOrderItem.user());
        PurchaseOrder purchaseOrder = purchaseOrderService.findById(purchaseOrderItem.purchaseOrderId());
        
        PurchaseOrderItem item = findById(id);

        item.setProduct(product);
        item.setPurchaseOrder(purchaseOrder);
        item.setQuantity(purchaseOrderItem.quantity());
        item.setUnitPrice(purchaseOrderItem.unitPrice());
        item.setLineTotal(purchaseOrderItem.lineTotal());
        item.setUpdatedByUser(user);
        item.setUpdatedAt(LocalDateTime.now());

        PurchaseOrderItem saved = purchaseOrderItemRepository.save(item);
        return new PurchaseOrderItemDTO(saved);
    }

    public void deleteById(Long id) {
        if (!purchaseOrderItemRepository.existsById(id)) {
            throw new RuntimeException("Item de pedido de compra com id \"" + id + "\" não encontrado.");
        }
        purchaseOrderItemRepository.deleteById(id);
    }
}
