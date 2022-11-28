package com.unicamp.vo;

public class LoginVo {
    private Integer ra;
    private String access_token;

    public LoginVo(Integer ra, String accessToken) {
        this.ra = ra;
        this.access_token = accessToken;
    }

    public Integer getRa() {
        return ra;
    }

    public String getAccessToken() {
        return access_token;
    }
}
