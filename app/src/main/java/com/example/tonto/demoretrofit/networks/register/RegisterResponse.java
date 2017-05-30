package com.example.tonto.demoretrofit.networks.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tonto on 5/23/2017.
 */

public class RegisterResponse {

    @SerializedName("access_token")
    private String accessToken;

    public RegisterResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
