package ru.sushi.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ru.sushi.demo.service.MenuService;

/**
 * @author "Timohin Igor"
 */
@Controller
public class MenuFoodTypeController {

    @Autowired
    MenuService MenuService;

    @GetMapping("/admin/listmenufoodtype/{id}")
    public ModelAndView listMenuFoodTypeGet(@PathVariable int id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listmenufoodtype", MenuService.getMenuFoodTypeList(id));
        modelAndView.setViewName("listmenufoodtype");
        return modelAndView;
    }
}