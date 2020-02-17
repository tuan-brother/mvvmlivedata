package com.example.mvvmlivedata.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mvvmlivedata.R;
import com.example.mvvmlivedata.view.FragmentInfo;
import com.example.mvvmlivedata.view.FragmentMedia;
import com.example.mvvmlivedata.view.FragmentNotifi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomnav=findViewById(R.id.nv_home);
        bottomnav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fg_container,new FragmentMedia()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectFr=null;
            switch (menuItem.getItemId()){
                case R.id.id_media:
                    selectFr=new FragmentMedia();
                    break;
                case R.id.id_info:
                    selectFr=new FragmentInfo();
                    break;
                case R.id.id_notifi:
                    selectFr=new FragmentNotifi();
                    break;
            }
            if (Checkinternet()==true)
            {
                Toast.makeText(MainActivity.this, ""+"Connect Success", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, ""+"Connect Fail", Toast.LENGTH_SHORT).show();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fg_container,selectFr).commit();
            return true;
        }
    };

    public Boolean Checkinternet() {
        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        if (activeNetwork != null) {
            return true;
        } else {
            return false;
        }
    }
}
