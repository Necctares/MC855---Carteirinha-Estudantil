package com.unicamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import com.unicamp.entity.Restaurant;
import com.unicamp.service.RestaurantService;

@RestController
@RequestMapping("/ru")
public class RestaurantController {
    @Autowired
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @RequestMapping(value = "/updateMeals", method = RequestMethod.PUT)
    public boolean clearMealStats(@RequestParam String key) {
        return restaurantService.clearMealStats();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertRestaurant(@RequestParam String key, @RequestBody Restaurant restaurant) {
        restaurantService.save(restaurant);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getRestaurantById(@RequestParam int id) {
        return restaurantService.getRestaurantById(Integer.valueOf(id));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public boolean deleteRestaurantById(@RequestParam int id) {
        return restaurantService.deleteRestaurantById(Integer.valueOf(id));
    }

    @RequestMapping(value = "/eated", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant eated(@RequestParam int id) {
        return restaurantService.eated(Integer.valueOf(id));
    }

    @RequestMapping(value = "/recharge", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant recharge(@RequestParam int id, @RequestParam double value) {
        return restaurantService.recharged(Integer.valueOf(id), value);
    }

    @RequestMapping(value = "/updateRoll", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Double updateRoll(@RequestParam String key, @RequestParam int id){
        return restaurantService.updateRoll(Integer.valueOf(id));
    }
}
