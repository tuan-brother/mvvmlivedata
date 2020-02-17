package com.example.mvvmlivedata.RecycleAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlivedata.Interface.OnclickListener;
import com.example.mvvmlivedata.R;
import com.example.mvvmlivedata.databinding.ItemListBinding;
import com.example.mvvmlivedata.model.ArticlesItem;

import java.util.ArrayList;

public class AdapterRecycle extends RecyclerView.Adapter<AdapterRecycle.ViewHolder> {
    private ArrayList<ArticlesItem> listItem;
    private Context context;
    private OnclickListener onclickListener;

    public AdapterRecycle(ArrayList<ArticlesItem> listItem, Context context,OnclickListener Onclick) {
        this.listItem = listItem;
        this.context = context;
        this.onclickListener=Onclick;
    }

    @NonNull
    @Override
    public AdapterRecycle.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        ItemListBinding biding= DataBindingUtil.inflate(inflater, R.layout.item_list,parent,false);
        return new ViewHolder(biding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycle.ViewHolder holder, int position) {
        ArticlesItem articlesItem=listItem.get(position);
        holder.bind(articlesItem);
        holder.itemView.setOnClickListener(v -> onclickListener.onClick(position));
    }

    @Override
    public int getItemCount() {
        return listItem==null?0:listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemListBinding itemListBinding;
        public ViewHolder(ItemListBinding binding) {
            super(binding.getRoot());
            this.itemListBinding=binding;
        }

        public void bind(ArticlesItem item)
        {
            this.itemListBinding.setArticle(item);
        }
    }

    public void fiterlist(ArrayList<ArticlesItem> fillist)
    {
        listItem=fillist;
        notifyDataSetChanged();
    }
}
