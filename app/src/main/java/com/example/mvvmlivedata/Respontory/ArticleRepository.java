package com.example.mvvmlivedata.Respontory;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlivedata.Interface.ApiRequest;
import com.example.mvvmlivedata.Retrofit.RetrofitRequest;
import com.example.mvvmlivedata.model.Response;

import retrofit2.Call;
import retrofit2.Callback;


public class ArticleRepository {
    private static final String TAG = ArticleRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public ArticleRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<Response> getMovieArticles(String query, String key) {
        final MutableLiveData<Response> data = new MutableLiveData<>();
        apiRequest.getMovieArticles(query, key).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.body()!=null)
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
