package com.example.spring_boot_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.spring_boot_test.ListViewAdapter.UserListViewAdapter;
import com.example.spring_boot_test.Retrofit.RetrofitClient;
import com.example.spring_boot_test.SessionManager.SessionManager;
import com.example.spring_boot_test.data.JwtSingleton;
import com.example.spring_boot_test.data.UserDto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    private Button btn_profile;
    private ListView listView;
    private UserListViewAdapter adapter;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager = new SessionManager(this);
        sessionManager.editorClear();

        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.listview);
        adapter = new UserListViewAdapter(ListActivity.this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListActivity.this, OtherHealthListActivity.class);
                intent.putExtra("userid", adapter.getItem(i).getUserid());
                intent.putExtra("usersex", adapter.getItem(i).isSex() == true? "Men":"Women");
                intent.putExtra("userheight", String.valueOf(adapter.getItem(i).getHeight()));
                startActivity(intent);
            }
        });
        getList();

        getUserInfo(JwtSingleton.getInstance().getToken());

        btn_profile = findViewById(R.id.btn_profile);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, MyHealthListActivity.class);
                startActivity(intent);
            }
        });

    }
    private void getList() {
        RetrofitClient retrofitClient = new RetrofitClient();
        Call<List<UserDto>> call = retrofitClient.retrofitService.getUserAll();
        call.enqueue(new Callback<List<UserDto>>() {
            @Override
            public void onResponse(Call<List<UserDto>> call, retrofit2.Response<List<UserDto>> response) {
                Log.i("getList", "Success : " + response.toString());
                if(response.isSuccessful()){
                    ArrayList<UserDto> userList = (ArrayList<UserDto>) response.body();
                    for (int i = 0; i < response.body().size(); i++) {
                        adapter.addItem(userList.get(i).getUserid(), userList.get(i).isSex(), userList.get(i).getHeight());
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Log.i("getList", "Failed");
                }
            }
            @Override
            public void onFailure(Call<List<UserDto>> call, Throwable t) {
                Log.d("getList", "onFailure" + t.getMessage());
            }
        });
    }

    public void getUserInfo(String jwt) {
        RetrofitClient retrofitClient = new RetrofitClient();
        Call<UserDto> call = retrofitClient.retrofitService.getUserInfo("Bearer " + jwt);
        call.enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                Log.i("getUserInfo", "response : " + response.toString());
                if(response.isSuccessful()){
                    UserDto user = response.body();
                    sessionManager.createSession(user.getUserid(), user.isSex(), user.getHeight());
                } else {
                    Log.i("getUserInfo", "Failed");
                }
            }
            @Override
            public void onFailure(Call<UserDto> call, Throwable t) {
                Log.d("getUserInfo", "onFailure" + t.getMessage());
            }
        });
    }
}
