package com.inventory.manager.domain.company;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CompanyRequestCreateDTO(String companyName,
                                      String fantasyName,
                                      @NotNull String cnpj,
                                      String stateRegistration,
                                      String municipalRegistration,
                                      @Email String email,
                                      String phone,
                                      Long address,
                                      Long createdByUser) {

    public CompanyRequestCreateDTO(Company company) {
        this(company.getCompanyName(),
                company.getFantasyName(),
                company.getCnpj(),
                company.getStateRegistration(),
                company.getMunicipalRegistration(),
                company.getEmail(),
                company.getPhone(),
                company.getAddress().getId(),
                company.getCreatedByUser().getId());
    }
}
