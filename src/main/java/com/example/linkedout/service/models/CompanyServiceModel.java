package com.example.linkedout.service.models;


import com.example.linkedout.data.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyServiceModel {

    private long id;
    private double budget;
    private String description;
    private String name;
    private String town;
    private List<Employee> employees;
}
