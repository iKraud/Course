/**
 * @author Sharafan Oksana
 * @date 22.01.2020
 * @package ru.innopolis.POJO
 */
package ru.sushi.demo.model;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name = "food")
public class Food implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "food_type_id")
    private int foodTypeId;

    @Column(name = "description")
    private String description;

    @Column(name = "weight")
    private double weight;

    @Column(name = "price")
    private double price;

    @Column(name = "image")
    private String image;

    @Column(name = "active")
    private boolean active;

    @Transient
    @OneToMany (mappedBy = "")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Food() {
    }

    public Food(String name, int foodTypeId, String description, double weight, double price, String image,
                boolean active) {
        this.name = name;
        this.foodTypeId = foodTypeId;
        this.description = description;
        this.weight = weight;
        this.price = price;
        this.image = image;
        this.active = active;
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

    public int getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }


    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foodTypeId=" + foodTypeId +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", image=" + image +
                ", isActive=" + active +
                '}';
    }

}
