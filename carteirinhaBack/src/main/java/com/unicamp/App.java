package com.unicamp;

import java.time.ZonedDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbctemplate;
    public static ZonedDateTime lastUpdate;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8080/auth/update?key=unicamp.ic.yksj2374")
                .build();
        client.newCall(request).execute();
    }
}
