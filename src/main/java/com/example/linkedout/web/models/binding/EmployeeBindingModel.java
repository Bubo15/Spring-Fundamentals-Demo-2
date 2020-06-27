package com.example.linkedout.web.models.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeBindingModel {

    private long id;

    @NotNull(message = "Birth Date can't be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotNull(message = "Education Level can't be null")
    private String educationLevel;

    @Length(min = 2, message = "First name must be at least 2 characters.")
    @NotNull(message = "First name can't be null")
    private String firstName;

    @Length(min = 2, message = "Last name must be at least 2 characters.")
    @NotNull(message = "Last name can't be null")
    private String lastName;

    @NotEmpty(message = "Job Title can't be empty")
    private String jobTitle;

    @Min(value = 0, message = "Salary must be positive")
    private double salary;

    @NotNull(message = "Company Name can't be null")
    private String companyName;
}
