package com.unicamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unicamp.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private final LoginService loginService;
    private ObjectMapper mapper = new ObjectMapper();

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode login(@RequestBody ObjectNode response) {
        return loginService.login(response.get("ra").asInt(), response.get("password").asText());
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode setLogin(@RequestBody ObjectNode response) {
        return loginService.login(response.get("ra").asInt(), response.get("password").asText());
    }
}