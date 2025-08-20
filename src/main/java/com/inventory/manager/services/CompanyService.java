package com.inventory.manager.services;

import com.inventory.manager.domain.address.Address;
import com.inventory.manager.domain.company.Company;
import com.inventory.manager.domain.company.CompanyRequestCreateDTO;
import com.inventory.manager.domain.company.CompanyRepository;
import com.inventory.manager.domain.company.CompanyRequestUpdateDTO;
import com.inventory.manager.domain.users.Users;
import com.inventory.manager.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressService addressService;
    @Autowired
    UsersServices usersServices;

    public List<CompanyRequestCreateDTO> findAll() {
        List<Company> companyList = companyRepository.findAll();
        return companyList.stream().map(CompanyRequestCreateDTO::new).collect(Collectors.toList());
    }

    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Company id \"" + id + "\" não encontrada.")
        );
    }

    @Transactional
    public CompanyRequestCreateDTO createCompany (CompanyRequestCreateDTO dto){
        Address address = addressService.findById(dto.address());
        Users users = usersServices.findById(dto.createdByUser());
        Company company = new Company();
        company.setCompanyName(dto.companyName());
        company.setFantasyName(dto.fantasyName());
        company.setCnpj(dto.cnpj());
        company.setStateRegistration(dto.stateRegistration());
        company.setMunicipalRegistration(dto.municipalRegistration());
        company.setEmail(dto.email());
        company.setPhone(dto.phone());
        company.setAddress(address);
        company.setCreatedByUser(users);

        Company savedCompany = companyRepository.save(company);
        return new CompanyRequestCreateDTO(savedCompany);
    }

    @Transactional
    public CompanyRequestUpdateDTO updateCompany (Long id, CompanyRequestUpdateDTO dto){
        Company company = findById(id);
        Address address = addressService.findById(dto.address());
        Users users = usersServices.findById(dto.updatedByUser());

        company.setCompanyName(dto.companyName());
        company.setFantasyName(dto.fantasyName());
        company.setCnpj(dto.cnpj());
        company.setStateRegistration(dto.stateRegistration());
        company.setMunicipalRegistration(dto.municipalRegistration());
        company.setEmail(dto.email());
        company.setPhone(dto.phone());
        company.setAddress(address);
        company.setUpdatedByUser(users);
        Company savedCompany = companyRepository.save(company);

        return new CompanyRequestUpdateDTO(savedCompany);
    }

    public void deleteById(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Empresa \"" + id + "\" não encontrada!");
        }
        companyRepository.deleteById(id);
    }
}
