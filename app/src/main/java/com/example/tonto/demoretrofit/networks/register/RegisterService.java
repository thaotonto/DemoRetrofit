package com.example.tonto.demoretrofit.networks.register;

import com.example.tonto.demoretrofit.networks.register.RegisterRequest;
import com.example.tonto.demoretrofit.networks.register.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by tonto on 5/23/2017.
 */

public interface RegisterService {
    @POST("register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);
}
