package com.unicamp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unicamp.Utils.AuthCheck;
import com.unicamp.Utils.JsonMessage;
import com.unicamp.dao.RestaurantDao;
import com.unicamp.entity.Restaurant;

@Service
@PropertySource(value = "classpath:applicationValues.properties")
public class RestaurantService {

    private RestaurantDao restaurantDao;
    @Value("${restaurant.fee}")
    private Double RESTAURANT_FEE;
    private ObjectMapper mapper = new ObjectMapper();

    public RestaurantService(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    public Restaurant getRestaurantById(Integer ra) {
        Restaurant restaurant;
        try {
            restaurant = restaurantDao.findById(ra).get();
        } catch (Exception e) {
            restaurant = null;
        }
        return restaurant;
    }

    public ObjectNode save(String key, Restaurant saveRestaurant) {
        ObjectNode node;
        try {
            AuthCheck.authenticate(key);
            node = JsonMessage.buildMessage("success", "", restaurantDao.save(saveRestaurant), mapper);
        } catch (Exception e) {
            node = JsonMessage.buildMessage("failure", e.getMessage(), mapper);
        }
        return node;
    }

    public boolean deleteRestaurantById(Integer ra) {
        boolean successfull;
        try {
            restaurantDao.deleteById(ra);
            successfull = true;
        } catch (Exception e) {
            successfull = false;
        }
        return successfull;
    }

    public ObjectNode clearMealStats(String oAuthKey) {
        ObjectNode node;
        try {
            AuthCheck.authenticate(oAuthKey);
            restaurantDao.clearMealStats();
            node = JsonMessage.buildMessage("success", "", mapper);
        } catch (Exception e) {
            node = JsonMessage.buildMessage("failure", e.getMessage(), mapper);
        }
        return node;
    }

    public Restaurant eated(Integer ra) {
        Restaurant restaurant;
        try {
            Restaurant currentRestaurant = getRestaurantById(ra);
            Double currentCredits = currentRestaurant.getCredits() - RESTAURANT_FEE;
            if ((currentRestaurant.getIsPostPaid() && !currentRestaurant.getIsAlreadyAte())
                    || (currentCredits >= 0.00 && !currentRestaurant.getIsAlreadyAte())) {
                restaurantDao.eated(ra, currentCredits);
                restaurant = currentRestaurant;
                restaurant.setIsAlreadyAte(true);
                restaurant.addCredits(-RESTAURANT_FEE);
            } else {
                restaurant = null;
            }
        } catch (Exception e) {
            restaurant = null;
        }
        return restaurant;
    }

    public Restaurant recharged(Integer ra, Double value) {
        Restaurant restaurant;
        try {
            Restaurant currentRestaurant = getRestaurantById(ra);
            if (value > 0) {
                restaurantDao.recharged(ra, currentRestaurant.getCredits() + value);
                restaurant = currentRestaurant;
                restaurant.addCredits(value);
            } else {
                restaurant = null;
            }
        } catch (Exception e) {
            restaurant = null;
        }
        return restaurant;
    }

    public Double updateRoll(Integer ra) {
        Double debt;
        try {
            Restaurant currentRestaurant = getRestaurantById(ra);
            if (currentRestaurant.getIsPostPaid()) {
                debt = currentRestaurant.getCredits();
                restaurantDao.updateRoll(ra);
            } else {
                debt = null;
            }
        } catch (Exception e) {
            debt = null;
        }
        return debt;
    }
}
