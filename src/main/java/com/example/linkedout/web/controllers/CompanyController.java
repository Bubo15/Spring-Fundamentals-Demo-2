package com.example.linkedout.web.controllers;

import com.example.linkedout.helper.Helper;
import com.example.linkedout.service.services.CompanyService;
import com.example.linkedout.web.models.binding.CompanyBindingModel;
import com.example.linkedout.web.models.view.CompanyViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyController(CompanyService companyService, ModelMapper modelMapper) {
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ModelAndView getCompanyAdd(ModelAndView modelAndView) {
        modelAndView.setViewName("company-add");
        return modelAndView;
    }

    @GetMapping("/all")
    public ModelAndView getAllCompany(ModelAndView modelAndView) {
        List<CompanyViewModel> list = (List<CompanyViewModel>) Helper.Converter.convertListFromServiceModelToView(this.companyService.getAllCompanies(), CompanyViewModel.class);
        Helper.Model.addAttributesAndView(new HashMap<>() {{ put("companies", list); }}, modelAndView, "company-all");
        return modelAndView;
    }

    @GetMapping("/company/{name}")
    public boolean isCompanyExist(@PathVariable String name) {
        return this.companyService.isCompanyExist(name);
    }

    @PostMapping("/add")
    public ModelAndView createCompany(@ModelAttribute() CompanyBindingModel companyBindingModel,
                                      ModelAndView modelAndView) {
        this.companyService.createCompany(companyBindingModel);
        modelAndView.setView(new RedirectView("/"));
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getCompanyDetails(@PathVariable long id, ModelAndView modelAndView){
        CompanyViewModel companyViewModel = (CompanyViewModel) Helper.Converter.convertFromServiceModelToView(this.companyService.getCompanyById(id), CompanyViewModel.class);
        Helper.Model.addAttributesAndView(new HashMap<>() {{ put("company", companyViewModel); }}, modelAndView, "company-details");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCompany(@PathVariable long id,
                                       ModelAndView modelAndView){
        this.companyService.deleteCompanyById(id);
        modelAndView.setView(new RedirectView("/"));
        return modelAndView;
    }
}
