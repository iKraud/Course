/**
 * @author Sharafan Oksana
 * @date 29.01.2020
 * @package ru.sushi.demo.model
 */
package ru.sushi.demo.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table (name = "food_menu")
public class FoodMenu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "food_id")
    private int foodId;
    @Column(name = "menu_id")
    private int menuId;

    public FoodMenu() {
    }

    public FoodMenu(int id, int menuId) {
        this.id = id;
        this.menuId = menuId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "FoodMenu{" +
                "id=" + id +
                ", foodId=" + foodId +
                ", menuId=" + menuId +
                '}';
    }
}
