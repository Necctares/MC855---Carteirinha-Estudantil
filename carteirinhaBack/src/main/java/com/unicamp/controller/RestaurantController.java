package com.unicamp.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import com.unicamp.entity.Restaurant;
import com.unicamp.service.RestaurantService;

@RestController
@RequestMapping("/ru")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Restaurant> getAllRestaurantStats() {
        return restaurantService.getAllRestaurantStats();
    }

    @RequestMapping(value = "/{ra}", method = RequestMethod.GET)
    public Restaurant getRestaurantStatsById(@PathVariable("ra") Integer ra) {
        return restaurantService.getRestaurantStatsById(ra);
    }

    @RequestMapping(value = "/{ra}", method = RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable("ra") Integer ra) {
        restaurantService.removeRestaurantStatsById(ra);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertStudent(@RequestBody Restaurant restaurant) {
        restaurantService.insertRestaurantToDB(restaurant);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void clearMealStats() {
        restaurantService.clearMealStats();
    }
}
