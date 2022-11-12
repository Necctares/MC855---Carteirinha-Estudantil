package com.unicamp.service;

import org.springframework.stereotype.Service;
import com.unicamp.dao.RestaurantDao;
import com.unicamp.entity.Restaurant;

@Service
public class RestaurantService {

    private RestaurantDao restaurantDao;
    private static final Double RESTAURANT_FEE = 3.00;

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

    public Restaurant save(Restaurant saveRestaurant) {
        Restaurant restaurant;
        try {
            restaurant = restaurantDao.save(saveRestaurant);
        } catch (Exception e) {
            restaurant = null;
            System.out.println(e.getMessage());
        }
        return restaurant;
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

    public boolean clearMealStats() {
        boolean successfull;
        try {
            restaurantDao.clearMealStats();
            successfull = true;
        } catch (Exception e) {
            successfull = false;
            System.out.println(e.getMessage());
        }
        return successfull;
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
