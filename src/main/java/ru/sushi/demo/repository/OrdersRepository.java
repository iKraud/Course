package ru.sushi.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import ru.sushi.demo.entity.User;
import ru.sushi.demo.model.Food;
import ru.sushi.demo.model.Orders;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    @Modifying
    @Query(value = "INSERT into orders (user_id,created_at,finished_at,order_status_id,note,address,persons,payment_id)" +
            "VALUES(:user_id, :created_at, :finished_at, :order_status_id, :note, :address, :persons, :payment_id);",
            nativeQuery = true)
    void saveOne(
            @Param("user_id") Integer userId,
            @Param("created_at") Timestamp createdAt,
            @Param("finished_at") Timestamp finishedAt,
            @Param("order_status_id") Integer orderStatusId,
            @Param("note") String note,
            @Param("address") String address,
            @Param("persons") Integer persons,
            @Param("payment_id") Integer paymentId
    );

}
