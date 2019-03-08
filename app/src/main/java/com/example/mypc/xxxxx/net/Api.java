package com.example.mypc.xxxxx.net;

import com.example.mypc.xxxxx.bean.Ask;
import com.example.mypc.xxxxx.bean.Take;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {
    @POST("openapi/api/v2")
    Call<Take> requset(@Body Ask ask);
}
