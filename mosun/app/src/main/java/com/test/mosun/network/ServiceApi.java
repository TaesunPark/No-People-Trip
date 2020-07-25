package com.test.mosun.network;

import com.test.mosun.data.LoginData;
import com.test.mosun.data.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

//    @POST("/user/join")
//    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/user/insert")
    Call<LoginResponse> userInsert(@Body LoginData data);
}