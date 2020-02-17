package com.example.mvvmlivedata.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlivedata.Interface.OnclickListener;
import com.example.mvvmlivedata.R;
import com.example.mvvmlivedata.RecycleAdapter.AdapterRecycle;
import com.example.mvvmlivedata.model.ArticlesItem;
import com.example.mvvmlivedata.viewmodel.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentMedia extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AdapterRecycle adapter;
    ArrayList<ArticlesItem> listarticle = new ArrayList<>();
    ArticleViewModel viewModel;
    OnclickListener onclickListener;
    private ArrayList<ArticlesItem> articlelist;
    EditText edtSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media, container, false);
        onclickListener = position -> {
            if (articlelist != null && articlelist.size() > 0) {
                Intent intent = new Intent(getActivity(), ManyInfo.class);
                Bundle bundle = new Bundle();
                bundle.putString("urlImage", articlelist.get(position).getUrlToImage());
                bundle.putString("title", articlelist.get(position).getTitle());
                bundle.putString("author", articlelist.get(position).getAuthor());
                bundle.putString("publish", articlelist.get(position).getPublishedAt());
                bundle.putString("des", articlelist.get(position).getDescription());
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            } else {
                Intent intent = new Intent(getActivity(), ManyInfo.class);
                Bundle bundle = new Bundle();
                bundle.putString("urlImage", listarticle.get(position).getUrlToImage());
                bundle.putString("title", listarticle.get(position).getTitle());
                bundle.putString("author", listarticle.get(position).getAuthor());
                bundle.putString("publish", listarticle.get(position).getPublishedAt());
                bundle.putString("des", listarticle.get(position).getDescription());
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        };
        recyclerView = view.findViewById(R.id.rc_danhsach);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new AdapterRecycle(listarticle, getContext(), onclickListener);
        recyclerView.setAdapter(adapter);
        getData();
        edtSearch = view.findViewById(R.id.edt_search);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        return view;
    }

    public void getData() {
            viewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
            viewModel.getArticleResponseLiveData().observe(getViewLifecycleOwner(), data -> {
                if (data != null) {
                    List<ArticlesItem> item = data.getArticles();
                    listarticle.addAll(item);
                    adapter.notifyDataSetChanged();
                }
            });
    }

    private void filter(String text) {
        articlelist = new ArrayList<>();
        for (ArticlesItem item : listarticle) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                articlelist.add(item);
            }
        }
        adapter.fiterlist(articlelist);
    }

    public Boolean Checkinternet() {
        ConnectivityManager manager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        if (activeNetwork != null) {
            return true;
        } else {
            return false;
        }
    }
}
