package com.lec.android.a008_recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    PhonebookAdapter adapter;  // adapter 객체
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        // RecyclerView 를 사용하기 위해서는 LayoutManager를 지정해주어야 한다.

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);  //그리드 레이아웃
        rv.setLayoutManager(layoutManager);

        //Adapter 객체 생성
        adapter = new PhonebookAdapter();
        initAdapter(adapter);     // 리사이클러뷰 초기화

        rv.setAdapter(adapter);    // RecyclerView에 adapter 장착
        Button btnInsert = findViewById(R.id.btnInsert);
        Button btnAppend = findViewById(R.id.btnAppend);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData(v);
            }
        });

        btnAppend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendData(v);
            }
        });



    }//end onCreate
    //샘플 데이터 가져오기
    protected void initAdapter(PhonebookAdapter adapter){
        //몇개만 생성하기 ex 10개
        for(int i = 0 ; i < 10; i++){
            int idx = D.next();
            adapter.addItem(new Phonebook(D.FACEID[idx], D.NAME[idx], D.PHONE[idx], D.EMAIL[idx]));
        }
    }

    protected void insertData(View v ){
        int idx = D.next();
        // 리스트의 '''''맨 앞'''''에 추가함    인덱스 0 자리에 추가
        adapter.addItem(0, new Phonebook(D.FACEID[idx], D.NAME[idx], D.PHONE[idx], D.EMAIL[idx]));
        adapter.notifyDataSetChanged();  // 추가했으니 리스트에  *** 반영을 해야함 ***
    }

    //리스트 맨 뒤에 추가
    protected void appendData(View v){
        int idx = D.next();

        adapter.addItem( new Phonebook(D.FACEID[idx], D.NAME[idx], D.PHONE[idx], D.EMAIL[idx]));
        adapter.notifyDataSetChanged();  // 추가했으니 리스트에  *** 반영을 해야함 ***
    }

}//end class
