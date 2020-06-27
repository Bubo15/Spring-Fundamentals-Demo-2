package com.example.linkedout.helper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Helper {

    private static final ModelMapper modelMapper = new ModelMapper();


    public static class Model {

        public static void addAttributesAndView(Map<String, Object> attrs, ModelAndView modelAndView, String path) {
            attrs.forEach((key, value) -> {
                if (modelAndView.getModel().get(key) == null) {
                    modelAndView.addObject(key, value);
                }
            });
            modelAndView.setViewName(path);
        }
    }

    public static class Converter {

        public static List<?> convertListFromServiceModelToView(List<?> serviceModels, Class view) {
            return serviceModels
                    .stream()
                    .map(companyServiceModel -> modelMapper.map(companyServiceModel, view))
                    .collect(Collectors.toList());
        }

        public static Object convertFromServiceModelToView(Object serviceModels, Class view) {
            return modelMapper.map(serviceModels, view);
        }

    }

    public static class Error {

        public static boolean setViewAndIfHasErrRedirectAttr(ModelAndView modelAndView,
                                                             BindingResult result,
                                                             RedirectAttributes redirectAttributes,
                                                             Map<String, Object> attrs,
                                                             String path) {
            if (result.hasErrors()){
                attrs.forEach(redirectAttributes::addFlashAttribute);
                modelAndView.setView(new RedirectView("/employees/add"));
                return true;
            }

            modelAndView.setView(new RedirectView(path));
            return false;
        }
    }
}
