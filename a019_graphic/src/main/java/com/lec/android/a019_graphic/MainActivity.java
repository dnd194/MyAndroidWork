package com.lec.android.a019_graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);   //XML 레이아웃 사용하지 않기
        MyView v = new MyView(MainActivity.this);
        setContentView(v);
    }//end onCreate

    class MyView extends View {
        public MyView (Context context){
            super(context);
        }

        //화면이 업데이트 될때, '그려주기'
        @Override
        protected void onDraw(Canvas canvas) {
            Paint paint = new Paint();    //화면에 그려줄 도구 세팅
            paint.setColor(Color.YELLOW);  //색상 지정

            setBackgroundColor(Color.BLUE);

            canvas.drawRect(100,100,200,200,paint);
            canvas.drawCircle(300,300,40, new Paint(Color.BLACK));
            paint.setColor(Color.CYAN);
            paint.setStrokeWidth(10f);
            canvas.drawLine(400,100,900,800,paint);

            //Path  자취 만들기
            Path path = new Path();   //android.graphic.Path
            path.moveTo(20,100);  // 자취이동
            path.lineTo(100,200);  //직선
            path.cubicTo(150,30,200,300,300,200);  //곡선
            canvas.drawPath(path,paint);

       }
    }//end Myview


}//end Activity

