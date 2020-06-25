package ru.sushi.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sushi.demo.model.FoodType;
import ru.sushi.demo.model.Menu;

import java.sql.Connection;
import java.util.List;

/**
 * @author "Timohin Igor"
 */

@Service
public class MenuService {
    @Autowired
    DBSQL dbSQL;

    public MenuService() {
    }

    public void addMenu(Menu menu) {
        Connection cn = dbSQL.getConnection();
        dbSQL.insertPreparedToMenu(cn, menu);
    }

    public List<Menu> getMenuList() {
        Connection cn = dbSQL.getConnection();
        return dbSQL.getMenuList(cn);
    }

    public Menu getMenu(int id) {
        Connection cn = dbSQL.getConnection();
        return dbSQL.getMenu(cn, id);
    }

    public void editMenu(int id, Menu menu) {
        Connection cn = dbSQL.getConnection();
        dbSQL.editMenu(cn, id, menu);
    }

    public void deleteMenu(int id) {
        Connection cn = dbSQL.getConnection();
        dbSQL.deleteMenu(cn, id);
    }

    public List<FoodType> getMenuFoodTypeList(int id) {
        Connection cn = dbSQL.getConnection();
        return dbSQL.getMenuFoodTypeList(cn, id);
    }
}