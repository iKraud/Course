package ru.sushi.demo.service;

import ru.sushi.demo.model.Food;
import ru.sushi.demo.model.Orders;

import java.util.List;

public interface OrdersService {
    Orders getById(Integer id);
    void add(Orders orders);
    void update(Orders orders);
//    void delete(Integer id);
    List<Orders> getAll();
}
