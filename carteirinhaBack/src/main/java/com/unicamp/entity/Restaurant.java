package com.unicamp.entity;

public class Restaurant {
    Integer ra;
    Double credits;
    Boolean isPostPaid;
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
