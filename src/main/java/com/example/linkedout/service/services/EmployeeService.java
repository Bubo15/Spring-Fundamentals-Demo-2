package com.example.linkedout.service.services;

import com.example.linkedout.service.models.EmployeeServiceModel;
import com.example.linkedout.web.models.binding.EmployeeBindingModel;

import java.util.List;

public interface EmployeeService {

    void create(EmployeeBindingModel employeeBindingModel);

    List<EmployeeServiceModel> getAllEmployees();

    EmployeeServiceModel getEmployeeById(long id);

    void deleteEmployeeById(long id);

    void setCompanyNullOfAllEmployee(long id);
}
