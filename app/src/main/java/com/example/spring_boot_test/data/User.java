package com.example.spring_boot_test.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class User {
    @SerializedName("userid")
    public String userid;
    @SerializedName("sex")
    public boolean sex;
    @SerializedName("height")
    public int height;
    @SerializedName("authorityDtoSet")
    public ArrayList<AuthorityDto> authorityDtoSet;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ArrayList<AuthorityDto> getAuthorityDtoSet() {
        return authorityDtoSet;
    }

    public void setAuthorityDtoSet(ArrayList<AuthorityDto> authorityDtoSet) {
        this.authorityDtoSet = authorityDtoSet;
    }

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
