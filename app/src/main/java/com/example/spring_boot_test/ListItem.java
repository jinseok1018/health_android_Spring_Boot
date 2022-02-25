package com.example.spring_boot_test;

import com.example.spring_boot_test.data.User;

import java.util.ArrayList;

public class ListItem {
    private String userid;
    private String sex;
    private String height;
    public ArrayList<User.AuthorityDto> authorityDtoSet;

    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public ArrayList<User.AuthorityDto> getAuthorityDtoSet() {
        return authorityDtoSet;
    }
    public void setAuthorityDtoSet(ArrayList<User.AuthorityDto> authorityDtoSet) {
        this.authorityDtoSet = authorityDtoSet;
    }
}
