package ru.sushi.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ru.sushi.demo.service.OrderService;

/**
 * @author "Timohin Igor"
 */
@Controller
public class OrdersController {

    @Autowired
    OrderService OrderService;

    @GetMapping("/admin/listorders")
    public ModelAndView listOrdersGet() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listorders", OrderService.getOrdersList());
        modelAndView.addObject("pageTitle", "Перечень заказов");
        modelAndView.setViewName("listorders");
        return modelAndView;
    }

    @GetMapping("/admin/browseorder/{id}")
    public ModelAndView browseOrder(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("browseorder", OrderService.getFoodListByOrderId(id));
        modelAndView.addObject("pageTitle", "Просмотр заказа № " + id);
        modelAndView.setViewName("browseorder");
        return modelAndView;
    }
}