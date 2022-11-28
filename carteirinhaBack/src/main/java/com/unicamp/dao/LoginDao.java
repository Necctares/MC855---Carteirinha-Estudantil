package com.unicamp.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.unicamp.entity.Login;
import com.unicamp.entity.Oauth;

public interface LoginDao extends CrudRepository<Login, Integer> {
    @Query("SELECT o FROM Oauth o WHERE ra = :studentRA")
    Oauth getToken(@Param("studentRA") Integer studentRA);
}
