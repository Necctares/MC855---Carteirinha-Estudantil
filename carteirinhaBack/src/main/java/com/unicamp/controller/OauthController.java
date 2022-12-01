package com.unicamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unicamp.service.OauthService;

@RestController
@RequestMapping("/auth")
public class OauthController {
    @Autowired
    private final OauthService oAuthService;

    public OauthController(OauthService oAuthService) {
        this.oAuthService = oAuthService;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode setOauthById(@RequestBody ObjectNode response) {
        return oAuthService.setOauthById(response.get("ra").asInt(), response.get("id").asInt(), response.get("key").asText());
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public void updateOauths(@RequestParam("key") String key) {
        oAuthService.updateAccessTokens(key);
    }
}
