package com.unicamp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    Boolean isAlreadyAte;

    Restaurant(){}

    @JsonProperty("already_ate")
    public Boolean getIsAlreadyAte() {
        return isAlreadyAte;
    }

    @JsonProperty("is_post_paid")
    public Boolean getIsPostPaid() {
        return isPostPaid;
    }

    public Double getCredits() {
        return credits;
    }

    public Integer getRa() {
        return ra;
    }

    @JsonProperty("already_ate")
    public void setIsAlreadyAte(Boolean isAlreadyAte) {
        this.isAlreadyAte = isAlreadyAte;
    }

    @JsonProperty("is_post_paid")
    public void setIsPostPaid(Boolean isPostPaid) {
        this.isPostPaid = isPostPaid;
    }

    public void addCredits(Double value) {
        credits += value;
    }
}
