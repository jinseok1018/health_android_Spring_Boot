package com.example.spring_boot_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.spring_boot_test.ListViewAdapter.MyListViewAdapter;
import com.example.spring_boot_test.Retrofit.RetrofitClient;
import com.example.spring_boot_test.data.JwtSingleton;
import com.example.spring_boot_test.data.UserHealthDto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class OtherHealthListActivity extends AppCompatActivity {
    private ListView listView;
    private MyListViewAdapter adapter;
    private TextView tv_id,tv_sex,tv_height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_health_list);

        Intent intent = getIntent();
        String userid = intent.getStringExtra("userid");
        String usersex = intent.getStringExtra("usersex");
        String userheight = intent.getStringExtra("userheight");
//        Log.i("@@@@", usersex);

        tv_id = findViewById(R.id.tv_id);
        tv_sex = findViewById(R.id.tv_sex);
        tv_height = findViewById(R.id.tv_height);

        tv_id.setText(userid);
        tv_sex.setText(usersex);
        tv_height.setText(userheight);

        listView = findViewById(R.id.listview);
        adapter = new MyListViewAdapter(OtherHealthListActivity.this);
        listView.setAdapter(adapter);

        getOtherUserHealthList(userid);
    }

    private void getOtherUserHealthList(String userid) {
        RetrofitClient retrofitClient = new RetrofitClient();
        Call<List<UserHealthDto>> call = retrofitClient.retrofitService.getOtherUserHealthList(userid,"Bearer " + JwtSingleton.getInstance().getToken());
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