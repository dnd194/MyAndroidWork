package com.lec.android.amu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.intro);

        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Intent intent = new Intent( getApplicationContext() , MainActivity.class);
                startActivity(intent);
                finish();
            }
        };// end Handler
        handler.sendEmptyMessageDelayed(1,3000);  // what에는 아무 숫자 해줘도됨
    }// end onCreate
}
