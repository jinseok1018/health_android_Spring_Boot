package com.example.spring_boot_test.data;

import java.util.ArrayList;

public class MyListItem {
    private String userid;
    private String sex;
    private String height;
    public ArrayList<UserDto.AuthorityDto> authorityDtoSet;

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
    public ArrayList<UserDto.AuthorityDto> getAuthorityDtoSet() {
        return authorityDtoSet;
    }
    public void setAuthorityDtoSet(ArrayList<UserDto.AuthorityDto> authorityDtoSet) {
        this.authorityDtoSet = authorityDtoSet;
    }
}
