/**
 * @author Sharafan Oksana
 * @date 22.01.2020
 * @package ru.innopolis.POJO
 */
package ru.sushi.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "menu")
public class Menu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    public Menu() {
    }

    public Menu(int id, String title) {
        this.title = title;
        this.id = id;
    }

    public Menu(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}