package ru.sushi.demo.controller;

import org.springframework.validation.FieldError;
import ru.sushi.demo.entity.User;
import ru.sushi.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @GetMapping("/registrationComplite")
    public String registrationOK(Model model) {
        return "registrationComplite";
    }


    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

        System.out.println("--> registration start " + userForm.getUsername() + " " + userForm.getPhone()) ;

        if(userForm.getPassword().isEmpty() ||
                userForm.getPasswordConfirm().isEmpty() ||
                userForm.getUsername().isEmpty() ||
                userForm.getPhone().isEmpty() ||
                userForm.getEmail().isEmpty()) {
            System.out.println("--> registration error не заполнены обязательные поля" + userForm.getUsername()) ;
            model.addAttribute("regError", "Не заполнены обязательные поля");
            return "registration";
        }

        // обработка ошибок
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            StringBuilder message = new StringBuilder();
            for (FieldError e : errors){
                message.append(e.getField().toUpperCase() + ": " + e.getDefaultMessage() + " ");
            }

            System.out.println("--> registration error " + message + " " + userForm.getUsername()) ;
            model.addAttribute("regError", message);
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            System.out.println("--> registration error несопадение паролей" + userForm.getUsername()) ;
            model.addAttribute("regError", "Пароли не совпадают");
            return "registration";
        }



        if (!userService.saveUser(userForm)){
            System.out.println("--> registration error пользователь существует " + userForm.getUsername()) ;
            model.addAttribute("regError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/registrationComplite";
    }
}
