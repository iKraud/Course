package ru.sushi.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sushi.demo.model.Food;

import java.util.List;

@Repository("foodRepository")
public interface FoodRepository extends JpaRepository<Food, Integer> {
//    @Query("SELECT f FROM Food f WHERE f.active = true ")
@Query("SELECT f FROM Food f WHERE f.active = true ORDER BY f.foodTypeId")
    List<Food> findByActive();
}
