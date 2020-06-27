package com.example.linkedout.web.models.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyViewModel {

    private long id;
    private String name;
    private String town;
    private String description;
    private double budget;

}
