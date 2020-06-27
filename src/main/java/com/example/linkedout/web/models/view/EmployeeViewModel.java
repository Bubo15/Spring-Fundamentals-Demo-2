package com.example.linkedout.web.models.view;

import com.example.linkedout.data.entities.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeViewModel {

    private long id;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String educationLevel;
    private double salary;
    private Company company;

}
