package ru.sushi.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sushi.demo.model.FoodType;

import java.sql.Connection;
import java.util.List;

@Service
public class FoodTypesEditorService {

    @Autowired
    DBSQL dbSQL;

    public FoodTypesEditorService() {
    }

    public List<FoodType> getFoodTypesList() {
        Connection connection = dbSQL.getConnection();
        return dbSQL.getFoodTypesList(connection);
    }

    public boolean addFoodType(FoodType foodType) {
        Connection connection = dbSQL.getConnection();
        return dbSQL.addFoodType(connection, foodType);
    }

    public FoodType getFoodType(int id) {
        Connection connection = dbSQL.getConnection();
        return dbSQL.getFoodType(connection, id);
    }

    public boolean editFoodType(int id, FoodType foodType) {
        Connection connection = dbSQL.getConnection();
        return dbSQL.editFoodType(connection, id, foodType);
    }

    public void deleteFoodType(int id) {
        Connection connection = dbSQL.getConnection();
        dbSQL.deleteFoodType(connection, id);
    }

}
