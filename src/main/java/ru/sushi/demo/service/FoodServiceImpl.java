/**
 * @author Sharafan Oksana
 * @date 04.02.2020
 * @package ru.sushi.demo.service
 */
package ru.sushi.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;
import ru.sushi.demo.repository.FoodRepository;
import ru.sushi.demo.model.Food;

import java.util.List;

@Service("foodService")

public class FoodServiceImpl implements FoodService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("foodRepository")
    private FoodRepository foodRepository;

//    private JdbcOperations jdbcTemplate;

    @Override
    public Food getById(Integer id) {
        return foodRepository.findById(id).get();
    }

    @Override
    public void add(Food food) {
        food = foodRepository.save(food);
    }

    @Override
    public void update(Food food) {
        food = foodRepository.save(food);
    }

    @Override
    public void delete(Integer id) {
        foodRepository.deleteById(id);
    }

    @Override
    public List<Food> getAll() {
        return foodRepository.findAll();
    }

//    @Override
//    public List<Food> findByOrderByFoodTypeIdAndByActive() {
//        return foodRepository.findByOrderByFoodTypeIdAndByActive();
//    }

    @Override
        public List<Food> findByActive() {
        return foodRepository.findByActive();
    }
}
