package com.unicamp.controller;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unicamp.Utils.JsonMessage;
import com.unicamp.entity.Restaurant;
import com.unicamp.service.RestaurantService;

@RestController
@RequestMapping("/ru")
public class RestaurantController {
    @Autowired
    private final RestaurantService restaurantService;
    private ObjectMapper mapper = new ObjectMapper();

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @RequestMapping(value = "/updateMeals", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode clearMealStats(@RequestBody ObjectNode response) {
        return restaurantService.clearMealStats(response.get("key").asText());
    }

    @RequestMapping(value = "/insert", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode insertRestaurant(@RequestBody ObjectNode response) {
        return restaurantService.save(response.get("key").asText(),
                mapper.convertValue(response.get("restaurant"), Restaurant.class));
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode getRestaurantById(@RequestParam int id) {
        Restaurant restaurant = restaurantService.getRestaurantById(Integer.valueOf(id));
        if(Objects.isNull(restaurant)){
            return JsonMessage.buildMessage("failure", "", mapper);
        }else{
            return JsonMessage.buildMessage("success", "", mapper);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ObjectNode deleteRestaurantById(@RequestParam int id) {
        return restaurantService.deleteRestaurantById(Integer.valueOf(id));
    }

    @RequestMapping(value = "/eated", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode eated(@RequestParam int id) {
        return restaurantService.eated(Integer.valueOf(id));
    }

    @RequestMapping(value = "/recharge", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode recharge(@RequestParam int id, @RequestParam double value) {
        return restaurantService.recharged(Integer.valueOf(id), value);
    }

    @RequestMapping(value = "/updateRoll", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode updateRoll(@RequestParam String key, @RequestParam int id) {
        return restaurantService.updateRoll(Integer.valueOf(id));
    }
}
