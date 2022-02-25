package com.example.spring_boot_test.data;

import com.google.gson.annotations.SerializedName;

public class JwtSingleton {
    private static JwtSingleton jwSingleton = null;
    @SerializedName("token")
    private String token = null;

    private JwtSingleton() {
    }

    public static JwtSingleton getInstance() {
        if (null == jwSingleton) {
            jwSingleton = new JwtSingleton();
        }
        return jwSingleton;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
