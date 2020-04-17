package com.lec.android.a008_recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

        rv.setLayoutManager(layoutManager);

        //Adapter 객체 생성
        adapter = new PhonebookAdapter();
        initAdapter(adapter);     // 리사이클러뷰 초기화

        rv.setAdapter(adapter);    // RecyclerView에 adapter 장착

    }//end onCreate
    //샘플 데이터 가져오기
    protected void initAdapter(PhonebookAdapter adapter){
        //몇개만 생성하기 ex 10개
        for(int i = 0 ; i < 10; i++){
            int idx = D.next();
            adapter.addItem(new Phonebook(D.FACEID[idx], D.NAME[idx], D.PHONE[idx], D.EMAIL[idx]));
        }
    }


}//end class
