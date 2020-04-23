package com.lec.android.amu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// 인증키 6577656a42646e6437366b496a4343
// http://openapi.seoul.go.kr:8088/6577656a42646e6437366b496a4343/json/MonthlyAverageAirQuality/1/5/200406  json 형식
//MonthlyAverageAirQuality 서비스명
//http://data.seoul.go.kr/dataList/OA-2217/S/1/datasetView.do
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
