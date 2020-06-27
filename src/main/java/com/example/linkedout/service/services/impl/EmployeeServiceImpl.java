package com.example.linkedout.service.services.impl;

import com.example.linkedout.data.entities.Company;
import com.example.linkedout.data.entities.Employee;
import com.example.linkedout.data.repositories.EmployeeRepository;
import com.example.linkedout.service.models.EmployeeServiceModel;
import com.example.linkedout.service.services.CompanyService;
import com.example.linkedout.service.services.EmployeeService;
import com.example.linkedout.web.models.binding.EmployeeBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyService companyService, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }


    @Override
    @Transactional
    public void create(EmployeeBindingModel employeeBindingModel) {
        Employee employee = this.modelMapper.map(employeeBindingModel, Employee.class);
        Company company =
                this.modelMapper.map(this.companyService.getCompanyByName(employeeBindingModel.getCompanyName()), Company.class);

        employee.setCompany(company);
        company.setEmployees(List.of(employee));

        this.employeeRepository.saveAndFlush(employee);
    }

    @Override
    public List<EmployeeServiceModel> getAllEmployees() {
        return this.employeeRepository
                .findAll()
                .stream()
                .map(employee -> this.modelMapper.map(employee, EmployeeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeServiceModel getEmployeeById(long id) {
        return this.modelMapper.map(this.employeeRepository.getEmployeeById(id), EmployeeServiceModel.class);
    }

    @Override
    @Transactional
    public void deleteEmployeeById(long id) {
        this.employeeRepository.deleteEmployeeById(id);
    }

    @Override
    public void setCompanyNullOfAllEmployee(long id) {
        this.employeeRepository.getAllByCompanyId(id).forEach(employee -> employee.setCompany(null));
    }
}
