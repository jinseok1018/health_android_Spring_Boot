package com.example.spring_boot_test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spring_boot_test.data.RegisterDto;
import com.example.spring_boot_test.data.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_email, et_pwd, et_height;
    private Button btn_register;
    private RadioButton rbtn_men, rbtn_women;
    private boolean sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_email = findViewById(R.id.et_email);
        et_pwd = findViewById(R.id.et_pwd);
        et_height = findViewById(R.id.et_height);
        btn_register = findViewById(R.id.btn_register);
        rbtn_men = findViewById(R.id.rbtn_men);
        rbtn_women = findViewById(R.id.rbtn_women);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = et_email.getText().toString();
                String userPassword = et_pwd.getText().toString();
                int userHeight = Integer.parseInt(et_height.getText().toString());

                if(userID.length() < 3){
                    Toast.makeText(getApplicationContext(), "3글자 이상의 ID로 만들어주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    if (rbtn_men.isChecked()){
                        sex = true;
                    } else if (rbtn_women.isChecked()){
                        sex = false;
                    } else {
                        Toast.makeText(getApplicationContext(), "성별을 선택해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
                register(userID, userPassword, sex, userHeight);
            }
        });
    }
    public void register(String userID, String userPassword, boolean sex, int userHeight) {
        RegisterDto registerDto = new RegisterDto(userID, userPassword, sex, userHeight);

        RetrofitClient retrofitClient = new RetrofitClient();
        Call<User> call = retrofitClient.retrofitService.register(registerDto);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("register", "response : " + response.toString());
                if(response.isSuccessful()){
                    Log.i("register", "Success : " + response.body().toString());
                    Toast.makeText(getApplicationContext(), "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("register", "Failed");
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("register", "onFailure" + t.getMessage());
            }
        });
    }
}
