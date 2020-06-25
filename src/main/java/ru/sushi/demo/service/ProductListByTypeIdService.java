package ru.sushi.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sushi.demo.model.Food;

import java.sql.Connection;
import java.util.List;

@Service
public class ProductListByTypeIdService {

    @Autowired
    DBSQL dbSQL;

    public ProductListByTypeIdService() {
    }

    public List<Food> getProductListById(int id) {
        Connection connection = dbSQL.getConnection();
        return dbSQL.getProductListById(connection, id);
    }

}
