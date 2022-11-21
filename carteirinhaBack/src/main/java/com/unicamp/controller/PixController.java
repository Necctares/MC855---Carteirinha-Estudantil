package com.unicamp.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unicamp.service.PixService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pix")
public class PixController {
    @Autowired
    private final PixService pixService;

    public PixController(PixService pixService) {
        this.pixService = pixService;
    }


    @RequestMapping(value = "/generate-pix", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode generatePix(@RequestBody ObjectNode response) {
        return pixService.generatePix(response.get("ra").asInt(), response.get("cpf").asText(), response.get("nome").asText(), response.get("valor").asText());
    }

    @RequestMapping(value = "/consult-pix", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode consultPix(@RequestBody ObjectNode response) {
        return pixService.consultAndCreditPix(response.get("txid").asText());
    }

}
