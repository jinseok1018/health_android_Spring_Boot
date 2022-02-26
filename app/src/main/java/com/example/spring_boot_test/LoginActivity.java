package com.example.spring_boot_test;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.spring_boot_test.SessionManager.SessionManager;
import com.example.spring_boot_test.data.JwtSingleton;
import com.example.spring_boot_test.data.LoginDto;
import com.example.spring_boot_test.data.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText et_email, et_pwd ;
    private Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = findViewById(R.id.et_email);
        et_pwd = findViewById(R.id.et_pwd);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail = et_email.getText().toString();
                String strPwd = et_pwd.getText().toString();
                login(strEmail, strPwd);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    public void login(String strEmail, String strPwd) {
        LoginDto loginDto = new LoginDto(strEmail, strPwd);

        RetrofitClient retrofitClient = new RetrofitClient();
        Call<JwtSingleton> call = retrofitClient.retrofitService.login(loginDto);
        call.enqueue(new Callback<JwtSingleton>() {
            @Override
            public void onResponse(Call<JwtSingleton> call, Response<JwtSingleton> response) {
                Log.i("login", "response : " + response.toString());
                if(response.isSuccessful()){
                    Log.i("login", "Success : " + response.body().getToken());

                    JwtSingleton.getInstance().setToken(response.body().getToken());

                    Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                    startActivity(intent);
                } else {
                    Log.i("login", "Failed");
                }
            }
            @Override
            public void onFailure(Call<JwtSingleton> call, Throwable t) {
                Log.d("login", "onFailure" + t.getMessage());
            }
        });
    }
}