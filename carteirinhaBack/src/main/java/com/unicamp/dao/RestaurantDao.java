package com.unicamp.dao;

import java.util.Collection;
import com.unicamp.entity.Restaurant;

public interface RestaurantDao {
    Collection<Restaurant> getAllRestaurantStats();

    Restaurant getRestaurantStatsById(Integer ra);

    void clearMealStats();

    void removeRestaurantStatsById(Integer ra);

    void insertRestaurantToDB(Restaurant restaurant);
}
