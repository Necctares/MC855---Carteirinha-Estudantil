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
    @Value("${alreadyAte.error}")
    private String ALREADY_ATE_ERROR;
    @Value("${insufficienteCredits.error}")
    private String INSUFFICIENT_CREDITS_ERROR;
    @Value("${invalidCreditsValue.error}")
    private String INVALID_CREDITS_VALUE;
    @Value("${notPostPaid.error}")
    private String NOT_POSTPAID;
    private ObjectMapper mapper = new ObjectMapper();

    public RestaurantService(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    private Restaurant getRestaurantById(Integer ra) {
        Restaurant restaurant;
        try {
            restaurant = restaurantDao.findById(ra).get();
        } catch (Exception e) {
            restaurant = null;
        }
        return restaurant;
    }

    public ObjectNode getRestaurantByRA(Integer ra, String key) {
        ObjectNode node;
        try {
            AuthCheck.authenticate(key, ra);
            node = JsonMessage.buildMessage("success", "", restaurantDao.findById(ra).get(), mapper);
        } catch (Exception e) {
            node = JsonMessage.buildMessage("failure", e.getMessage(), mapper);
        }
        return node;
    }

    public ObjectNode save(String key, Restaurant saveRestaurant, Integer id) {
        ObjectNode node;
        try {
            AuthCheck.authenticateAdmin(key, id);
            node = JsonMessage.buildMessage("success", "", restaurantDao.save(saveRestaurant), mapper);
        } catch (Exception e) {
            node = JsonMessage.buildMessage("failure", e.getMessage(), mapper);
        }
        return node;
    }

    public ObjectNode deleteRestaurantById(Integer ra, String key, Integer id) {
        ObjectNode node;
        try {
            AuthCheck.authenticateAdmin(key, id);
            restaurantDao.deleteById(ra);
            node = JsonMessage.buildMessage("success", "", mapper);
        } catch (Exception e) {
            node = JsonMessage.buildMessage("failure", e.getMessage(), mapper);
        }
        return node;
    }

    public ObjectNode clearMealStats(String oAuthKey, Integer id) {
        ObjectNode node;
        try {
            AuthCheck.authenticateAdmin(oAuthKey, id);
            restaurantDao.clearMealStats();
            node = JsonMessage.buildMessage("success", "", mapper);
        } catch (Exception e) {
            node = JsonMessage.buildMessage("failure", e.getMessage(), mapper);
        }
        return node;
    }

    public ObjectNode eated(Integer ra, String key, Integer id) {
        Restaurant restaurant;
        ObjectNode node;
        try {
            AuthCheck.authenticateAdmin(key, id);
            Restaurant currentRestaurant = getRestaurantById(ra);
            Double currentCredits = currentRestaurant.getCredits() - RESTAURANT_FEE;
            if ((currentRestaurant.getIsPostPaid() && !currentRestaurant.getIsAlreadyAte())
                    || (currentCredits >= 0.00 && !currentRestaurant.getIsAlreadyAte())) {
                restaurantDao.eated(ra, currentCredits);
                restaurant = currentRestaurant;
                restaurant.setIsAlreadyAte(true);
                restaurant.addCredits(-RESTAURANT_FEE);
                node = JsonMessage.buildMessage("success", "", restaurant, mapper);
            } else {
                if (currentRestaurant.getIsAlreadyAte()) {
                    node = JsonMessage.buildMessage("failure", ALREADY_ATE_ERROR, mapper);
                } else {
                    node = JsonMessage.buildMessage("failure", INSUFFICIENT_CREDITS_ERROR, mapper);
                }
            }
        } catch (Exception e) {
            node = JsonMessage.buildMessage("failure", e.getMessage(), mapper);
        }
        return node;
    }

    public ObjectNode recharged(Integer ra, Double value, String key, Integer id) {
        ObjectNode node;
        try {
            AuthCheck.authenticateAdmin(key, id);
            Restaurant currentRestaurant = getRestaurantById(ra);
            if (value > 0) {
                restaurantDao.recharged(ra, currentRestaurant.getCredits() + value);
                currentRestaurant.addCredits(value);
                node = JsonMessage.buildMessage("success", "", currentRestaurant, mapper);
            } else {
                node = JsonMessage.buildMessage("failure", INVALID_CREDITS_VALUE, mapper);
            }
        } catch (Exception e) {
            node = JsonMessage.buildMessage("failure", e.getMessage(), mapper);
        }
        return node;
    }

    public ObjectNode updateRoll(Integer ra, String key, Integer id) {
        Double debt;
        ObjectNode node;
        try {
            AuthCheck.authenticateAdmin(key, id);
            Restaurant currentRestaurant = getRestaurantById(ra);
            if (currentRestaurant.getIsPostPaid()) {
                debt = currentRestaurant.getCredits();
                restaurantDao.updateRoll(ra);
                node = JsonMessage.buildMessage("success", "", Double.valueOf(debt), mapper);
            } else {
                node = JsonMessage.buildMessage("failure", NOT_POSTPAID, mapper);
            }
        } catch (Exception e) {
            node = JsonMessage.buildMessage("failure", e.getMessage(), mapper);
        }
        return node;
    }
}
