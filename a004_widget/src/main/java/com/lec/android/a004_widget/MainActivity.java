package com.lec.android.a004_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etName, etPassword, etNumber, etEmail;
    TextView tvName, tvPassword, tvNumber, tvEmail, tvResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail= findViewById(R.id.etEmail);
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        etNumber = findViewById(R.id.etNumber);

        tvEmail = findViewById(R.id.tvEmail);
        tvName = findViewById(R.id.tvName);
        tvNumber = findViewById(R.id.tvNumber);
        tvPassword = findViewById(R.id.tvPassword);

        tvResult = findViewById(R.id.tvResult);

        //포커스 변화
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            //hasFocus : true -- 포커스 받은 경우 / false --포커스 잃은 경우
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){    //포커스가 잡히면 지정한 배경색으로 바뀜
                    ((EditText)v).setBackgroundColor(Color.YELLOW);
                }else{
                    //투명색 rgb + 투명도
                    ((EditText)v).setBackgroundColor(Color.parseColor("#00000000"));
                    //                                                             A,R,G,B  2바이트씩
                }
            }
        });

        //키보드가 눌릴때
        etPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
                //keycode : 눌린 키의 코드값 (키보드만 가능)
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                tvResult.setText(((EditText)v).getText().toString());
                //받은 비밀번호를 tvResult 쪽에서 보여줌  키보드만 됨  마우스로 클릭하면 안읽힘

                return false;
            }
        });

        //값의 변화 ( 입력 완료 )
        etEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                tvResult.setText("입력완료: "+ actionId);
                //하단에 run 부분에서 무슨 에러인지 알려줌 파란색부분으로
                // or  로그캣에서 에러부분 필터로 알 수 있음
                //actionid 의 코드 값이 5임
                return false;
            }
        });






    }// end onCreate
}//end activity
