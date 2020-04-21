package com.lec.android.a014_dialog;

import androidx.appcompat.app.AlertDialog;  //최신버전에는 Theme가 없음
import androidx.appcompat.app.AppCompatActivity;

//import android.app.AlertDialog;   //옛날 버전
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    TextView tvResult;

    // 다이얼로그의 ID를 보기 좋은 상수로 선언해서 사용한다
    final int DIALOG_TEXT = 1;
    final int DIALOG_LIST = 2; // 리스트 형식의 다이얼로그 ID
    final int DIALOG_RADIO= 3; // 하나만 선택할 수 있는 다이얼로그 ID
    final int DIALOG_MULTICHOICE= 4;

    int choice=-1;  //라디오 버튼 선택 값

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // AlertDialog : Dialog 를 상속받은 자식클래스로
//          다이얼로그를 쉽게 만들수 있도록 지원해주고,
//          Activity 라이프 사이클과 다이얼로그의 라이프사이클을
//          맞춰줌

        tvResult = findViewById(R.id.tvResult);
        Button btnText = findViewById(R.id.btnText);
        Button btnList = findViewById(R.id.btnList);
        Button btnRadio = findViewById(R.id.btnRadio);
        Button btnMultiChoice = findViewById(R.id.btnMultiChoice);

        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert(DIALOG_TEXT);
            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert(DIALOG_LIST);
            }
        });
        btnRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert(DIALOG_RADIO);
            }
        });
        btnMultiChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert(DIALOG_MULTICHOICE);
            }
        });



    }//end onCreate

    protected AlertDialog.Builder showAlert(int id){
        switch (id){
            case DIALOG_TEXT:
                    AlertDialog.Builder builder1 =
                            new AlertDialog.Builder(this);
                    builder1.setTitle("다이얼로그 제목")
                            .setMessage("안녕하세요 ")
                            .setPositiveButton("긍정", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(),"긍정", Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("부정", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(),"부정", Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNeutralButton("중립", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(),"중립", Toast.LENGTH_LONG).show();
                                }
                            })
                            .show();
                    //builder1.show();
                return builder1;
            case DIALOG_LIST:
                AlertDialog.Builder builder2 =
                        new AlertDialog.Builder(this);
                final String[] str = {"사과","딸기","수박","배"};
                builder2.setTitle("리슽 형식 다이얼로그 제목")
                        .setNegativeButton("취소",null)
                        .setItems(str,   //리스트 목록에 사용할 배열

                                new DialogInterface.OnClickListener() {
                            //리스트 아이템이 선택되었을때 호출되는 콜백
                            //which --> 몇번 째가 선택되었는지에 대한 값
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"선택은 : "+str[which],Toast.LENGTH_SHORT).show();
                            }
                        })
                        ;
                builder2.show();
                return builder2;
            case DIALOG_RADIO:

                //커스텀 스타일 적용 ==>> styles.xml 편집
                AlertDialog.Builder builder3 =
                        new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.AlertDialogCustom));

                final String [] str2 = {"Red","Green","Blue"};
                builder3.setTitle("색을 고르세요")
                        .setPositiveButton("선택완료", new DialogInterface.OnClickListener() {


                            //which --> 몇번 째가 선택되었는지에 대한 값
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"선택은 : "+ str2[choice], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        //라디오버튼에서 선택했을 때 setSingleChoiceItems
                        .setSingleChoiceItems(str2,  //배열 목록
                                -1,     //처음에 누가 선택되어있나 -1: 아무것도 선택되어있지않음 default
                                new DialogInterface.OnClickListener() {
                                    //선택되었을 때 호출되는 콜백
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        choice=which;  //여기서 선택된 값이 위에 onClick 에 영향을 미침 str[choice]
                                    }
                                })
                        ;
                builder3.show();
                return builder3;
            case DIALOG_MULTICHOICE:
                AlertDialog.Builder builder4 =
                        new AlertDialog.Builder(this);

                final String[] data = {"korea","usa","uk","russsia"};
                final boolean [] checked= {true,false,true,false};

                builder4.setTitle("multichoice dialog")
                        .setPositiveButton("choice", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String str = "choiced value : ";

                                for(int i = 0 ; i<checked.length; i++){
                                    if (checked[i]){
                                        str = str+data[i]+", ";  // 문자열 연산
                                    }
                                }

                                tvResult.setText(str);
                            }
                        })
                        .setNegativeButton("cancel",null)
//                        //setMultiChoiceItems 매개변수 3개를 받음
                        .setMultiChoiceItems(data, //체크박스 list 항목
                                checked,   //초기 값 (선택여부) 배열
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        checked[which] = isChecked;

                                    }
                                }

                        )
                        ;

                builder4.show();
                return builder4;
        }
    return null;
    }

}//end activity












