package com.unicamp;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class App implements CommandLineRunner{

    @Autowired
    private JdbcTemplate jdbctemplate;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String query = "INSERT INTO student (ra, name, course, url, exp_date) VALUES (?, ?, ?, ?, ?)";
        //jdbctemplate.update(query, 192513, "Alisson Bispo Freire", "Engenharia Mecanica", "img/192513", new Date(450000));
    }
}
