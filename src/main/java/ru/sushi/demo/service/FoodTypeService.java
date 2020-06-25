package ru.sushi.demo.service;

import ru.sushi.demo.model.FoodType;

import java.util.ArrayList;
import java.util.List;

public class FoodTypeService {

    // заглушка получение из БД
    public List<FoodType> getFoodType () {
        System.out.println("--> reed from DB start");
        return new ArrayList<FoodType>();
    }

    // заглушка сохранения в БД
    public boolean insertFoodType (FoodType foodType) {
        System.out.println("--> insert to DB start - " + foodType.getId() + " - " + foodType.getName());
        System.out.println("--> insert result to DB start - OK");
        return true;
    }

    // заглушка обновления в БД
    public boolean updateFoodType (FoodType foodType) {
        System.out.println("--> update to DB start - " + foodType.getId() + " - " + foodType.getName());
        System.out.println("--> update result to DB start - OK");
        return true;
    }

}
