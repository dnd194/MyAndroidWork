package com.lec.android.a006_widget2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    TextView tvResult;
    Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvResult = findViewById(R.id.tvResult);
        spinner1 = findViewById(R.id.spinner1);

        //아이템을 선택했을때 호출되는 콜백
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //선택했을 때
            //position : 몇번째 item 인지, 0 ~
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvResult.setText("position : "+ position + parent.getItemAtPosition(position));
                Toast.makeText(getApplicationContext(), (String)parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                                // 컨텍스트를 보여줄 객체가 누구인지,  내보낼 문자열은 무엇인지,   토스트의 길이는 무엇인지
            }

            @Override  // 아무것도 선택하지 않았을 때
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "선택 안했어요", Toast.LENGTH_SHORT).show();
            }
        });

    }//end onCreate


}


