package com.inventory.manager.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.manager.domain.company.Company;
import com.inventory.manager.domain.deliveryConditions.DeliveryConditions;
import com.inventory.manager.domain.paymentConditions.PaymentConditions;
import com.inventory.manager.domain.project.Project;
import com.inventory.manager.domain.purchaseOrder.PurchaseOrder;
import com.inventory.manager.domain.purchaseOrder.PurchaseOrderDTO;
import com.inventory.manager.domain.purchaseOrder.PurchaseOrderRequestDTO;
import com.inventory.manager.domain.users.Users;
import com.inventory.manager.domain.purchaseOrder.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private UsersServices usersServices;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PaymentConditionsService paymentConditionsService;

    @Autowired
    private DeliveryConditionsService deliveryConditionsService;

    public PurchaseOrder findById(Long id) {
        return purchaseOrderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido de compra com id \"" + id + "\" não encontrado."));
    }

    public List<PurchaseOrderDTO> findAll() {
        return purchaseOrderRepository.findAll()
            .stream()
            .map(PurchaseOrderDTO::new)
            .collect(Collectors.toList());
    }

    public PurchaseOrderDTO createPurchaseOrder(PurchaseOrderRequestDTO dto) {
        Users user = usersServices.findById(dto.userId());
        Project project = projectService.findById(dto.project());
        Company company = companyService.findById(dto.company());
        PaymentConditions paymentConditions = paymentConditionsService.findById(dto.paymentConditions());
        DeliveryConditions deliveryConditions = deliveryConditionsService.findById(dto.deliveryConditions());
        
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setDateOfIssu(dto.dateOfIssu());
        purchaseOrder.setOrderStatus(dto.orderStatus());
        purchaseOrder.setProject(project);
        purchaseOrder.setCompany(company);
        purchaseOrder.setPaymentConditions(paymentConditions);
        purchaseOrder.setDeliveryConditions(deliveryConditions);
        purchaseOrder.setTotalIpi(dto.totalIpi());
        purchaseOrder.setTotalIcms(dto.totalIcms());
        purchaseOrder.setSubtotal(dto.subtotal());
        purchaseOrder.setDiscountValue(dto.discountValue());
        purchaseOrder.setDiscountDescription(dto.discountDescription());
        purchaseOrder.setTotalNetValue(dto.totalNetValue());
        purchaseOrder.setGeneralObservations(dto.generalObservations());
        purchaseOrder.setBusinessObservations(dto.businessObservations());
        purchaseOrder.setSupplierBusiness(dto.supplierBusiness());
        purchaseOrder.setCreatedByUser(user);

        PurchaseOrder saved = purchaseOrderRepository.save(purchaseOrder);
        return new PurchaseOrderDTO(saved);
    }

    public PurchaseOrderDTO updatePurchaseOrder(Long id, PurchaseOrderRequestDTO dto) {        
        PurchaseOrder purchaseOrder = findById(id);
        Users user = usersServices.findById(dto.userId());
        Project project = projectService.findById(dto.project());
        Company company = companyService.findById(dto.company());
        PaymentConditions paymentConditions = paymentConditionsService.findById(dto.paymentConditions());
        DeliveryConditions deliveryConditions = deliveryConditionsService.findById(dto.deliveryConditions());
        
        purchaseOrder.setDateOfIssu(dto.dateOfIssu());
        purchaseOrder.setOrderStatus(dto.orderStatus());
        purchaseOrder.setProject(project);
        purchaseOrder.setCompany(company);
        purchaseOrder.setPaymentConditions(paymentConditions);
        purchaseOrder.setDeliveryConditions(deliveryConditions);
        purchaseOrder.setTotalIpi(dto.totalIpi());
        purchaseOrder.setTotalIcms(dto.totalIcms());
        purchaseOrder.setSubtotal(dto.subtotal());
        purchaseOrder.setDiscountValue(dto.discountValue());
        purchaseOrder.setDiscountDescription(dto.discountDescription());
        purchaseOrder.setTotalNetValue(dto.totalNetValue());
        purchaseOrder.setGeneralObservations(dto.generalObservations());
        purchaseOrder.setBusinessObservations(dto.businessObservations());
        purchaseOrder.setSupplierBusiness(dto.supplierBusiness());
        purchaseOrder.setCreatedByUser(user);

        PurchaseOrder updated = purchaseOrderRepository.save(purchaseOrder);
        return new PurchaseOrderDTO(updated);
    }

    public void deleteById(Long id) {
        if (!purchaseOrderRepository.existsById(id)) {
            throw new RuntimeException("Pedido de compra com id \"" + id + "\" não encontrado.");
        }
        purchaseOrderRepository.deleteById(id);
    }
}
