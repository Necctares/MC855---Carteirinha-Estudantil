package com.unicamp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "login")
public class Login {
    @Id
    @Column(name = "ra", nullable = false)
    private Integer ra;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "isAdmin", nullable = false)
    @JsonProperty("isAdmin")
    private Boolean isAdmin;

    public Login() {
    }

    public Login(Integer ra, String password, boolean isAdmin) {
        this.ra = ra;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Integer getRa() {
        return ra;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setRa(Integer ra) {
        this.ra = ra;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
