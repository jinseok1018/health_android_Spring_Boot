package com.example.spring_boot_test.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RegisterDto {
    @SerializedName("userid")
    public String userid;
    @SerializedName("password")
    public String password;
    @SerializedName("sex")
    public boolean sex;
    @SerializedName("height")
    public int height;

    public RegisterDto(String userid, String password, boolean sex, int height) {
        this.userid = userid;
        this.password = password;
        this.sex = sex;
        this.height = height;
    }

    @SerializedName("authorityDtoSet")
    public ArrayList<UserDto.AuthorityDto> authorityDtoSet;



    public class AuthorityDto {
        @SerializedName("authorityName")
        public String authorityName;

        public String getAuthorityName() {
            return authorityName;
        }
    }

    @Override
    public String toString() {
        return "PostResult{" +
                "userid=" + userid +
                ", sex=" + sex +
                ", height='" + height + '\'' +
                ", authorityDtoSet='" + authorityDtoSet.toString() + '\'' +
                '}';
    }
}
