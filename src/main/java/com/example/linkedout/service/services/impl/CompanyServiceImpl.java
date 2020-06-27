package com.example.linkedout.service.services.impl;

import com.example.linkedout.data.entities.Company;
import com.example.linkedout.data.repositories.CompanyRepository;
import com.example.linkedout.service.models.CompanyServiceModel;
import com.example.linkedout.service.services.CompanyService;
import com.example.linkedout.service.services.EmployeeService;
import com.example.linkedout.web.models.binding.CompanyBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final ModelMapper modelMapper;
    private final CompanyRepository companyRepository;
    private final EmployeeService employeeService;

    @Autowired
    public CompanyServiceImpl(ModelMapper modelMapper, CompanyRepository companyRepository, @Lazy EmployeeService employeeService) {
        this.modelMapper = modelMapper;
        this.companyRepository = companyRepository;
        this.employeeService = employeeService;
    }

    @Override
    public void createCompany(CompanyBindingModel companyBindingModel) {
        Company company = this.modelMapper.map(companyBindingModel, Company.class);
        this.companyRepository.saveAndFlush(company);
    }

    @Override
    public boolean isCompanyExist(String name) {
        return this.companyRepository.getCompaniesByName(name) != null;
    }

    @Override
    public List<CompanyServiceModel> getAllCompanies() {
        return this.companyRepository
                .findAll()
                .stream()
                .map(c -> this.modelMapper.map(c, CompanyServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void saveCompany(Company company) {
        this.companyRepository.saveAndFlush(company);
    }

    @Override
    public CompanyServiceModel getCompanyById(long id) {
        return this.modelMapper.map(this.companyRepository.getCompanyById(id), CompanyServiceModel.class);
    }

    @Override
    @Transactional
    public void deleteCompanyById(long id) {
        this.employeeService.setCompanyNullOfAllEmployee(id);
        this.companyRepository.deleteCompanyById(id);
    }

    @Override
    public CompanyServiceModel getCompanyByName(String companyName) {
        return this.modelMapper.map(this.companyRepository.getCompaniesByName(companyName), CompanyServiceModel.class);
    }
}

