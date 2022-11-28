package com.unicamp.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="student")
public class Student {
    @Id
    @Column(name="ra", nullable = false)
    private Integer ra;
    @Column(name="name", nullable = false, length = 200)
    private String name;
    @Column(name="course", nullable = false, length = 100)
    private String course;
    @Column(name="url", nullable = false, length = 300)
    private String imageUrl;
    @Column(name="exp_date", nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm")
    private Date expirationDate;
    @Column(name="cpf", nullable = false, length = 11)
    private String cpf;

    public Student(){}

    public Student(Integer ra, String name, String course, String imageUrl, Date date, String cpf) {
        this.ra = ra;
        this.name = name;
        this.course = course;
        this.imageUrl = imageUrl;
        this.expirationDate = date;
        this.cpf = cpf;
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
