package com.example.linkedout.web.models.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyBindingModel {

    private long id;
    private double budget;
    private String description;
    private String name;
    private String town;
}
