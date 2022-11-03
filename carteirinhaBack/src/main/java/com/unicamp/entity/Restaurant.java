package com.unicamp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="restaurant")
public class Restaurant {
    @Id
    @Column(name="ra", nullable = false)
    Integer ra;
    @Column(name="credits", nullable = false)
    Double credits;
    @Column(name="is_post_paid", nullable = false)
    Boolean isPostPaid;
    @Column(name="already_ate", nullable = false)
    Boolean alreadyAte;

    public Boolean alreadyAte() {
        return alreadyAte;
    }

    public void cleanMeal() {
        alreadyAte = false;
    }

    public void setAlreadyAte() {
        alreadyAte = true;
    }

    public Boolean isPostPaid() {
        return isPostPaid;
    }

    public Double getCredits() {
        return credits;
    }

    public Integer getRa() {
        return ra;
    }
}
