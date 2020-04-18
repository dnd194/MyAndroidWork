package com.lec.android.a008_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    ProfileAdapter adapter;
    RecyclerView rv;
    static EditText et,et2,et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);


        rv = findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);

        adapter = new ProfileAdapter();
        rv.setAdapter(adapter);

        Button btnAppend = findViewById(R.id.btnAppend);
        btnAppend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendData(v);
            }
        });


    }
    protected void appendData(View v){
        Profile.name = et.getText().toString().trim();
        Profile.addr = et3.getText().toString().trim();
        Profile.age  = Integer.parseInt(et2.getText().toString().trim());
        adapter.addItem(0,new Profile(Profile.name, Profile.addr, Profile.age));
        adapter.notifyDataSetChanged();  // 추가했으니 리스트에  *** 반영을 해야함 ***
    }
}
