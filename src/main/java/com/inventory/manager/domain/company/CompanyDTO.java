package com.inventory.manager.domain.company;

public record CompanyDTO(
        Long id,
        String companyName,
        String fantasyName,
        String cnpj,
        String stateRegistration,
        String municipalRegistration,
        String email,
        String phone,
        Long address,
        Long createdByUser,
        Long updatedByUser
) {
    public CompanyDTO(Company company) {
        this(
                company.getId(),
                company.getCompanyName(),
                company.getFantasyName(),
                company.getCnpj(),
                company.getStateRegistration(),
                company.getMunicipalRegistration(),
                company.getEmail(),
                company.getPhone(),
                company.getAddress() != null ? company.getAddress().getId() : null,
                company.getCreatedByUser() != null ? company.getCreatedByUser().getId() : null,
                company.getUpdatedByUser() != null ? company.getUpdatedByUser().getId() : null
        );
    }
}


