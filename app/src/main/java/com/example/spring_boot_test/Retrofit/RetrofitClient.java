package com.example.spring_boot_test.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    Retrofit retrofit = new Retrofit.Builder()
            //http://localhost:8080/api/user/jinseok
            .baseUrl("http://10.0.2.2:8080/api/")
            //String으로 response가 올때
//            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public RetrofitService retrofitService = retrofit.create(RetrofitService.class);
}
