package ru.sushi.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.sushi.demo.model.Food;
import ru.sushi.demo.model.FoodType;
import ru.sushi.demo.model.Menu;
import ru.sushi.demo.service.FoodTypesEditorService;

@Controller
public class FoodTypesEditorController {

    @Autowired
    FoodTypesEditorService foodTypesEditorService;

    @GetMapping("/admin/getfoodtypeslist")
    public ModelAndView foodTypesListGet() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("getfoodtypeslist", foodTypesEditorService.getFoodTypesList());
        modelAndView.addObject("pageTitle", "Перечень категорий продуктов");
        modelAndView.setViewName("admin/foodtypeslist");
        return modelAndView;
    }

    @GetMapping("/admin/addfoodtype")
    public ModelAndView addFoodTypeGet() {
        FoodType foodType = new FoodType();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("addfoodtype", foodType);
        modelAndView.addObject("pageTitle", "Добавить категорию продуктов");
        modelAndView.setViewName("admin/addfoodtype");

        return modelAndView;
    }

    @PostMapping("/admin/addfoodtype")
    public ModelAndView addFoodTypePost(@ModelAttribute FoodType foodType) {
        foodTypesEditorService.addFoodType(foodType);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("getfoodtypeslist", foodTypesEditorService.getFoodTypesList());
        modelAndView.addObject("pageTitle", "Обновлённый список категорий продуктов");
        modelAndView.setViewName("admin/foodtypeslist");

        return modelAndView;
    }

    @GetMapping("/admin/editfoodtype/{id}")
    public ModelAndView editFoodTypeGet(@PathVariable int id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("getfoodtypeslist", foodTypesEditorService.getFoodTypesList());
        modelAndView.addObject("editfoodtype", foodTypesEditorService.getFoodType(id));
        modelAndView.addObject("pageTitle", "Редактирование категории продуктов");
        modelAndView.setViewName("admin/editfoodtype");

        return modelAndView;
    }

    @PostMapping("/admin/editfoodtype/{id}")
    public ModelAndView editFoodTypePost(@PathVariable int id, @ModelAttribute FoodType foodType) {
        foodTypesEditorService.editFoodType(id, foodType);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("getfoodtypeslist", foodTypesEditorService.getFoodTypesList());
        modelAndView.addObject("pageTitle", "Обновлённый список категорий продуктов");
        modelAndView.setViewName("admin/foodtypeslist");

        return modelAndView;
    }

    @GetMapping("/admin/deletefoodtype/{id}")
    public ModelAndView deleteMenuGet(@PathVariable int id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("getfoodtypeslist", foodTypesEditorService.getFoodTypesList());
        modelAndView.addObject("deletefoodtype", foodTypesEditorService.getFoodType(id));
        modelAndView.addObject("pageTitle", "Удаление категории продуктов");
        modelAndView.setViewName("admin/deletefoodtype");

        return modelAndView;
    }

    @PostMapping("/admin/deletefoodtype/{id}")
    public ModelAndView deleteMenuPost(@PathVariable int id) {
        foodTypesEditorService.deleteFoodType(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("getfoodtypeslist", foodTypesEditorService.getFoodTypesList());
        modelAndView.addObject("pageTitle", "Обновлённый список категорий продуктов");
        modelAndView.setViewName("admin/foodtypeslist");

        return modelAndView;
    }

}
