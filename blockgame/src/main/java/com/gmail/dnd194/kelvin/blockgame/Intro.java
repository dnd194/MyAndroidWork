package com.gmail.dnd194.kelvin.blockgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

public class Intro extends AppCompatActivity {

    //액션바 없애기 --> styles.xml에서 noActionBAR 상속
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.intro);

        //초기화면
        //3초동안 보이고, 다음화면(main)으로 넘어가기기
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent); //화면전환
                finish();  //intro 화면 종료
            }
        }; //end handler
        handler.sendEmptyMessageDelayed(1,3000);


    }//end onCreate
}//end Activity