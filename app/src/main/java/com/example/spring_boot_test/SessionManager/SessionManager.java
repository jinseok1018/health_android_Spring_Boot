package com.example.spring_boot_test.SessionManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";

    // 변수명을 대문자로 작성해야함
    public static final String TOKEN = "Token";
    public static final String USERID = "UserID";
    public static final String SEX = "Sex";
    public static final String HEIGHT = "Height";

    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

//    public void createSession(String UserID, boolean Sex, int Height, String Token){
//        editor.putBoolean(LOGIN, true);
//        editor.putString(USERID, UserID);
//        editor.putBoolean(SEX, Sex);
//        editor.putInt(HEIGHT, Height);
//        editor.putString(TOKEN, Token);
//        editor.apply();
//    }
public void createSession(String UserID, String Token){
    editor.putBoolean(LOGIN, true);
    editor.putString(USERID, UserID);

    editor.putString(TOKEN, Token);
    editor.apply();
}
    public HashMap<String, String> getUserDetail()    {
        HashMap<String, String> user = new HashMap<>();
        user.put(USERID, sharedPreferences.getString(USERID, null));
        user.put(TOKEN, sharedPreferences.getString(TOKEN, null));

        return user;
    }
    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

//    public HashMap<String, String> getUserDetail()    {
//        HashMap<String, String> user = new HashMap<>();
//        user.put(USERID, sharedPreferences.getString(USERID, null));
//        user.put(SEX, sharedPreferences.getString(SEX, null));
//        user.put(HEIGHT, sharedPreferences.getString(HEIGHT, null));
//        user.put(TOKEN, sharedPreferences.getString(TOKEN, null));
//
//        return user;
//    }


    public void editorClear(){
        editor.clear();
        editor.commit();
    }

}

