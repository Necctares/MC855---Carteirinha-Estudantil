package com.unicamp.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.unicamp.entity.Oauth;

public interface OauthDao extends CrudRepository<Oauth, Integer> {
    @Modifying
    @Query("UPDATE Oauth SET access_token = :accessToken WHERE ra = :studentRA")
    @Transactional
    void updateToken(@Param("studentRA") Integer studentRA, @Param("accessToken") String accessToken);
}
