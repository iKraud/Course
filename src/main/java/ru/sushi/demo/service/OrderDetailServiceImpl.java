/**
 * @author Sharafan Oksana
 * @date 15.02.2020
 * @package ru.sushi.demo.service
 */
package ru.sushi.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.sushi.demo.model.Food;
import ru.sushi.demo.model.OrderDetail;
import ru.sushi.demo.repository.FoodRepository;
import ru.sushi.demo.repository.OrderDetailRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    @Qualifier("orderDetailRepository")
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    @Qualifier("foodRepository")
    private FoodRepository foodRepository;


    @Override
    public Map<Food, Integer> getAll() {
        return null;
    }

    @Override
    public void add(OrderDetail orderDetail) {
        orderDetail = orderDetailRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetail> convertFromBasket(Basket basket) {
        List<OrderDetail> result = new ArrayList<>();

        for (Map.Entry<String, String> entry : basket.getItems().entrySet()) {
            Food food = this.foodRepository.getOne(Integer.valueOf(entry.getKey()));
            OrderDetail det = new OrderDetail();
            det.setOrderId(100);//todo 03/03
            det.setFood(food);
            det.setQuantity(Integer.valueOf(entry.getValue()));
            result.add(det);
        }
        return result;
    }

    @Override
    public void saveAll(List<OrderDetail> orderDetailList) {
        this.orderDetailRepository.saveAll(orderDetailList);
    }
}
