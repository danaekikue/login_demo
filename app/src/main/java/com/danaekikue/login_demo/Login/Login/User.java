package com.danaekikue.login_demo.Login.Login;

public class User {

    private int expires_in;
    private String token_type;
    private String refresh_token;
    private String access_token;


    public User(int expires_in, String token_type, String refresh_token, String access_token) {
        this.expires_in = expires_in;
        this.token_type = token_type;
        this.refresh_token = refresh_token;
        this.access_token = access_token;
    }

    public int getExpiration() {
        return expires_in;
    }

    public void setExpiration(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getTokenType() {
        return token_type;
    }

    public void setTokenType(String refresh_token) {
        this.token_type = token_type;
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    public void setRefreshToken(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String access_token) {
        this.access_token = access_token;
    }


}