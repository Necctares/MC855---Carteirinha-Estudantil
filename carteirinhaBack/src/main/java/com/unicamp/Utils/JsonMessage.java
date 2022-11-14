package com.unicamp.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonMessage {

    public static ObjectNode buildMessage(String status, String message, Object pojo, ObjectMapper mapper) {
        ObjectNode jsonResponse = mapper.createObjectNode();
        jsonResponse.put("response", status);
        jsonResponse.put("message", message);
        String[] className = pojo.getClass().toString().split("[.]");
        jsonResponse.putPOJO(className[className.length - 1].toLowerCase(), pojo);
        return jsonResponse;
    }

    public static ObjectNode buildMessage(String status, String message, ObjectMapper mapper) {
        ObjectNode jsonResponse = mapper.createObjectNode();
        jsonResponse.put("response", status);
        jsonResponse.put("message", message);
        return jsonResponse;
    }
}
