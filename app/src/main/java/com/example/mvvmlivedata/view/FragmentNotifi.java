package com.example.mvvmlivedata.view;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mvvmlivedata.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FragmentNotifi extends Fragment {
    private Boolean check=false;
    FloatingActionButton fab,f1,f2,f3;
    EditText edtSave;
    Button btnSave;
    FirebaseDatabase database;
    DatabaseReference myRef;
    final ArrayList<String> arrayList=new ArrayList<>();
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_notifi,container,false);
        anhxa(view);
        fab.setOnClickListener(v -> {
            if (!check)
            {
                Hien();
                check=true;
            }
            else {
                An();
                check=false;
            }
        });
        f1.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        });
        f3.setOnClickListener(v -> {
            choosePicture();
        });
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference();
        btnSave.setOnClickListener(v -> {
            String title=edtSave.getText().toString();
            myRef.child("ghichu").push().setValue(title);
            edtSave.setText("");
        });

        //listview
        final ArrayAdapter adapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(view.getContext(),many_inforNotifi.class);
                startActivity(intent);
            }
        });
        //su kien thay doi su kien
        myRef.child("ghichu").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                arrayList.add(dataSnapshot.getValue().toString());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                arrayList.remove(dataSnapshot.getValue().toString());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }
    public void anhxa(View view)
    {
        fab=view.findViewById(R.id.fb_container);
        f1=view.findViewById(R.id.fb_camera);
        f2=view.findViewById(R.id.fb_google);
        f3=view.findViewById(R.id.fb_storage);
        edtSave=view.findViewById(R.id.edt_save);
        btnSave=view.findViewById(R.id.btn_save);
        listView=view.findViewById(R.id.lv_notifi);
    }
    public void Hien()
    {
        f1.show();
        f2.show();
        f3.show();
    }
    public void An(){
        f1.hide();
        f2.hide();
        f3.hide();
    }
    private void choosePicture() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto,200);//one can be replaced with any action code
    }
}
