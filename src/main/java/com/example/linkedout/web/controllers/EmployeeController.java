package com.example.linkedout.web.controllers;

import com.example.linkedout.helper.Helper;
import com.example.linkedout.service.services.CompanyService;
import com.example.linkedout.service.services.EmployeeService;
import com.example.linkedout.web.models.binding.EmployeeBindingModel;
import com.example.linkedout.web.models.view.CompanyViewModel;
import com.example.linkedout.web.models.view.EmployeeViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private static final String BINDING_RESULT_NAME = "org.springframework.validation.BindingResult.";


    private final CompanyService companyService;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(CompanyService companyService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public ModelAndView getAdd(ModelAndView modelAndView, Model model){
        List<CompanyViewModel> list = (List<CompanyViewModel>) Helper.Converter.convertListFromServiceModelToView(this.companyService.getAllCompanies(), CompanyViewModel.class);

        Helper.Model.addAttributesAndView(new HashMap<>() {{
            put("companies", list);
            put("employee", new EmployeeBindingModel());
        }}, modelAndView, "employee-add");

        modelAndView.addAllObjects(model.asMap());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView createEmployee(@Valid @ModelAttribute("employee") EmployeeBindingModel employeeBindingModel,
                                       BindingResult result,
                                       ModelAndView modelAndView,
                                       RedirectAttributes redirectAttributes){

        if (Helper.Error.setViewAndIfHasErrRedirectAttr(modelAndView, result, redirectAttributes, new LinkedHashMap<>() {{
                    put(BINDING_RESULT_NAME + "employee", result);
                    put("employee", employeeBindingModel); }}, "/")) {
            return modelAndView;
        }

        this.employeeService.create(employeeBindingModel);
        return modelAndView;
    }

    @GetMapping("/all")
    public ModelAndView getAll(ModelAndView modelAndView){
        List<EmployeeViewModel> list = (List<EmployeeViewModel>) Helper.Converter.convertListFromServiceModelToView(this.employeeService.getAllEmployees(), EmployeeViewModel.class);
        Helper.Model.addAttributesAndView(new HashMap<>() {{ put("employees", list); }}, modelAndView, "employee-all");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getEmployeeDetails(@PathVariable long id,
                                           ModelAndView modelAndView){
        EmployeeViewModel employeeViewModel = (EmployeeViewModel) Helper.Converter.convertFromServiceModelToView(this.employeeService.getEmployeeById(id), EmployeeViewModel.class);
        Helper.Model.addAttributesAndView(new HashMap<>() {{ put("employee", employeeViewModel); }}, modelAndView, "employee-details");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteEmployee(@PathVariable long id,
                                           ModelAndView modelAndView){
        this.employeeService.deleteEmployeeById(id);
        modelAndView.setView(new RedirectView("/"));
        return modelAndView;
    }

}
