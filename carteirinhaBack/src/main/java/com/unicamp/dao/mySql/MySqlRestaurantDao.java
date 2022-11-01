package com.unicamp.dao.mySql;

import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.unicamp.dao.RestaurantDao;
import com.unicamp.entity.Restaurant;

@Repository
@Qualifier("carteirinha")
public class MySqlRestaurantDao implements RestaurantDao {
    private static Map<Integer, Restaurant> restaurant;

    @Override
    public Collection<Restaurant> getAllRestaurantStats() {
        return this.restaurant.values();
    }

    @Override
    public Restaurant getRestaurantStatsById(Integer ra) {
        return this.restaurant.get(ra);
    }

    // TODO: Terminar implementação
    @Override
    public void clearMealStats() {
        for (Restaurant r : restaurant.values()) {
            r.cleanMeal();
        }
    }

    @Override
    public void removeRestaurantStatsById(Integer ra) {
        this.restaurant.remove(ra); 
    }

    @Override
    public void insertRestaurantToDB(Restaurant restaurantInfo) {
        this.restaurant.put(restaurantInfo.getRa(), restaurantInfo);
    }
    
}
