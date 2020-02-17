package com.example.mvvmlivedata.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.mvvmlivedata.R;
import com.example.mvvmlivedata.databinding.ActivityManyInfoBinding;
import com.example.mvvmlivedata.model.ArticlesItem;

public class ManyInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_many_info);
        Bundle extra=getIntent().getBundleExtra("bundle");
        String urlImage=extra.getString("urlImage");
        String title=extra.getString("title");
        String author=extra.getString("author");
        String publish=extra.getString("publish");
        String des=extra.getString("des");
        ActivityManyInfoBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_many_info);
        ArticlesItem article=new ArticlesItem(title,author,urlImage,publish,des);
        binding.setArticle(article);
    }
}
