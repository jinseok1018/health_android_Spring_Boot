package com.example.spring_boot_test;

import com.example.spring_boot_test.data.Jwt;
import com.example.spring_boot_test.data.LoginDto;
import com.example.spring_boot_test.data.RegisterDto;
import com.example.spring_boot_test.data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("user/{userid}")
    Call<User> getProduct3(@Path("userid") String userid, @Header("Authorization") String token);

    @POST("authenticate")
    Call<Jwt> login(@Body LoginDto loginDto);

    @POST("signup")
    Call<User> register(@Body RegisterDto registerDto);

    @GET("user")
    Call<User> getUserInfo(@Header("Authorization") String token);

}
