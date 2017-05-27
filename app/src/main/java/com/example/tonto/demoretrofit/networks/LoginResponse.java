package com.example.tonto.demoretrofit.networks;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tonto on 5/26/2017.
 */

public class LoginResponse {
    @SerializedName("access_token")
    private String accessToken;

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
