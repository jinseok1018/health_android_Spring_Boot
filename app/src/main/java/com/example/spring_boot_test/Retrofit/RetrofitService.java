package com.example.spring_boot_test.Retrofit;

import com.example.spring_boot_test.data.JwtSingleton;
import com.example.spring_boot_test.data.LoginDto;
import com.example.spring_boot_test.data.RegisterDto;
import com.example.spring_boot_test.data.UserDto;
import com.example.spring_boot_test.data.UserHealthDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("user/{userid}")
    Call<UserDto> getProduct3(@Path("userid") String userid, @Header("Authorization") String token);

    @POST("authenticate")
    Call<JwtSingleton> login(@Body LoginDto loginDto);

    @POST("signup")
    Call<UserDto> register(@Body RegisterDto registerDto);

    @GET("user")
    Call<UserDto> getUserInfo(@Header("Authorization") String token);

    @GET("user-all")
    Call<List<UserDto>> getUserAll();

    @GET("user/health")
    Call<List<UserHealthDto>> getMyList(@Header("Authorization") String token);

    @GET("user/health/{userid}")
    Call<List<UserHealthDto>> getOtherUserHealthList(@Path("userid") String userid, @Header("Authorization") String token);

    @POST("user/health")
    Call<UserHealthDto> insertHealth(@Body UserHealthDto userHealthDto, @Header("Authorization") String token);

}
