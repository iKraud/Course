package ru.sushi.demo.service;

import ru.sushi.demo.model.Food;
import ru.sushi.demo.model.OrderDetail;

import java.util.List;
import java.util.Map;

public interface OrderDetailService {

    Map<Food, Integer> getAll();

    void add(OrderDetail orderDetail);

    List<OrderDetail> convertFromBasket(Basket basket);

    void saveAll (List<OrderDetail> orderDetailList);

//    Food getById(Integer id);
//    void add(Food food);
//    void update(Food food);
//    void delete(Integer id);
//    List<Food> getAll();

}
