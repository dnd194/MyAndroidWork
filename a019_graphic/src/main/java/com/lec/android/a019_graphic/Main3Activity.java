package com.lec.android.a019_graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main3);
        LinearLayout ll =new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(new MyView3(this));  // linearLayout 에 들어감
        setContentView(ll);

    }//end onCreate
    class MyView3 extends View {

        Paint paint = new Paint();
        Path path = new Path();

        public MyView3(Context context) {
            super(context);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10f);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawPath(path,paint);
        }//end onDraw
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(x,y);           //path 를 그리지말고, 위치이동만 함
                    break;
                case MotionEvent.ACTION_MOVE:
                    path.lineTo(x,y);           // path 에 선 그리기
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }//end switch
            invalidate();
            return true;
        }//end onTouchEvent
    }//end MyView3


}//end Activity
