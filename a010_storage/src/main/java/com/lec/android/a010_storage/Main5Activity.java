package com.lec.android.a010_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;


//SharedPreference
//key - value 쌍으로 데이터 저장
// 작은 데이터들 (세팅 값) 저장용도로 활용

public class Main5Activity extends AppCompatActivity {
    EditText etInput;
    String sfName = "myFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        etInput = findViewById(R.id.etInput);

        //저장되어 있던 값을 꺼내서 보여주기
        SharedPreferences sf = getSharedPreferences(sfName, MODE_PRIVATE);
        String str =sf.getString("name",""); //키 값으로 꺼냄
        String xx = sf.getString("xx","ABC");
        String yy = sf.getString("yy","xcta");
        etInput.setText(str);
        Log.d("myapp",str+" - "+xx+" - "+yy);

    }//end oncreate

    @Override
    protected void onPause() {
        super.onPause();
        //activity 가 종료되기 전에 저장
        SharedPreferences sf = getSharedPreferences(sfName, MODE_PRIVATE);
        //저장하려면 Editor 객체 필요
        SharedPreferences.Editor editor = sf.edit();

        String str = etInput.getText().toString();  //사용자가 입력한 값 가져옴

        editor.putString("name", str);   //key - value 쌍으로 저장
        editor.putString("xx", "가나다");
        editor.commit();  //이래야 저장이 됨 파일의형태로로
    }
}//end activity
