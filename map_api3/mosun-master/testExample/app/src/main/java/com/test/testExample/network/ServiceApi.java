package com.test.testExample.network;

import com.test.testExample.data.JoinData;
import com.test.testExample.data.JoinResponse;
import com.test.testExample.data.LoginData;
import com.test.testExample.data.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);
}