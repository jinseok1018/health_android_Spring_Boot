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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

public class ListActivity extends AppCompatActivity {
    private ListView listView;
    private ListViewAdapter adapter;
    private static final String TAG = ListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listview);
        adapter = new ListViewAdapter(ListActivity.this);
        listView.setAdapter(adapter);
        getList();

    }
    private void getList() {
        RetrofitClient retrofitClient = new RetrofitClient();
        Call<List<User>> call = retrofitClient.retrofitService.getUserAll();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, retrofit2.Response<List<User>> response) {
                Log.i(TAG, "Success : " + response.toString());
                if(response.isSuccessful()){
                    ArrayList<User> userList = (ArrayList<User>) response.body();
                    for (int i = 0; i < response.body().size(); i++) {
                        adapter.addItem(userList.get(i).getUserid(), userList.get(i).isSex(), userList.get(i).getHeight());
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Log.i(TAG, "Failed");
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("Tag", "Failed" + t.getMessage());
            }
        });
    }
}
