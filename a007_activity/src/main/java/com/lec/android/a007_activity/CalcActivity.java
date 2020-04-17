package com.lec.android.a007_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class CalcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);             //--> 화면이 없는 엑티비티 생성

        Intent intent = getIntent();
        int a = intent.getIntExtra("num1",0);
        int b = intent.getIntExtra("num2",0);

        intent.putExtra("plus",a + b);
        intent.putExtra("minus",a - b);

        //호출한 화면에 값 돌려주기
        setResult(RESULT_OK, intent);

        finish();  // 텅빈 레이아웃을 보여줄 필요가없으므로 바로 finish
        // finish() == onDestroy 랑 동일
    }
}
