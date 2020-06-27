package com.example.linkedout.service.services;

import com.example.linkedout.data.entities.Company;
import com.example.linkedout.service.models.CompanyServiceModel;
import com.example.linkedout.web.models.binding.CompanyBindingModel;
import com.example.linkedout.web.models.view.CompanyViewModel;

import java.util.List;

public interface CompanyService {

    void createCompany(CompanyBindingModel companyBindingModel);

    boolean isCompanyExist(String name);

    List<CompanyServiceModel> getAllCompanies();

    void saveCompany(Company company);

    CompanyServiceModel getCompanyById(long id);

    void deleteCompanyById(long id);

    CompanyServiceModel getCompanyByName(String companyName);
}
