package com.example.tonto.demoretrofit.networks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tonto on 5/23/2017.
 */

public class RegisterResponse {
//    private String message;
//    private int code;
//    private String token;
    @SerializedName("access_token")
    private String accessToken;

//    public RegisterResponse(String message, int code, String token) {
//        this.message = message;
//        this.code = code;
//        this.token = token;
//    }
//
//    public RegisterResponse(String message, int code, String token, String accessToken) {
//        this.message = message;
//        this.code = code;
//        this.token = token;
//        this.accessToken = accessToken;
//    }

    public RegisterResponse(String accessToken) {
        this.accessToken = accessToken;
    }

//    public String getMessage() {
//        return message;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public String getToken() {
//        return token;
//    }

    public String getAccessToken() {
        return accessToken;
    }
}
