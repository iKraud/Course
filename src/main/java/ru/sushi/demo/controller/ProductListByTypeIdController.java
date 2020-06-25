package ru.sushi.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.sushi.demo.model.Food;
import ru.sushi.demo.model.Menu;
import ru.sushi.demo.service.ProductListByTypeIdService;

@Controller
public class ProductListByTypeIdController {

    @Autowired
    ProductListByTypeIdService productListByTypeIdService;

    @GetMapping("/client/listfoodbytypeid/{id}")
    public ModelAndView listFoodByTypeIdGet(@PathVariable int id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listFoodByTypeId", productListByTypeIdService.getProductListById(id));
        modelAndView.addObject("pageTitle", "Продукты данной категории");
        modelAndView.setViewName("client/listfoodbytypeid");
        return modelAndView;
    }

    /*
    @PostMapping("/client/listfoodbytypeid/{id}")
    public ModelAndView listFoodByTypeIdGet(@PathVariable int id, @ModelAttribute Food food) {
        ProductListByTypeIdService.getProductListById(id, menu);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listmenu", ProductListByTypeIdService.getMenuList());
        modelAndView.setViewName("listmenu");
        return modelAndView;
    }
    */
}