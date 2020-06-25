package ru.sushi.demo.service;

import ru.sushi.demo.model.Food;

import java.util.List;

public interface FoodService {
    Food getById(Integer id);
    void add(Food food);
    void update(Food food);
    void delete(Integer id);
    List<Food> getAll();
    List<Food> findByActive();
//    List<Food> findByOrderByFoodTypeIdAndByActive();
}
