package com.example.spring_boot_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.spring_boot_test.data.Jwt;
import com.example.spring_boot_test.data.LoginDto;
import com.example.spring_boot_test.data.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText et_email, et_pwd ;
    private Button btn_login, btn_register, button;
    private static TextView textView;
    private static String jwt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_email = findViewById(R.id.et_email);
        et_pwd = findViewById(R.id.et_pwd);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail = et_email.getText().toString();
                String strPwd = et_pwd.getText().toString();

                login(strEmail, strPwd);

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserInfo(jwt);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    public static void login(String strEmail, String strPwd) {
        LoginDto loginDto = new LoginDto(strEmail, strPwd);

        RetrofitClient retrofitClient = new RetrofitClient();
        Call<Jwt> call = retrofitClient.retrofitService.login(loginDto);
        call.enqueue(new Callback<Jwt>() {
            @Override
            public void onResponse(Call<Jwt> call, Response<Jwt> response) {
                Log.i("Tag", "Success@@!!@ : " + response.toString());
                if(response.isSuccessful()){
                    jwt = response.body().getToken();
                    Log.i("Tag", "Success@@!! : " + response.body().toString());
                    textView.setText(jwt);
                } else {
                    Log.i("Tag", "Failed@@@");
                }
            }
            @Override
            public void onFailure(Call<Jwt> call, Throwable t) {
                Log.d("Tag", "Failed@@" + t.getMessage());
            }
        });
    }
    public static void getUserInfo(String jwt) {
        RetrofitClient retrofitClient = new RetrofitClient();
        Call<User> call = retrofitClient.retrofitService.getUserInfo("Bearer " +jwt);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("Tag", "Success@@!!@ : " + response.toString());
                if(response.isSuccessful()){
                    User user = response.body();
                    Log.i("Tag", "Success@@!!@ authorityName : " + user.authorityDtoSet.get(0).getAuthorityName());
                    Log.i("Tag", "Success@@!! : " + response.body().toString());
                    textView.setText(user.authorityDtoSet.get(0).getAuthorityName());
                } else {
                    Log.i("Tag", "Failed@@@");
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Tag", "Failed@@" + t.getMessage());
            }
        });
    }
}