/**
 * @author Sharafan Oksana
 * @date 11.02.2020
 * @package ru.sushi.demo.controller
 */
package ru.sushi.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.sushi.demo.model.Food;
import ru.sushi.demo.model.FoodType;
import ru.sushi.demo.service.FileUploadingService;
import ru.sushi.demo.service.FoodService;
import ru.sushi.demo.service.FoodTypeService;
import ru.sushi.demo.service.FoodTypesEditorService;

import java.util.List;

@Controller
public class AdminFoodController {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FoodTypesEditorService foodTypesEditorService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private FileUploadingService fileUploadingService;

    /**
     * Список блюд
     */
    @GetMapping("/admin/foods")
//    @PreAuthorize("hashRole('ROLE_ADMIN')") //TODO закрыть доступ ROLE_USER
    public ModelAndView showFoods() {
        ModelAndView modelAndView = new ModelAndView();
        List<Food> foods = foodService.getAll();
        List<FoodType> foodTypesList = foodTypesEditorService.getFoodTypesList();
        modelAndView.addObject("foodTypesList", foodTypesList);
        modelAndView.addObject("foods", foods);
        modelAndView.addObject("pageTitle", "Список блюд");
        modelAndView.setViewName("admin/food/index");
        return modelAndView;
    }

    /**
     * Форма для Добавления блюда
     */
    @RequestMapping(value = "/admin/food/add", method = RequestMethod.GET)
//    @PreAuthorize("hasRole('ROLE_ADMIN')") //TODO закрыть доступ ROLE_USER
    public ModelAndView addFoodForm() {
        Food food = new Food();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("food", food);
        List<FoodType> foodTypesList = foodTypesEditorService.getFoodTypesList();
        modelAndView.addObject("foodTypesList", foodTypesList);
//        modelAndView.addObject("foodTypesEditorService", foodTypesEditorService);
        modelAndView.addObject("action", "add");
        modelAndView.addObject("formAction", "/admin/food/add");
        modelAndView.addObject("pageTitle", "Добавление блюда");
        modelAndView.setViewName("admin/food/edit");
        return modelAndView;
    }

    /**
     * Добавление блюда
     */
    @RequestMapping(value = "/admin/food/add", method = RequestMethod.POST)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")//TODO закрыть доступ ROLE_USER
    public ModelAndView addFoodSave(@ModelAttribute Food food, @RequestParam("file") MultipartFile file) {
        this.fileUploadingService.store(file);
        food.setImage(file.getOriginalFilename());
        foodService.add(food);

        return new ModelAndView("redirect:/admin/foods");
    }

    /**
     * Форма для Редактирования блюда
     */
    @RequestMapping(value = "/admin/food/{id}/edit", method = RequestMethod.GET)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")//TODO закрыть доступ ROLE_USER
    public ModelAndView editFoodForm(@PathVariable Integer id) {
        Food food = this.foodService.getById(id);


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("food", food);
        List<FoodType> foodTypesList = foodTypesEditorService.getFoodTypesList();
        modelAndView.addObject("foodTypesList", foodTypesList);
        modelAndView.addObject("action", "edit");
        modelAndView.addObject("formAction", "/admin/food/" + id + "/edit");
        modelAndView.addObject("pageTitle", "Редактирование блюда");
        modelAndView.setViewName("admin/food/edit");
        return modelAndView;
    }

    /**
     * Редактирование блюда
     */
    @RequestMapping(value = "/admin/food/{id}/edit", method = RequestMethod.POST)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")//TODO закрыть доступ ROLE_USER

    public ModelAndView editFoodSave(@ModelAttribute Food food, @RequestParam(value = "file", required = false) MultipartFile file) {
        if (!file.isEmpty()) {
            this.fileUploadingService.store(file);
            food.setImage(file.getOriginalFilename());
        }
        this.foodService.update(food);

        return new ModelAndView("redirect:/admin/foods");
    }

    /**
     * Форма для Удаления блюда
     */
    @RequestMapping(value = "/admin/food/{id}/delete", method = RequestMethod.GET)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")//TODO закрыть доступ ROLE_USER
    public ModelAndView deleteFoodForm(@PathVariable Integer id) {
        this.foodService.delete(id);
        return new ModelAndView("redirect:/admin/foods");
    }
}
