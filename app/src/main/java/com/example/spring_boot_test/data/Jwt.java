package com.example.spring_boot_test.data;

import com.google.gson.annotations.SerializedName;

public class Jwt {
    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    @Override
    public String toString() {
        return "PostResult{" +
                "token=" + token +
                '}';
    }
}
