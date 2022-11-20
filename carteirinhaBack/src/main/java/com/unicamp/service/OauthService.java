package com.unicamp.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unicamp.App;
import com.unicamp.Utils.AuthCheck;
import com.unicamp.Utils.JsonMessage;
import com.unicamp.dao.OauthDao;
import com.unicamp.entity.Oauth;

@Service
@PropertySource(value = "classpath:applicationValues.properties")
public class OauthService {
    private ObjectMapper mapper = new ObjectMapper();
    private OauthDao oAuthDao;
    @Value("${original.value}")
    private String originalValue;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public OauthService(OauthDao oAuthDao) {
        this.oAuthDao = oAuthDao;
    }

    public ObjectNode getOauthById(Integer ra) {
        ObjectNode node;
        try {
            node = JsonMessage.buildMessage("success", "", oAuthDao.findById(ra).get(), mapper);
        } catch (Exception e) {
            node = JsonMessage.buildMessage("failure", e.getMessage(), mapper);
        }
        return node;
    }

    public ObjectNode setOauthById(Integer ra) {
        ObjectNode node;
        try {
            Oauth oAuth = new Oauth();
            oAuth.setRa(ra);
            oAuth.setAccess_token(AuthCheck.generateHash(
                    ra.toString() + App.lastUpdate.format(formatter) + originalValue));
            node = JsonMessage.buildMessage("success", "", oAuthDao.save(oAuth), mapper);
        } catch (Exception e) {
            node = JsonMessage.buildMessage("failure", e.getMessage(), mapper);
        }
        return node;
    }

    private boolean updateOauth(Oauth auth) {
        try {
            auth.setAccess_token(AuthCheck.generateHash(
                    auth.getRa().toString() + App.lastUpdate.format(formatter) + originalValue));
            oAuthDao.save(auth);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean updateAccessTokens(String key) {
        ZonedDateTime aux = App.lastUpdate;
        try {
            if (key.equals(originalValue)) {
                App.lastUpdate = ZonedDateTime.now(ZoneId.of("Z"));
                Iterator<Oauth> oAuths = oAuthDao.findAll().iterator();
                while (oAuths.hasNext()) {
                    updateOauth(oAuths.next());
                }
            }
        } catch (Exception e) {
            App.lastUpdate = aux;
            return false;
        }
        return true;
    }
}
