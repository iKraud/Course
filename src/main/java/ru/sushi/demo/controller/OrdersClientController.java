/**
 * @author Sharafan Oksana
 * @date 18.02.2020
 * @package ru.sushi.demo.controller
 */
package ru.sushi.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sushi.demo.model.Food;
import ru.sushi.demo.model.OrderDetail;
import ru.sushi.demo.model.Orders;
import ru.sushi.demo.service.Basket;
import ru.sushi.demo.service.FoodService;
import ru.sushi.demo.service.OrderDetailService;
import ru.sushi.demo.service.OrdersService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrdersClientController {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    private FoodService foodService;

    @GetMapping("/client/order/list")
    public ModelAndView listMenuGet() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("listorders", ordersService.getAll());
        modelAndView.setViewName("/client/order/list");
        return modelAndView;
    }

    /**
     * Форма для cоздания заказа
     */
    @RequestMapping(value = "/client/order/add", method = RequestMethod.GET)
    public ModelAndView add(HttpServletRequest request) {
        Basket basket = new Basket(request);
        List<OrderDetail> orderDetails = this.orderDetailService.convertFromBasket(basket);

        Orders order = new Orders();
        order.setOrderDetails(orderDetails);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("order", order);
        modelAndView.addObject("pageTitle", "Оформление заказа");
        modelAndView.setViewName("client/order/add");
        return modelAndView;
    }

    /**
     * Добавление заказа
     */
//    @RequestMapping(value = "/client/order/add/", method = RequestMethod.POST)
//    public ModelAndView addOrderSave(@ModelAttribute Orders order) {
//        ordersService.add(order);
////        return new ModelAndView("redirect:/client/foods/");
//        return new ModelAndView("client/order/answer");
//    }

    @RequestMapping(value = "/client/order/add", method = RequestMethod.POST)
    public ModelAndView addOrderSave() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle", "Заказ оформлен");
        modelAndView.setViewName("client/order/answer");
        return modelAndView;

        /*return new ModelAndView("client/order/answer");*/
    }
}
