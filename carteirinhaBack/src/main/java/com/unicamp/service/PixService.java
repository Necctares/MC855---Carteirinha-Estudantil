package com.unicamp.service;

import java.io.IOException;
import org.json.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PixService {

    private static String basic_auth_string = "Basic ZXlKcFpDSTZJbUVpTENKamIyUnBaMjlRZFdKc2FXTmhaRzl5SWpvd0xDSmpiMlJwWjI5VGIyWjBkMkZ5WlNJNk5EWXhNelFzSW5ObGNYVmxibU5wWVd4SmJuTjBZV3hoWTJGdklqb3hmUTpleUpwWkNJNklqTTBObUZtTWpjdFltRTJaaTAwTlRFNExUbGtZeUlzSW1OdlpHbG5iMUIxWW14cFkyRmtiM0lpT2pBc0ltTnZaR2xuYjFOdlpuUjNZWEpsSWpvME5qRXpOQ3dpYzJWeGRXVnVZMmxoYkVsdWMzUmhiR0ZqWVc4aU9qRXNJbk5sY1hWbGJtTnBZV3hEY21Wa1pXNWphV0ZzSWpveExDSmhiV0pwWlc1MFpTSTZJbWh2Ylc5c2IyZGhZMkZ2SWl3aWFXRjBJam94TmpZMU5qZ3pOek16TVRNeGZR";
    private static String dev_app_key = "d27b977908ffab20136ae17d70050f56b971a5bb";

    private ObjectMapper mapper = new ObjectMapper();

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

    // TODO: RETORNAR ObjectNode
    public static ObjectNode generatePix(String cpf, String nome, String valor) {
        try {
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
            ObjectNode json = mapper.readTree(response.body().string());
            // TODO: ADICIONAR TRANSAÇÃO NO DB
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String consultPix(String txid) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create("", mediaType);
            Request request = new Request.Builder()
                    .url("https://api.hm.bb.com.br/pix/v1/cob/" + txid + "?gw-dev-app-key=" + dev_app_key)
                    .addHeader("Authorization", "Bearer " + PixService.generateAuthToken())
                    .build();
            Response response = client.newCall(request).execute();
            // ATUALIZAR TRANSAÇÃO NO DB E CREDITAR ALUNO
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generateTxid(String cpf, String nome){
        nome = nome.replace("-", "").toUpperCase();
        return nome + cpf + "000001";
    }

}
