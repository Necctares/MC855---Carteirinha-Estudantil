package com.unicamp.entity;

import java.sql.Date;

public class Student {
    private Integer ra;
    private String name;
    private String course;
    private String imageUrl;
    private Date expirationDate;

    public Student(Integer ra, String name, String course, String imageUrl, Date date) {
        this.ra = ra;
        this.name = name;
        this.course = course;
        this.imageUrl = imageUrl;
        this.expirationDate = date;
    }

    public Integer getRa() {
        return ra;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
}
