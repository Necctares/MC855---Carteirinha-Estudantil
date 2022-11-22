package com.unicamp.service;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unicamp.Utils.AuthCheck;
import com.unicamp.Utils.JsonMessage;
import com.unicamp.dao.LoginDao;
import com.unicamp.entity.Login;

@Service
public class LoginService {
    private LoginDao loginDao;
    private ObjectMapper mapper = new ObjectMapper();

    public LoginService(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public ObjectNode login(Integer ra, String password) {
        ObjectNode node;
        try {
            Login login = loginDao.findById(ra).get();
            if (login.getPassword().equals(AuthCheck.generateHash(password))) {
                node = JsonMessage.buildMessage("success", "", mapper);
            } else {
                node = JsonMessage.buildMessage("failure", "Invalid password", mapper);
            }
        } catch (Exception e) {
            node = JsonMessage.buildMessage("failure", e.getMessage(), mapper);
        }
        return node;
    }
}
