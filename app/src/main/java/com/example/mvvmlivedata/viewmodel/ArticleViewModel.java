package com.example.mvvmlivedata.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmlivedata.Respontory.ArticleRepository;
import com.example.mvvmlivedata.model.Response;

public class ArticleViewModel extends ViewModel {
    private ArticleRepository articleRepository;
    private LiveData<Response> articleResponseLiveData;

    public LiveData<Response> getArticleResponseLiveData() {
        articleRepository = new ArticleRepository();
        this.articleResponseLiveData = articleRepository.getMovieArticles("movies", "84a7decf3110498ea372bd16dd0601e8");
        return articleResponseLiveData;
    }
}
