package com.unicamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
        return restaurantService.clearMealStats(response.get("key").asText(), response.get("id").asInt());
    }

    @RequestMapping(value = "/insert", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode insertRestaurant(@RequestBody ObjectNode response) {
        return restaurantService.save(response.get("key").asText(),
                mapper.convertValue(response.get("restaurant"), Restaurant.class), response.get("id").asInt());
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode getRestaurantById(@RequestBody ObjectNode response) {
        return restaurantService.getRestaurantByRA(response.get("ra").asInt(), response.get("key").asText());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode deleteRestaurantById(@RequestBody ObjectNode response) {
        return restaurantService.deleteRestaurantById(response.get("ra").asInt(), response.get("key").asText(), response.get("id").asInt());
    }

    @RequestMapping(value = "/eated", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode eated(@RequestBody ObjectNode response) {
        return restaurantService.eated(response.get("ra").asInt(), response.get("key").asText(), response.get("id").asInt());
    }

    @RequestMapping(value = "/recharge", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode recharge(@RequestBody ObjectNode response) {
        return restaurantService.recharged(response.get("ra").asInt(), response.get("value").asDouble(), response.get("key").asText(), response.get("id").asInt());
    }

    @RequestMapping(value = "/updateRoll", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode updateRoll(@RequestBody ObjectNode response) {
        return restaurantService.updateRoll(response.get("ra").asInt(), response.get("key").asText(), response.get("id").asInt());
    }
}
