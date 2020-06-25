/**
 * @author Sharafan Oksana
 * @date 22.01.2020
 * @package ru.innopolis.POJO
 */
package ru.sushi.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.sushi.demo.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonIgnore
    private User users;

    @JsonIgnore
    private OrderStatus orderStatus;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "finished_at")
    private Timestamp finishedAt;

    @Column(name = "order_status_id")
    private int orderStatusId;

    @Column(name = "note")
    private String notes;

    @Column(name = "address")
    private String address;

    @Column(name = "persons")
    private int persons;

    @Column(name = "payment_id")
    private int paymentId;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Orders() {
    }

    public Orders(int id, User users, int userId, Timestamp createdAt, Timestamp finishedAt, int orderStatusId, String notes, String address, int persons) {
        this.id = id;
        this.users = users;
        this.userId = userId;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
        this.orderStatusId = orderStatusId;
        this.notes = notes;
        this.address = address;
        this.persons = persons;
    }

    public Orders(int id, User users, Timestamp createdAt, Timestamp finishedAt, OrderStatus orderStatus, String notes, String address, int persons) {
        this.id = id;
        this.users = users;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
        this.orderStatus = orderStatus;
        this.notes = notes;
        this.address = address;
        this.persons = persons;
        this.paymentId = paymentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() { //todo замена Date
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getFinishedAt() {
        return finishedAt;
    } //todo замена Date

    public void setFinishedAt(Timestamp finishedAt) {
        this.finishedAt = finishedAt;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public List<OrderDetail> getOrderDetails() {
        return this.orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        this.orderDetails.add(orderDetail);
    }

}