package com.unicamp.vo;

import java.sql.Date;
import com.unicamp.entity.Student;

public class StudentVo {
    private Integer ra;
    private String name;
    private String course;
    private String imageUrl;
    private Date expirationDate;

    public StudentVo(Student student) {
        this.ra = student.getRa();
        this.name = student.getName();
        this.course = student.getCourse();
        this.imageUrl = student.getImageUrl();
        this.expirationDate = student.getExpirationDate();
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
