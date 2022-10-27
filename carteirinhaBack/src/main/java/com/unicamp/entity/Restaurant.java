package com.unicamp.entity;

public class Restaurant {
    Integer ra;
    Double credits;
    Boolean isPostPaid;
    Boolean alreadyAte;

    Boolean alreadyAte() {
        return alreadyAte;
    }

    Boolean isPostPaid() {
        return isPostPaid;
    }

    Double getCredits() {
        return credits;
    }

    Integer getRa() {
        return ra;
    }
}
