package ru.sushi.demo.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sushi.demo.controller.MenuEditorController;
import ru.sushi.demo.entity.User;
import ru.sushi.demo.extra.FoodQuantityInOrder;
import ru.sushi.demo.model.*;

/**
 * @author "Timohin Igor"
 */
@Service
public class DBPostgreSQL implements DBSQL {
    private static final Logger logger = LogManager.getLogger(MenuEditorController.class.getName());
    private Connection connection = null;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    public static final String INSERT_PREPARED_TO_MENU = "insert into public.menu (title) values (?)";

    @Value("${spring.datasource.url}")
    private String jdbcURL;

    @Value("${spring.datasource.username}")
    private String jdbcUserName;

    @Value("${spring.datasource.password}")
    private String jdbcPass;

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPass);
            logger.info("Соединение создано...");
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Ошибка при создании соединения!", e);
        }
        return connection;
    }

    public void insertPreparedToMenu(Connection cn, Menu menu) {
        try {
            preparedStatement = cn.prepareStatement(INSERT_PREPARED_TO_MENU);
            preparedStatement.setString(1, menu.getTitle());
            preparedStatement.execute();
            preparedStatement.close();
            logger.info("Prepared Statement выполнен...");
        } catch (SQLException e) {
            logger.error("Ошибка при использовании Prepared Statement!", e);
        }
    }

    public List<Menu> getMenuList(Connection cn) {
        List<Menu> menuList = new ArrayList<>();
        try {
            statement = cn.createStatement();
            resultSet = statement.executeQuery(
                    "select count(*) from public.menu");
            resultSet.next();
            int colCount = resultSet.getInt(1);

            resultSet = statement.executeQuery(
                    "select * from public.menu");
            resultSet.next();
            for (int i = 1; i <= colCount; i++) {
                menuList.add(new Menu(resultSet.getInt(1), resultSet.getString(2)));
                resultSet.next();
            }
        } catch (SQLException e) {
            logger.error("Ошибка при получении перечня меню!", e);
        }
        return menuList;
    }

    public Menu getMenu(Connection cn, int id) {
        Menu menu = null;
        try {
            statement = cn.createStatement();
            resultSet = statement.executeQuery(
                    "select * from public.menu" +
                            " where id =" + id);
            resultSet.next();
            menu = new Menu(resultSet.getInt(1), resultSet.getString(2));
        } catch (SQLException e) {
            logger.error("Ошибка при получении меню!", e);
        }
        return menu;
    }

    public void editMenu(Connection cn, int id, Menu menu) {
        try {
            statement = cn.createStatement();
            statement.execute(
                    "update public.menu" +
                            " set" +
                            " title=" + "'" + menu.getTitle() + "'" +
                            " where id =" + id);
        } catch (SQLException e) {
            logger.error("Ошибка при редактировании меню!", e);
        }
    }

    public void deleteMenu(Connection cn, int id) {
        try {
            statement = cn.createStatement();
            statement.execute(
                    "delete from public.menu" +
                            " where id =" + id);
        } catch (SQLException e) {
            logger.error("Ошибка при удалении меню!", e);
        }
    }

    public List<FoodType> getMenuFoodTypeList (Connection cn, int id) {
        List<FoodType> menuFoodTypeList = new ArrayList<>();
        try {
            statement = cn.createStatement();
            resultSet = statement.executeQuery(
        "select * from" +
            " public.food_type ft" +
            " left join public.food f" +
            " on ft.id = f.food_type_id" +
            " left join public.food_menu fm" +
            " on f.id = fm.food_id" +
            " left join public.menu m" +
            " on fm.menu_id = m.id" +
            " where m.id=" + id);
            resultSet.next();
            int colCount = resultSet.getInt(1);
            for (int i = 1; i <= colCount; i++) {
                menuFoodTypeList.add(new FoodType(resultSet.getInt(1), resultSet.getString(2)));
                resultSet.next();
            }
        } catch (SQLException e) {
            logger.error("Ошибка при получении типов еды в меню!", e);
        }
        return menuFoodTypeList;
    }

    public List<Orders> getOrdersList(Connection cn) {
        List<Orders> ordersList = new ArrayList<>();
        try {
            statement = cn.createStatement();
            resultSet = statement.executeQuery(
        "select count(*) from public.orders");
            resultSet.next();
            int colCount = resultSet.getInt(1);

            resultSet = statement.executeQuery(
        "select o.*, u.username, u.phone, u.email, os.name" +
            " from public.orders o" +
            " left join public.users u" +
            " on o.user_id = u.id" +
            " left join public.order_status os" +
            " on o.order_status_id = os.id" +
            " order by finished_at desc," +
            " created_at");
            resultSet.next();

//            Orders(int id, Users users, Timestamp createdAt, Timestamp finishedAt, OrderStatus orderStatus, String notes, String address, int persons)
            for (int i = 1; i <= colCount; i++) {
                ordersList.add(new Orders(
                        resultSet.getInt(1), //id
                        new User(resultSet.getString(10), //User.username
                                resultSet.getString(11), //User.phone
                                resultSet.getString(12)), //User.email
                        resultSet.getTimestamp(3), //createdAt
                        resultSet.getTimestamp(4), //finishedAt
                        new OrderStatus(resultSet.getString(13)), //OrderStatus.name
                        resultSet.getString(6), //notes
                        resultSet.getString(7), //address
                        resultSet.getInt(8))); //persons
                resultSet.next();
            }
        } catch (SQLException e) {
            logger.error("Ошибка при получении перечня заказов!", e);
        }
        return ordersList;
    }

    public List<Food> getProductListById(Connection connection, int id) {
        List<Food> foodList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "select count(*) from public.food");
            resultSet.next();
            int columnsCount = resultSet.getInt(1);

            resultSet = statement.executeQuery(
                    "select * from public.food" +
                            " where id =" + id);
            resultSet.next();

            for (int i = 1; i <= columnsCount; i++) {
                foodList.add(new Food(
                        // ID
                        /*resultSet.getInt(1),*/
                        // User ID
                        resultSet.getString(2),
                        // Food Type ID
                        id,
                        // Description
                        resultSet.getString(4),
                        // Weight
                        resultSet.getDouble(5),
                        // Price
                        resultSet.getDouble(6),
                        // Image
                        resultSet.getString(7),
                        // Active or Not
                        resultSet.getBoolean(8)));
                        resultSet.next();
            }
        } catch (SQLException e) {
            logger.error("Ошибка при получении перечня заказов!", e);
        }
        return foodList;
    }

    public List<FoodQuantityInOrder> getFoodListByOrderId(Connection cn, int id) {
        List<FoodQuantityInOrder> foodList = new ArrayList<>();
        try {
            statement = cn.createStatement();
            resultSet = statement.executeQuery(
        "select count(*) from public.order_detail" +
            " where order_id= " + id);
            resultSet.next();
            int colCount = resultSet.getInt(1);

            resultSet = statement.executeQuery(
        "select f.*, od.order_id, od.quantity from" +
            " public.food f" +
            " left join public.order_detail od" +
            " on f.id = od.food_id" +
            " where od.order_id= " + id +
            " order by od.order_id");
            resultSet.next();

            for (int i = 1; i <= colCount; i++) {
//                int foodId, String foodName, int foodTypeId, String foodDescription,
//                double weight, double price, String image, boolean active, int orderId, int foodQuantity
                foodList.add(new FoodQuantityInOrder(
                        resultSet.getInt(1), //foodId
                        resultSet.getString(2), //name
                        resultSet.getInt(3), //foodTypeId
                        resultSet.getString(4), //description
                        resultSet.getDouble(5), //weight
                        resultSet.getDouble(6), //price
                        resultSet.getString(7), //image
                        resultSet.getBoolean(8), //active
                        resultSet.getInt(9), //orderId
                        resultSet.getInt(10))); //foodQuantity
                resultSet.next();
            }
        } catch (SQLException e) {
            logger.error("Ошибка при получении списка еды в заказе!", e);
        }
        return foodList;
    }

    public List<FoodType> getFoodTypesList (Connection cn) {
        List<FoodType> foodTypesList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "select count(*) from public.food_type");
            resultSet.next();
            int colCount = resultSet.getInt(1);

            resultSet = statement.executeQuery("select * from public.food_type");
            resultSet.next();

            for (int i = 1; i <= colCount; i++) {
                foodTypesList.add(new FoodType(
                        // ID
                        resultSet.getInt(1),
                        // Name
                        resultSet.getString(2)));
                resultSet.next();
            }
        } catch (SQLException e) {
            logger.error("Ошибка при получении перечня категорий продуктов!", e);
        }
        return foodTypesList;
    }

    public boolean addFoodType (Connection cn, FoodType foodType) {
        boolean success = false;
        try {
            String sql = "insert into public.food_type(name) values(?)";
            preparedStatement = cn.prepareStatement(sql);
            preparedStatement.setString(1, foodType.getName());
            success = preparedStatement.execute();
            preparedStatement.close();
            logger.info("Добавление категории продуктов выполнено!");
        } catch (SQLException e) {
            logger.error("Ошибка при добавлении категории продуктов!", e);
        }
        return success;
    }

    public FoodType getFoodType (Connection cn, int id) {
        FoodType foodType = null;
        try {
            statement = cn.createStatement();
            resultSet = statement.executeQuery(
                    "select * from public.food_type" +
                            " where id =" + id);
            resultSet.next();
            foodType = new FoodType(resultSet.getInt(1), resultSet.getString(2));
        } catch (SQLException e) {
            logger.error("Ошибка при получении категории продуктов!", e);
        }
        return foodType;
    }

    public boolean editFoodType (Connection cn, int id, FoodType foodType) {
        boolean success = false;
        try {
            statement = cn.createStatement();
            success = statement.execute("update public.food_type set name = " + "'" + foodType.getName() + "'" + " where id = " + id);
        } catch (SQLException e) {
            logger.error("Ошибка при редактировании категории продуктов!", e);
        }
        return success;
    }

    public void deleteFoodType(Connection cn, int id) {
        try {
            statement = cn.createStatement();
            statement.execute(
                    "delete from public.food_type where id = " + id);
        } catch (SQLException e) {
            logger.error("Ошибка при удалении категории продуктов!", e);
        }
    }

}