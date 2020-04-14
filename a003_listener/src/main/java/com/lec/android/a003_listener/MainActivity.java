package com.lec.android.a003_listener;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvResult ;
    EditText et;

    //방법 5 : 액티비티가 implement 한 것을 사용
    @Override
    public void onClick(View v) {
        Log.d("myapp","Clear 버튼이 클릭되었습니다");
        tvResult.setText("CLEAR 버튼이 클릭");
        et.setText("");
    }

    //onCreate
    //엑티비티 (화면 객체) 가 생성될때 호출되는 메소드
    //엑티비티를 초기화하는 코드작성
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);

        tvResult = findViewById(R.id.tvResult);
        et = findViewById(R.id.et);
        LinearLayout ll = findViewById(R.id.ll);    // effective final or final 로 하는 경우가있음


        // 방법 2 : 익명클래스 (anonymous class) 사용
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {         // ''''클릭되었을때'''' 호출되는 메서드 ==> callback 메서드
                Log.d("myapp", "버튼2가 클릭되었습니다");
                tvResult.setText("버튼 2가 클릭");
                tvResult.setBackgroundColor(Color.BLUE);


            }
        });
        //다양한 이벤트, 다양한 리스너 등록 가능

        // long 클릭 ==> 길게 누르는거
//        클릭은 눌렀다 뗏다로 완성됨
        btn2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("myapp","버튼2가 롱클릭 되었습니당");
                tvResult.setText("버튼2가 롱클릭되었습니다.");
                tvResult.setBackgroundColor(Color.CYAN);
                return false;    //false 리턴하면 이벤트가 click까지간다.
//                return true;       //true 리턴하면 이벤트가 long click 하나로 소멸 (CONSUME) 된다
            }
        });

        //방법 3 : 람다 표현
        btn3.setOnClickListener((v)->{    //람다는 자바 8부터 도입되었기 때문에 project structure 세팅이 필요하다
            //onClick(View v)
            Log.d("myapp","버틈3 가 클릭되었다.");
            tvResult.setText("버튼 3 클릭됨");
            ll.setBackgroundColor(Color.rgb(90,112,48));



        }) ;
        // 안드로이드는 자바7을 사용하기 때문


        //방법 4 : implement 한 클래스 사용
        //동일한 동작을 하는 객체를 여러개 만들때
        Button btnA = findViewById(R.id.btnA);
        Button btnB = findViewById(R.id.btnB);
        Button btnC = findViewById(R.id.btnC);
        Button btnD = findViewById(R.id.btnD);
        Button btnE = findViewById(R.id.btnE);
        Button btnF = findViewById(R.id.btnF);

        class myListener implements View.OnClickListener{

            String name;  //매개변수를 받아줌

            public myListener(String name) {this.name=name;}

            @Override
            public void onClick(View v) {
                String tag = (String)v.getTag();
                String text = (String)((Button)v).getText();    //getText() CharSequence 객체 리턴
                String msg = String.format("%s 버튼 %s 이 클릭[%s] ",name,text,tag );
                Log.d("myapp", msg);
                tvResult.setText(msg);
                et.setText(et.getText().append(name));  //기존의 텍스트에 추가해서 set


            }
        }

        btnA.setOnClickListener(new myListener("안녕1"));
        btnB.setOnClickListener(new myListener("안녕2"));
        btnC.setOnClickListener(new myListener("안녕3"));
        btnD.setOnClickListener(new myListener("안녕4"));
        btnE.setOnClickListener(new myListener("안녕5"));
        btnF.setOnClickListener(new myListener("안녕6"));

        //방법 5 : 액티비티가 implement
        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        //연습
        // +   -  버튼 누르면 tvResult 의 글씨가 커지거나 작아지게 하기
        //getTextSize() : float

        Button btnInc = findViewById(R.id.btnInc);
        Button btnDec = findViewById(R.id.btnDec);
        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float f = tvResult.getTextSize();
                Log.d("myapp","현재글꼴사이즈 : "+f);
                tvResult.setTextSize(0,f + 10);
            }
        });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float f =tvResult.getTextSize();
                tvResult.setTextSize(0,f - 10);     //setTextSize는 2가지로 오버로딩 되어있음
            }
        });


    }//end oncreate

    // 방법 1 : 레이아웃 xml 의 onxxxxx에 지정


    public void changeText(View v){
        //Log.d(tag, msg)
        //로그창의 디버그 메세지로 출력
        Log.d("myapp", "버튼1이 클릭되었습니다" );
        tvResult.setText("버튼 1이 클릭 되었습니다.");
    }
}
