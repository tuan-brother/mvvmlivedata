package com.example.mvvmlivedata.Interface;

import com.example.mvvmlivedata.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {
    @GET("v2/everything/")
    Call<Response> getMovieArticles(
            @Query("q") String query,
            @Query("apikey") String apiKey
    );
}
