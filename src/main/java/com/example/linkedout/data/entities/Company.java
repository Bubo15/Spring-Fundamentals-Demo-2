package com.example.linkedout.data.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity {

    @Column(name = "budget")
    private double budget;

    @Column(name = "description" ,columnDefinition = "text")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "town")
    private String town;

    @OneToMany(mappedBy = "company", targetEntity = Employee.class, fetch = FetchType.EAGER)
    private List<Employee> employees;
}