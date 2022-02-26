package com.example.spring_boot_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.spring_boot_test.SessionManager.SessionManager;
import com.example.spring_boot_test.data.JwtSingleton;
import com.example.spring_boot_test.data.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    private Button btn_profile;
    private ListView listView;
    private ListViewAdapter adapter;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager = new SessionManager(this);
        sessionManager.editorClear();

        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.listview);
        adapter = new ListViewAdapter(ListActivity.this);
        listView.setAdapter(adapter);

        getList();

        getUserInfo(JwtSingleton.getInstance().getToken());

        btn_profile = findViewById(R.id.btn_profile);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getList() {
        RetrofitClient retrofitClient = new RetrofitClient();
        Call<List<User>> call = retrofitClient.retrofitService.getUserAll();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, retrofit2.Response<List<User>> response) {
                Log.i("getList", "Success : " + response.toString());
                if(response.isSuccessful()){
                    ArrayList<User> userList = (ArrayList<User>) response.body();
                    for (int i = 0; i < response.body().size(); i++) {
                        adapter.addItem(userList.get(i).getUserid(), userList.get(i).isSex(), userList.get(i).getHeight());
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Log.i("getList", "Failed");
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("getList", "onFailure" + t.getMessage());
            }
        });
    }

    public void getUserInfo(String jwt) {
        RetrofitClient retrofitClient = new RetrofitClient();
        Call<User> call = retrofitClient.retrofitService.getUserInfo("Bearer " + jwt);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("getUserInfo", "response : " + response.toString());
                if(response.isSuccessful()){
                    User user = response.body();
                    sessionManager.createSession(user.getUserid(), JwtSingleton.getInstance().getToken());
                } else {
                    Log.i("getUserInfo", "Failed");
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("getUserInfo", "onFailure" + t.getMessage());
            }
        });
    }
}
