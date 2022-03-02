package com.example.spring_boot_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spring_boot_test.ListViewAdapter.MyListViewAdapter;
import com.example.spring_boot_test.Retrofit.RetrofitClient;
import com.example.spring_boot_test.SessionManager.SessionManager;
import com.example.spring_boot_test.data.JwtSingleton;
import com.example.spring_boot_test.data.UserHealthDto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MyHealthListActivity extends AppCompatActivity {
    SessionManager sessionManager;
    private ListView listView;
    private MyListViewAdapter adapter;
    private TextView tv_id,tv_sex,tv_height;
    private EditText et_weight,et_bodyfat,et_bodymuscle,et_menuplan,et_excersicemethod;
    private Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_healt_list);

        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_sex = (TextView)findViewById(R.id.tv_sex);
        tv_height = (TextView)findViewById(R.id.tv_height);
        btn_add = (Button) findViewById(R.id.btn_add);
        et_weight = (EditText) findViewById(R.id.et_weight);
        et_bodyfat = (EditText) findViewById(R.id.et_bodyfat);
        et_bodymuscle = (EditText) findViewById(R.id.et_bodymuscle);
        et_menuplan = (EditText) findViewById(R.id.et_menuplan);
        et_excersicemethod = (EditText) findViewById(R.id.et_excersicemethod);

        sessionManager = new SessionManager(this);
        HashMap<String, Object> user = sessionManager.getUserDetail();
        tv_id.setText(user.get(sessionManager.USERID).toString());
        tv_sex.setText(user.get(sessionManager.SEX).toString() == "true"?"Men":"Women");
        tv_height.setText(user.get(sessionManager.HEIGHT).toString());

        listView = findViewById(R.id.listview);
        adapter = new MyListViewAdapter(MyHealthListActivity.this);
        listView.setAdapter(adapter);

        getMyList();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long mNow = System.currentTimeMillis();
                Date date = new Date(mNow);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String getTime = sdf.format(date);

                UserHealthDto userHealthDto = new UserHealthDto();
                userHealthDto.setUserid(user.get(sessionManager.USERID).toString());
                userHealthDto.setDate(getTime);
                userHealthDto.setWeight(Integer.parseInt(et_weight.getText().toString()));
                userHealthDto.setBody_fat(Integer.parseInt(et_bodyfat.getText().toString()));
                userHealthDto.setBody_muscle(Integer.parseInt(et_bodymuscle.getText().toString()));
                userHealthDto.setMenu_planner(et_menuplan.getText().toString());
                userHealthDto.setExercise_method(et_excersicemethod.getText().toString());

                insertList(userHealthDto);
            }
        });
    }
    private void insertList(UserHealthDto userHealthDto){
        RetrofitClient retrofitClient = new RetrofitClient();
        Call<UserHealthDto> call = retrofitClient.retrofitService.insertHealth(userHealthDto,"Bearer " + JwtSingleton.getInstance().getToken());
        call.enqueue(new Callback<UserHealthDto>() {
            @Override
            public void onResponse(Call<UserHealthDto> call, retrofit2.Response<UserHealthDto> response) {
                Log.i("insertList", "Response : " + response.toString());
                if(response.isSuccessful()){
                    Log.i("insertList", "Success : " + response.body().toString());

                    UserHealthDto userHealthDto1 = (UserHealthDto) response.body();
                    adapter.addItem(userHealthDto1.getDate(), userHealthDto1.getWeight(),
                            userHealthDto1.getBody_fat(),userHealthDto1.getBody_muscle(),
                            userHealthDto1.getMenu_planner(),userHealthDto1.getExercise_method());
                    adapter.notifyDataSetChanged();

                    et_weight.setText("");
                    et_bodyfat.setText("");
                    et_bodymuscle.setText("");
                    et_menuplan.setText("");
                    et_excersicemethod.setText("");

                    Toast.makeText(getApplicationContext(), "등록에 성공했습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("insertList", "Failed");
                    Toast.makeText(getApplicationContext(), "등록에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserHealthDto> call, Throwable t) {
                Log.d("insertList", "onFailure" + t.getMessage());
            }
        });
    }

    private void getMyList() {
        RetrofitClient retrofitClient = new RetrofitClient();
        Call<List<UserHealthDto>> call = retrofitClient.retrofitService.getMyList("Bearer " + JwtSingleton.getInstance().getToken());
        call.enqueue(new Callback<List<UserHealthDto>>() {
            @Override
            public void onResponse(Call<List<UserHealthDto>> call, retrofit2.Response<List<UserHealthDto>> response) {
                Log.i("getMyList", "Response : " + response.toString());
                if(response.isSuccessful()){
                    ArrayList<UserHealthDto> userList = (ArrayList<UserHealthDto>) response.body();
                    for (int i = 0; i < response.body().size(); i++) {
                        adapter.addItem(userList.get(i).getDate(), userList.get(i).getWeight(),
                                userList.get(i).getBody_fat(),userList.get(i).getBody_muscle(),
                                userList.get(i).getMenu_planner(),userList.get(i).getExercise_method());
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Log.i("getMyList", "Failed");
                }
            }
            @Override
            public void onFailure(Call<List<UserHealthDto>> call, Throwable t) {
                Log.d("getMyList", "onFailure" + t.getMessage());
            }
        });
    }

}