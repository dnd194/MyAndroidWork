package com.lec.android.a018_touch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    TextView tv;
    //3개 까지의 멀티터치를 다루기 위한 배열
    int id[] = new int[3];
    int x[] = new int[3];
    int y[] = new int[3];
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tv = findViewById(R.id.tvResult);
    }//end onCreate

    //액티비티 (쌩 화면) 에서 발생한 터치 이벤트 처리
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //현재 터치가 발생한 포인트 개수를 얻어온다.
        int pointCount = event.getPointerCount();  //getPointerCount 는 int 형 반환
        if (pointCount > 3) pointCount = 3; //손가락을 4개이상 올려놔도 3개로 인식(처리)

        switch (event.getAction() & MotionEvent.ACTION_MASK) {    //비트연산자
            case MotionEvent.ACTION_DOWN: //한개 포인트에 대한 DOWN이 발생했을때
                id[0] = event.getPointerId(0);
                x[0] = (int) (event.getX());
                y[0] = (int) (event.getY());
                result = "싱글 터치 : \n";
                result += "(" + x[0] + ", " + y[0] + ")";
                break;

            case MotionEvent.ACTION_POINTER_DOWN: //2개 이상의 포인트에 대한 down이 발생했을때
                result = "멀티터치 : \n";
                for (int i = 0; i < pointCount; i++) {
                    id[i] = event.getPointerId(i);
                    x[i] = (int) (event.getX(i));
                    y[i] = (int) (event.getY(i));
                    result += "id["+id[i]+"] ("+x[i]+", "+y[i]+")\n";
                }
                break;
            case MotionEvent.ACTION_MOVE:
                result = "멀티터치 MOVE: \n";
                for (int i = 0; i < pointCount; i++) {
                    id[i] = event.getPointerId(i);
                    x[i] = (int) (event.getX(i));
                    y[i] = (int) (event.getY(i));
                    result += "id["+id[i]+"] ("+x[i]+", "+y[i]+")\n";
                }
                break;
            case MotionEvent.ACTION_UP:
                result="";

                break;
        }//end switch
        tv.setText(result);
        return super.onTouchEvent(event);
    }
}//end Activity

