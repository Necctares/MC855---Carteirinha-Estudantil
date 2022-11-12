package com.unicamp.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.unicamp.entity.Restaurant;

public interface RestaurantDao extends CrudRepository<Restaurant, Integer> {
    @Modifying
    @Query("UPDATE Restaurant SET already_ate = 0")
    @Transactional
    void clearMealStats();

    @Modifying
    @Query("UPDATE Restaurant SET already_ate = 1, credits = :currentCredits WHERE ra = :studentRA")
    @Transactional
    void eated(@Param("studentRA") Integer studentRA, @Param("currentCredits") Double currentCredits);

    @Modifying
    @Query("UPDATE Restaurant SET credits = :currentCredits WHERE ra = :studentRA")
    @Transactional
    void recharged(@Param("studentRA") Integer studentRA, @Param("currentCredits") Double currentCredits);

    @Modifying
    @Query("UPDATE Restaurant SET credits = 0 WHERE ra = :studentRA")
    @Transactional
    void updateRoll(@Param("studentRA") Integer studentRA);
}
