package com.example.spring_boot_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.spring_boot_test.SessionManager.SessionManager;
import com.example.spring_boot_test.data.JwtSingleton;
import com.example.spring_boot_test.data.User;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.test);

        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        String userID = user.get(sessionManager.USERID);
        Log.i("Tag", "Success@@2: " + userID);

        textView.setText(userID);
    }
}