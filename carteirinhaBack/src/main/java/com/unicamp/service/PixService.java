package com.unicamp.service;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unicamp.Utils.JsonMessage;
import com.unicamp.dao.PixTransferenceDao;
import com.unicamp.dao.RestaurantDao;
import com.unicamp.entity.PixTransference;
import com.unicamp.entity.Restaurant;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
@PropertySource(value = "classpath:applicationValues.properties")
public class PixService {

    private static String basic_auth_string = "Basic ZXlKcFpDSTZJbUVpTENKamIyUnBaMjlRZFdKc2FXTmhaRzl5SWpvd0xDSmpiMlJwWjI5VGIyWjBkMkZ5WlNJNk5EWXhNelFzSW5ObGNYVmxibU5wWVd4SmJuTjBZV3hoWTJGdklqb3hmUTpleUpwWkNJNklqTTBObUZtTWpjdFltRTJaaTAwTlRFNExUbGtZeUlzSW1OdlpHbG5iMUIxWW14cFkyRmtiM0lpT2pBc0ltTnZaR2xuYjFOdlpuUjNZWEpsSWpvME5qRXpOQ3dpYzJWeGRXVnVZMmxoYkVsdWMzUmhiR0ZqWVc4aU9qRXNJbk5sY1hWbGJtTnBZV3hEY21Wa1pXNWphV0ZzSWpveExDSmhiV0pwWlc1MFpTSTZJbWh2Ylc5c2IyZGhZMkZ2SWl3aWFXRjBJam94TmpZMU5qZ3pOek16TVRNeGZR";
    private static String dev_app_key = "d27b977908ffab20136ae17d70050f56b971a5bb";

    private PixTransferenceDao pixTransferenceDao;
    private RestaurantDao restaurantDao;
    private ObjectMapper mapper = new ObjectMapper();

    public PixService(PixTransferenceDao pixTransferenceDao) {
        this.pixTransferenceDao = pixTransferenceDao;
    }

    private static String generateAuthToken() {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create("grant_type=client_credentials&scope=cob.read cob.write pix.read pix.write", mediaType);
            Request request = new Request.Builder()
                    .url("https://oauth.hm.bb.com.br/oauth/token")
                    .method("POST", body)
                    .addHeader("Authorization", basic_auth_string)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            Response response = client.newCall(request).execute();
            JSONObject json = new JSONObject(response.body().string());
            return json.getString("access_token");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObjectNode generatePix(Integer ra, String cpf, String nome, String valor) {
        try {
            // Communicate with BB Pix API to generate new transaction
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create("{\n    \"calendario\": {\n        \"expiracao\": 300\n    },\n    \"devedor\": {\n        \"cpf\": " + cpf + ",\n        \"nome\": \"" + nome + "\"\n    },\n    \"valor\": {\n        \"original\": " + valor + "\n    },\n    \"chave\": \"28779295827\",\n    \"solicitacaoPagador\": \"Recarga de RA.\"\n}", mediaType);
            Request request = new Request.Builder()
                    .url("https://api.hm.bb.com.br/pix/v1/cobqrcode/" + PixService.generateTxid(cpf, nome) + "?gw-dev-app-key=" + dev_app_key)
                    .method("PUT", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + PixService.generateAuthToken())
                    .build();
            Response response = client.newCall(request).execute();
            ObjectNode json = mapper.readValue(response.body().string(), ObjectNode.class);

            // Get and format date from BB response
            String strDate = json.get("calendario").get("criacao").asText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            java.util.Date date = sdf.parse(strDate);
            java.sql.Date sqlDate = new Date(date.getTime());
            
            // Create new pixTransference and save it to db
            PixTransference newPixTranference = new PixTransference(json.get("txid").asText(), ra, sqlDate);
            pixTransferenceDao.save(newPixTranference);

            ObjectNode node;
            try {  
                node = JsonMessage.buildMessage("success", "", json, mapper);
            } catch (Exception e) {
                node = JsonMessage.buildMessage("failure", e.getMessage(), mapper);
            }
            return node;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    public ObjectNode consultAndCreditPix(String txid) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            // MediaType mediaType = MediaType.parse("text/plain");
            // RequestBody body = RequestBody.create("", mediaType);
            Request request = new Request.Builder()
                    .url("https://api.hm.bb.com.br/pix/v1/cob/" + txid + "?gw-dev-app-key=" + dev_app_key)
                    .addHeader("Authorization", "Bearer " + PixService.generateAuthToken())
                    .build();
            Response response = client.newCall(request).execute();
            ObjectNode json = mapper.readValue(response.body().string(), ObjectNode.class);
            
            if (json.get("status").asText() == "CONCLUIDA"){
                PixTransference transference = pixTransferenceDao.findById(json.get("txid").asText()).get();
                if (transference.isActive()){
                    Restaurant restaurant = restaurantDao.findById(transference.getRa()).get();
                    restaurant.addCredits(json.get("valor").get("original").asDouble());
                    transference.setIsActive(false);
                    transference.setIsCompleted(true);
                }
            }

            ObjectNode node;
            try {  
                node = JsonMessage.buildMessage("success", "", json, mapper);
            } catch (Exception e) {
                node = JsonMessage.buildMessage("failure", e.getMessage(), mapper);
            }
            return node;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generateTxid(String cpf, String nome){
        nome = nome.replace("-", "").toUpperCase();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        return cpf + nome + dtf.format(now);
    }

}
