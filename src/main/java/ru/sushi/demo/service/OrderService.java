package ru.sushi.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sushi.demo.extra.FoodQuantityInOrder;
import ru.sushi.demo.model.Orders;

import java.sql.Connection;
import java.util.List;

/**
 * @author "Timohin Igor"
 */

@Service
public class OrderService {
    @Autowired
    DBSQL dbSQL;

    public OrderService() {
    }

    public List<Orders> getOrdersList() {
        Connection cn = dbSQL.getConnection();
        return dbSQL.getOrdersList(cn);
    }

    public List<FoodQuantityInOrder> getFoodListByOrderId (int id) {
        Connection cn = dbSQL.getConnection();
        return dbSQL.getFoodListByOrderId(cn, id);
    }
}