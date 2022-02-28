package com.example.spring_boot_test.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserHealthDto {
    @SerializedName("userid")
    private String userid;
    @SerializedName("date")
    private String date;
    @SerializedName("weight")
    private float weight;
    @SerializedName("body_fat")
    private float body_fat;
    @SerializedName("body_muscle")
    private float body_muscle;
    @SerializedName("menu_planner")
    private String menu_planner;
    @SerializedName("exercise_method")
    private String exercise_method;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getBody_fat() {
        return body_fat;
    }

    public void setBody_fat(float body_fat) {
        this.body_fat = body_fat;
    }

    public float getBody_muscle() {
        return body_muscle;
    }

    public void setBody_muscle(float body_muscle) {
        this.body_muscle = body_muscle;
    }

    public String getMenu_planner() {
        return menu_planner;
    }

    public void setMenu_planner(String menu_planner) {
        this.menu_planner = menu_planner;
    }

    public String getExercise_method() {
        return exercise_method;
    }

    public void setExercise_method(String exercise_method) {
        this.exercise_method = exercise_method;
    }






}
