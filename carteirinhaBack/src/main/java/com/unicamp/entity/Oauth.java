package com.unicamp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth")
public class Oauth {
    @Id
    @Column(name = "ra", nullable = false)
    private Integer ra;
    @Column(name = "access_token", nullable = false)
    private String access_token;

    public Oauth() {
    }

    public Integer getRa() {
        return ra;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setRa(Integer ra) {
        this.ra = ra;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
