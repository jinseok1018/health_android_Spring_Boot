package com.example.spring_boot_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.spring_boot_test.ListViewAdapter.MyListViewAdapter;
import com.example.spring_boot_test.Retrofit.RetrofitClient;
import com.example.spring_boot_test.SessionManager.SessionManager;
import com.example.spring_boot_test.data.JwtSingleton;
import com.example.spring_boot_test.data.UserHealthDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MyHealthListActivity extends AppCompatActivity {
    SessionManager sessionManager;
    private ListView listView;
    private MyListViewAdapter adapter;
    private TextView tv_id,tv_sex,tv_height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_healt_list);

        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_sex = (TextView)findViewById(R.id.tv_sex);
        tv_height = (TextView)findViewById(R.id.tv_height);

        sessionManager = new SessionManager(this);
        HashMap<String, Object> user = sessionManager.getUserDetail();
        tv_id.setText(user.get(sessionManager.USERID).toString());
        tv_sex.setText(user.get(sessionManager.SEX).toString() == "true"?"Men":"Women");
        tv_height.setText(user.get(sessionManager.HEIGHT).toString());

        listView = findViewById(R.id.listview);
        adapter = new MyListViewAdapter(MyHealthListActivity.this);
        listView.setAdapter(adapter);

        getMyList();
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