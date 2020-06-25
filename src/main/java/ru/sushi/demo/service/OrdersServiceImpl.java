/**
 * @author Sharafan Oksana
 * @date 20.02.2020
 * @package ru.sushi.demo.service
 */
package ru.sushi.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ru.sushi.demo.model.Food;
import ru.sushi.demo.model.OrderDetail;
import ru.sushi.demo.model.Orders;
import ru.sushi.demo.repository.FoodRepository;
import ru.sushi.demo.repository.OrdersRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    @Autowired
    @Qualifier("ordersRepository")
    private OrdersRepository ordersRepository;


    @Autowired
    @Qualifier("orderDetailServiceImpl")
    private OrderDetailService orderDetailService;


    @Override
    public Orders getById(Integer id) {
        return ordersRepository.findById(id).get();
    }

    @Override
    public void add(Orders order) {
        List<OrderDetail> res = new ArrayList<>();
        for (OrderDetail det : order.getOrderDetails()) {
            det.setOrder(order);
            det.setOrderId(order.getId());
            det.setFoodId(det.getFood().getId());
            res.add(det);
        }

        order.setUserId(100);
        order.setOrderStatusId(100);
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        order.setFinishedAt(new Timestamp(System.currentTimeMillis()+30*60*1000));
        order.setOrderDetails(res);


        this.ordersRepository.saveOne(
                order.getUserId(),
                order.getCreatedAt(),
                order.getFinishedAt(),
                order.getOrderStatusId(),
                order.getNotes(),
                order.getAddress(),
                order.getPersons(),
                order.getPaymentId()
        );
//        Orders result = ordersRepository.save(order);
//
        int a = 3;
//
//
//
//        this.orderDetailService.saveAll(res);


    }

    @Override
    public void update(Orders orders) {
        orders = ordersRepository.save(orders);
    }

    @Override
    public List<Orders> getAll() {
        return ordersRepository.findAll();
    }
}
