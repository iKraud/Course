package ru.sushi.demo.service;

import ru.sushi.demo.extra.FoodQuantityInOrder;
import ru.sushi.demo.model.*;

import java.sql.Connection;
import java.util.List;

/**
 * @author "Timohin Igor"
 */

public interface DBSQL {
    Connection getConnection();
    void insertPreparedToMenu(Connection cn, Menu menu);
    List<Menu> getMenuList(Connection cn);
    Menu getMenu(Connection cn, int id);
    void editMenu (Connection cn, int id, Menu menu);
    void deleteMenu (Connection cn, int id);
    List<FoodType> getMenuFoodTypeList(Connection cn, int id);
    List<Orders> getOrdersList(Connection cn);
    List<FoodQuantityInOrder> getFoodListByOrderId(Connection cn, int id);
    List<Food> getProductListById(Connection cn, int id);
    List<FoodType> getFoodTypesList (Connection cn);
    boolean addFoodType (Connection cn, FoodType foodType);
    FoodType getFoodType(Connection cn, int id);
    boolean editFoodType (Connection cn, int id, FoodType foodType);
    void deleteFoodType (Connection cn, int id);
}