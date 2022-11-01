package com.unicamp.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unicamp.dao.RestaurantDao;
import com.unicamp.entity.Restaurant;

@Service
public class RestaurantService {
    
    @Autowired
    @Qualifier("carteirinha")
    private RestaurantDao restaurantDao;

    public Restaurant getRestaurantStatsById(Integer ra) {
        return restaurantDao.getRestaurantStatsById(ra);
    }

    public void clearMealStats() {
        restaurantDao.clearMealStats();
    }

    public void removeRestaurantStatsById(Integer ra) {
        restaurantDao.removeRestaurantStatsById(ra);
    }

    public void insertRestaurantToDB(Restaurant restaurant) {
        restaurantDao.insertRestaurantToDB(restaurant);
    }

    public Collection<Restaurant> getAllRestaurantStats() {
        return restaurantDao.getAllRestaurantStats();
    }
}
