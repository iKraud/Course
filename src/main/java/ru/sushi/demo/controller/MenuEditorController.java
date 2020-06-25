package ru.sushi.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sushi.demo.model.Menu;
import ru.sushi.demo.service.MenuService;

/**
 * @author "Timohin Igor"
 */
@Controller
public class MenuEditorController {

    @Autowired
    MenuService MenuService;

    @GetMapping("/admin/listmenu")
    public ModelAndView listMenuGet() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listmenu", MenuService.getMenuList());
        modelAndView.addObject("pageTitle", "Перечень меню");
        modelAndView.setViewName("listmenu");
        return modelAndView;
    }

    @GetMapping("/admin/addmenu")
    public ModelAndView addMenuGet() {
        Menu menu = new Menu();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("menu", menu);
        modelAndView.addObject("pageTitle", "Добавление меню");
        modelAndView.setViewName("addmenu");

        return modelAndView;
    }

    @PostMapping("/admin/addmenu")
    public ModelAndView addMenuPost(@ModelAttribute Menu menu) {
        MenuService.addMenu(menu);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listmenu", MenuService.getMenuList());
        modelAndView.setViewName("listmenu");

        return modelAndView;
    }

    @GetMapping("/admin/editmenu/{id}")
    public ModelAndView editMenuGet(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("menu", MenuService.getMenu(id));
        modelAndView.addObject("pageTitle", "Редактирование меню");
        modelAndView.setViewName("editmenu");

        return modelAndView;
    }

    @PostMapping("/admin/editmenu/{id}")
    public ModelAndView editMenuPost(@PathVariable int id, @ModelAttribute Menu menu) {
        MenuService.editMenu(id, menu);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listmenu", MenuService.getMenuList());
        modelAndView.setViewName("listmenu");
        return modelAndView;
    }

    @GetMapping("/admin/deletemenu/{id}")
    public ModelAndView deleteMenuGet(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("menu", MenuService.getMenu(id));
        modelAndView.addObject("pageTitle", "Удаление меню");
        modelAndView.setViewName("deletemenu");

        return modelAndView;
    }

    @PostMapping("/admin/deletemenu/{id}")
    public ModelAndView deleteMenuPost(@PathVariable int id) {
        MenuService.deleteMenu(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listmenu", MenuService.getMenuList());
        modelAndView.setViewName("listmenu");

        return modelAndView;
    }
}