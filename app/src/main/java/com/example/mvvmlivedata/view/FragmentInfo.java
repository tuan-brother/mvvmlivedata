package com.example.mvvmlivedata.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.mvvmlivedata.R;

import java.util.ArrayList;

public class FragmentInfo extends Fragment {
    ViewFlipper viewFlipper;
    private ArrayList<String> mangDS=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_info,container,false);
        viewFlipper=view.findViewById(R.id.vl_infor);
        ActionViewFliper();
        return view;
    }
    public void ActionViewFliper()
    {
        mangDS.add("https://icdn.dantri.com.vn/thumb_w/640/2017/1-1510967806416.jpg");
        mangDS.add("https://i0.wp.com/kynguyenlamdep.com/wp-content/uploads/2020/01/hinh-anh-dep-hoa-bo-cong-anh.jpg");
        mangDS.add("https://pf.nl/wp-content/uploads/2019/10/Khanh-Phan-Viet-Nam-entry-Open-competition-Travel-2020-Sony-World-Photography-Awards.jpg");
        mangDS.add("https://znews-photo.zadn.vn/w1024/Uploaded/kbd_bcvi/2019_11_23/5d828d976f24eb1a752053b5.jpg");
        for (int i=0;i<mangDS.size();i++) {
        ImageView imageView = new ImageView(getContext());
        Glide.with(getContext()).load(mangDS.get(i)).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        viewFlipper.addView(imageView);
            viewFlipper.setFlipInterval(5000);
            viewFlipper.setAutoStart(true);
            Animation animationIn = AnimationUtils.loadAnimation(getContext(),
                    R.anim.vf_left);
            Animation animationOut = AnimationUtils.loadAnimation(getContext(),
                    R.anim.vf_out_left
            );
            viewFlipper.setInAnimation(animationIn);
            viewFlipper.setOutAnimation(animationOut);
    }
    }
}
