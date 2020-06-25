/**
 * @author Sharafan Oksana
 * @date 22.01.2020
 * @package ru.innopolis.POJO
 */
package ru.sushi.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "order_detail")
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "food_id")
    private int foodId;

    @Column(name = "quantity")
    private int quantity;

    @Transient
    @ManyToOne
    private Food food;

    @Transient
    @ManyToOne
    private Orders order;

    public OrderDetail() {
    }

    public OrderDetail(Orders order, int orderId, int foodId, int quantity) {
        this.order = order;
        this.orderId = orderId;
        this.foodId = foodId;
        this.quantity = quantity;
    }

    public OrderDetail(int id, int orderId, int foodId, int quantity) {
        this.id = id;
        this.foodId = foodId;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", order=" + order +
                ", orderId=" + orderId +
                ", foodId=" + foodId +
                ", quantity=" + quantity +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

}