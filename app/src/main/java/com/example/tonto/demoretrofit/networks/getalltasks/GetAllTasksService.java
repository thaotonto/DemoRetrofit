package com.example.tonto.demoretrofit.networks.getalltasks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by tonto on 5/29/2017.
 */

public interface GetAllTasksService {
    @GET("task")
    Call<List<GetAllTasksResponse>> getAllTasks(@Header("Authorization") String token);
}
