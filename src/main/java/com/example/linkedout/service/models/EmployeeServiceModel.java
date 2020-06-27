package com.example.linkedout.service.models;

import com.example.linkedout.data.entities.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeServiceModel {

    private long id;
    private LocalDate birthDate;
    private String educationLevel;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private double salary;
    private Company company;
}
