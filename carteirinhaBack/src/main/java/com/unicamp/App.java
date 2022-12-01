package com.unicamp;

import java.time.ZonedDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class App {

    @Autowired
    private JdbcTemplate jdbctemplate;
    public static ZonedDateTime lastUpdate;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
