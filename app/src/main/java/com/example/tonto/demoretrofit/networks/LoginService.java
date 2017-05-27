package com.example.tonto.demoretrofit.networks;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by tonto on 5/26/2017.
 */

public interface LoginService {
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
