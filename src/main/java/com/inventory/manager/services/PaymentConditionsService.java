package com.inventory.manager.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.manager.domain.paymentConditions.PaymentConditions;
import com.inventory.manager.domain.paymentConditions.PaymentConditionsDTO;
import com.inventory.manager.domain.paymentConditions.PaymentConditionsRepository;
import com.inventory.manager.domain.paymentConditions.PaymentConditionsRequestDTO;

@Service
public class PaymentConditionsService {
    @Autowired
    private PaymentConditionsRepository paymentConditionsRepository;

    public List<PaymentConditionsDTO> findAll() {
        return paymentConditionsRepository.findAll().stream().map(PaymentConditionsDTO::new).collect(Collectors.toList());
    }

    public PaymentConditions findById(Long id) {
        return paymentConditionsRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Condição de pagamento com id \"" + id + "\" não encontrada.")
        );
    }

    public PaymentConditionsDTO createPaymentConditions(PaymentConditionsRequestDTO dto) {
        PaymentConditions paymentConditions = new PaymentConditions();
        paymentConditions.setCode(dto.code());
        paymentConditions.setDescription(dto.description());
        paymentConditions.setDaysToMaturity(dto.daysToMaturity());
        paymentConditions.setIsActive(true);
        PaymentConditions saved = paymentConditionsRepository.save(paymentConditions);
        return new PaymentConditionsDTO(saved);
    }

    public PaymentConditionsDTO updatePaymentConditions(Long id, PaymentConditionsRequestDTO dto) {
        PaymentConditions paymentConditions = findById(id);

        paymentConditions.setCode(dto.code());
        paymentConditions.setDescription(dto.description());
        paymentConditions.setDaysToMaturity(dto.daysToMaturity());

        PaymentConditions saved = paymentConditionsRepository.save(paymentConditions);
        return new PaymentConditionsDTO(saved);
    }

    public void deleteById(Long id) {
        if (!paymentConditionsRepository.existsById(id)) {
            throw new RuntimeException("Condição de pagamento com id \"" + id + "\" não encontrada.");
        }
        paymentConditionsRepository.deleteById(id);
    }
}
