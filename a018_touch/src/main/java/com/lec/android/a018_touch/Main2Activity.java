package com.lec.android.a018_touch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Main2Activity extends AppCompatActivity {
    ImageView iv;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        iv = findViewById(R.id.ivRobot);
        ll = findViewById(R.id.linearLayout1);

        ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:    //버튼을 누른 상태에서 이동했을 때
                    case MotionEvent.ACTION_UP: //버튼에서 손을 땟을 때
                        //이미지 뷰의 위치 옮기기
                        iv.setX(event.getX());
                        iv.setY(event.getY());
                        ;
                }
                return true;
            }
        });

    }//end onCreate
}//end Activity
