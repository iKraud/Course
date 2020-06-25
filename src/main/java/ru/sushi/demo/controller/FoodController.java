/**
 * @author Sharafan Oksana
 * @date 04.02.2020
 * @package ru.sushi.demo.controller
 */
package ru.sushi.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.sushi.demo.model.Food;
import ru.sushi.demo.service.Basket;
import ru.sushi.demo.service.FileUploadingService;
import ru.sushi.demo.service.FoodService;
//import ru.sushi.demo.uploadingfiles.storage.StorageService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionAttributes("food")
@Controller
public class FoodController {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FoodService foodService;

//    @Autowired
//    private FileUploadingService fileUploadingService;

    /**
     * Список блюд для Клиента в меню
     */
    @GetMapping("/client/foods")
    public ModelAndView showFoodsMenu(HttpServletRequest request) {
        Basket basket = new Basket(request);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("foods", foodService.findByActive());
        modelAndView.setViewName("client/food/index");
        modelAndView.addObject("pageTitle", "Перечень блюд");
        modelAndView.addObject("cnt", basket.getQuantity());
        return modelAndView;
    }

    /**
     * Форма для выбора блюда по id
     */
    @RequestMapping(value = "/client/foods", method = RequestMethod.POST)
    public ModelAndView pickFoodForm(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("id") Integer id) {

        Basket basket = new Basket(request);
        basket.add(id, quantity);

        Cookie cookie = new Cookie(Basket.BASKET_COOKIE_HANDLE, basket.toString());
        cookie.setPath("/");
        response.addCookie(cookie);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/client/foods");
        modelAndView.addObject("pageTitle", "Выбор блюда по ID");
        return modelAndView;
    }

}