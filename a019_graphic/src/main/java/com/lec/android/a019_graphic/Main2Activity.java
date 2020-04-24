package com.lec.android.a019_graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
        MyView2 v = new MyView2(Main2Activity.this);
        setContentView(v);
    }//end onCreate
    class MyView2 extends View {
        int x = 100, y = 100;

        public MyView2(Context context){
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            canvas.drawRect(x-100,y-100,x  +100,y+100,paint);
            paint.setColor(Color.CYAN);
            paint.setTextSize(80);
            canvas.drawText("글씨",550,550,paint);
        }//end onDraw

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                    x =(int)event.getX();
                    y=(int)event.getY();
                    invalidate();  //ondraw를 다시 호출함
            }
            return true;
        }
    }//end MyView2
}
