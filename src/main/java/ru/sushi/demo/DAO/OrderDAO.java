///**
// * @author Sharafan Oksana
// * @date 18.02.2020
// * @package ru.sushi.demo.repository
// */
//package ru.sushi.demo.DAO;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import ru.sushi.demo.model.OrderDetail;
//import ru.sushi.demo.model.Orders;
//import ru.sushi.demo.service.FoodService;
//
//import java.sql.Timestamp;
//import java.util.Date;
//
//@Transactional
//@Repository
//public class OrderDAO {
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    @Autowired
//    private FoodService foodService;
//
//    private int getMaxOrderNum() {
//        String sql = "Select max(o.id) from" + Orders.class.getName() + " o ";
//        Session session = this.sessionFactory.getCurrentSession();
//        Query<Integer> query = session.createQuery(sql, Integer.class);
//        Integer value = (Integer) query.getSingleResult();
//        if (value == null) {
//            return 0;
//        }
//        return value;
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    public void saveOrder(OrderDetail orderDetail) {
//        Session session = this.sessionFactory.getCurrentSession();
//        int id = this.getMaxOrderNum() + 1;
//        Orders order = new Orders();
//
////        order.setId(UUID.randomUUID().toString());
//        order.setId(id);
////        order.setOrderNum(orderNum);
//        order.setCreatedAt(new Timestamp(new Date().getTime()));
//
////        order.setFinishedAt(new Timestamp(new Date().setTime());
//        order.setNotes(order.getNotes());
//        order.setPersons(order.getPersons());
//        order.setAddress(order.getAddress());
//        order.setUserId(u);
//
//
//
//        order.setAmount(orderDetail.getAmountTotal());
//
//        CustomerInfo customerInfo = orderDetail.getCustomerInfo();
//        order.setUserId(customerInfo.getName());
//        order.setAddress(customerInfo.getEmail());
//        order.setCustomerPhone(customerInfo.getPhone());
//        order.setCustomerAddress(customerInfo.getAddress());
//
//        session.persist(order);
//
//        List<CartLineInfo> lines = orderDetail.getCartLines();
//
//        for (CartLineInfo line : lines) {
//            OrderDetail detail = new OrderDetail();
//            detail.setId(UUID.randomUUID().toString());
//            detail.setOrder(order);
//            detail.setAmount(line.getAmount());
//            detail.setPrice(line.getProductInfo().getPrice());
//            detail.setQuanity(line.getQuantity());
//
//            String code = line.getProductInfo().getCode();
//            Product product = this.productDAO.findProduct(code);
//            detail.setProduct(product);
//
//            session.persist(detail);
//        }
//
//        // Order Number!
//        orderDetail.setOrderNum(orderNum);
//        // Flush
//        session.flush();
//    }
//}
