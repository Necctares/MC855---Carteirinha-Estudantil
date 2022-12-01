package com.unicamp.service;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unicamp.Utils.AuthCheck;
import com.unicamp.Utils.JsonMessage;
import com.unicamp.dao.LoginDao;
import com.unicamp.entity.Login;
import com.unicamp.entity.Oauth;
import com.unicamp.vo.LoginVo;

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
            Oauth oAuth = loginDao.getToken(ra);
            if (login.getPassword().equals(AuthCheck.generateHash(password))) {
                node = JsonMessage.buildMessage("success", "", new LoginVo(ra, oAuth.getAccess_token()) , mapper);
            } else {
                node = JsonMessage.buildMessage("failure", "Invalid password", mapper);
            }
        } catch (Exception e) {
            node = JsonMessage.buildMessage("failure", e.getMessage(), mapper);
        }
        return node;
    }

    public ObjectNode setLogin(Integer ra, String password) {
        ObjectNode node;
        try {
            loginDao.save(new Login(ra, AuthCheck.generateHash(password), false));
            node = JsonMessage.buildMessage("success", "operation completed", mapper);
        } catch (Exception e) {
            node = JsonMessage.buildMessage("failure", e.getMessage(), mapper);
        }
        return node;
    }
}
