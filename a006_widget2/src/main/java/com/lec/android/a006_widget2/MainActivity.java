package com.lec.android.a006_widget2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb1, pb2 , pb3;
    int value = 0;   //막대 프로그래스 바의 현재 진행 값
    int add =10;     //증가량
    int value2;
    int add2 = 1;

    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb1=findViewById(R.id.pb1);
        pb2=findViewById(R.id.pb2);
        pb3=findViewById(R.id.pb3);

        ToggleButton btn1 = findViewById(R.id.tb);
        Button btn2 = findViewById(R.id.btn2);

        btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                    pb1.setVisibility(View.INVISIBLE);
                }else{
                    pb1.setVisibility(View.VISIBLE);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + add;    //progress bar 는 100이 최대
                if(value > 100 || value < 0 ){
                    value = -add;
                }
                pb2.setProgress(value);  // 프로그레스바의 진행 값 설정

            }
        });
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){

                        value2 = value2 + add2;
                        if(value2 > 100 || value2 < 0){
                            add2 = - add2;
                        }

                        //별도의 작업 Thread에서
                        //메인 ui에 접근하려면 핸들러가 필요
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                pb3.setProgress(value2);
                            }
                        });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }
}
