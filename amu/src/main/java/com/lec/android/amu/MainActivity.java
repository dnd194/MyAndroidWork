package com.lec.android.amu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

// 인증키 6577656a42646e6437366b496a4343
// http://openapi.seoul.go.kr:8088/6577656a42646e6437366b496a4343/json/MonthlyAverageAirQuality/1/5/200406  json 형식
//MonthlyAverageAirQuality 서비스명
//http://data.seoul.go.kr/dataList/OA-2217/S/1/datasetView.do
public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    AirAdapter adapter; //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);  //그리드 레이아웃
        rv.setLayoutManager(layoutManager);

        adapter = new AirAdapter();
        initAdapter(adapter);
        rv.setAdapter(adapter);

    }//end OnCreate

    protected void initAdapter(AirAdapter adapter){
        for(int i =0; i<10; i++){
            int idx = Sample.next();
            adapter.addItem(new Air(Sample.FACEID[idx],Sample.DUST[idx],Sample.FINEDUST[idx]));
        }
    }
}
