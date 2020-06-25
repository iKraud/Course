package ru.sushi.demo.extra;

import java.io.Serializable;

/**
 * @author "Timohin Igor"
 */
public class FoodQuantityInOrder implements Serializable {
    private int orderId;
    private int foodQuantity;

    private int foodId;
    private String foodName;
    private int foodTypeId;
    private String foodDescription;
    private double weight;
    private double price;
    private String image;
    private boolean active;

    public FoodQuantityInOrder(int foodId, String foodName, int foodTypeId, String foodDescription,
                               double weight, double price, String image, boolean active, int orderId, int foodQuantity) {
        this.orderId = orderId;
        this.foodQuantity = foodQuantity;
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodTypeId = foodTypeId;
        this.foodDescription = foodDescription;
        this.weight = weight;
        this.price = price;
        this.image = image;
        this.active = active;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
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
}