/**
 * @author Sharafan Oksana
 * @date 22.01.2020
 * @package ru.innopolis.POJO
 */
package ru.sushi.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_status")
public class OrderStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public OrderStatus() {
    }

    public OrderStatus(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}