package com.gmail.dnd194.kelvin.blockgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Start extends AppCompatActivity implements View.OnClickListener {
    TextView tvTime;    //시간
    TextView tvPoint;   //점수

    int time = 30;      //시간 값
    int point = 0;      //점수 값


    //블록이미지 리소스 배열
    int[] img = {R.drawable.block_red,R.drawable.block_blue,R.drawable.block_green};


    //떨어지는 블록의  IMAGEView 배열 객체
    ImageView[] iv = new ImageView[8];  //초기화 null 8개모두

    private Vibrator vibrator;      //진동
    private SoundPool sp;           //음악
    private MediaPlayer mp;         //배경음악

    int soundID_OK;                 //음향 id : 블럭이 서로 일치했을때
    int soundID_Error;              //음향 id : 블럭이 서로 불일치할때

    //다이얼로그 ID
    final int DIALOG_TIMEOVER = 1;   //다이얼로그 아이디

    //시간을 관리할 HANDLER
    Handler handler = new Handler();

    //게임진행 쓰레드
    class GameThread extends Thread{   //쓰레드 클래스 만들고 항상 run
        @Override
        public void run() {
            //시간을 1초마다 다시 표시 (업데이트)
            //Handler 사용하여 화면 UI 업데이트

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(time >= 0){
                        tvTime.setText("시간 : "+time);
                        if(time > 0){
                            time--;         //1초 감소, 1초후에 다시 Run 수행
                            handler.postDelayed(this,1000);
                        }else {
                            //time == 0 이 된 경우
                            AlertDialog.Builder builder = new AlertDialog.Builder(Start.this);
                            builder.setTitle("timeout")
                                    .setMessage("score : "+point)
                                    .setNegativeButton("stop", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();   //현재 화면 종료
                                        }
                                    })
                                    .setPositiveButton("again", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //게임 리셋하고 새 게임 시작
                                            time = 30;
                                            point=0;
                                            tvTime.setText("시간 : "+time);
                                            tvPoint.setText("점수 : "+point);
                                            new GameThread().start();  //새 게임 시작
                                        }
                                    })
                                    .setCancelable(false)
                                    ;
                            builder.show();
                        }//end if
                    }//end if



                }//end run
            },1000); //1초후에 시간 표시

        }//END RUN()
    }//end GameThread class


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //상태바 (statusView) 제거하기
        //반드시 setContentView 앞에서 해줘야함
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.start);

        //레이아웃 객체 (뷰) 초기화
        tvTime = findViewById(R.id.tvTime);
        tvPoint = findViewById(R.id.tvPoint);

        ImageView ivRed = findViewById(R.id.ivRed);
        ImageView ivBlue = findViewById(R.id.ivBlue);
        ImageView ivGreen = findViewById(R.id.ivGreen);

        iv[0] = findViewById(R.id.ivBlock1);
        iv[1] = findViewById(R.id.ivBlock2);
        iv[2] = findViewById(R.id.ivBlock3);
        iv[3] = findViewById(R.id.ivBlock4);
        iv[4] = findViewById(R.id.ivBlock5);
        iv[5] = findViewById(R.id.ivBlock6);
        iv[6] = findViewById(R.id.ivBlock7);
        iv[7] = findViewById(R.id.ivBlock8);
        // 매칭 완료

        //      게임이 시작되면, 초기화  랜덤으로 블럭의 색상 지정
        for(int i =0; i<iv.length; i++){        //iv 는 imageview[]
            //0,1,2 <- red, green, blue
            int num = new Random().nextInt(3);      // 0 ,1 ,2 중에 랜덤 정수
            iv[i].setImageResource(img[num]);
            iv[i].setTag(num +"");      //   태그를 버튼의 색상 판단을 위한 값으로 활용

        }

        //점수 초기화
        point = 0;
        tvPoint.setText("점수 : "+point);

        ivRed.setOnClickListener(this);
        ivBlue.setOnClickListener(this);
        ivGreen.setOnClickListener(this);

        //시간표시, 게임 진행 쓰레드 시작하기
        new GameThread().start();


    }//end onCreate

    @Override
    public void onClick(View v) {
        //버튼을 눌렀을 때 호출되는 콜백
        //블록과 같은 색의 버튼이 눌렸는지 판별 해야함, 같은블럭이면 이미지블럭이 한칸씩 내려오게함
        // + 맨위에는 새로운 랜덤블록이 생겨야함
        boolean isOk = false;  //맞추었는지 판별 결과
        ImageView imageView = (ImageView)v;  //이미지뷰로 캐스팅해줘야함
        switch (imageView.getId()){
            //맨 아래 블럭 ImageView의 색상과 일치하는 버튼인지 판정  == 7
            case R.id.ivRed:  //빨간 버튼 클릭시
            //빨강의 태그 값은 0임
                if("0".equals(iv[7].getTag().toString())) isOk = true;

                break;
                //초록 태그 값은 2
            case R.id.ivGreen:  //초록 버튼 클릭시
                if("2".equals(iv[7].getTag().toString())) isOk = true;

                break;
                //파란 태그는 1
            case R.id.ivBlue:   //파란 버튼 클릭시
                if("1".equals(iv[7].getTag().toString())) isOk = true;

                break;
        }//end switch
        if(isOk){   //버튼색을 맞추었다면
            //위의 7개 블럭을 한칸 아래로 이동
            for(int i = iv.length-2; i>=0; i--){
                // i 번째를 i+1 번째로 복사  ==  iv[i] -> iv[i+1] 로 하는 작업임

                int num = Integer.parseInt(iv[i].getTag().toString());   // 태그는 0, 1, 2
                iv[i+1].setImageResource(img[num]);     //i 아래쪽 블럭 이미지 업데이트
                iv[i+1].setTag(num+"");                 // i 아래쪽 태그 값 업데이트
            }//end for

            //가장 위의 블록 ImageView 는 랜덤으로 생성하여 넣어줌
            int num = new Random().nextInt(3);   // 0 , 1 , 2 중에 나옴
            iv[0].setImageResource(img[num]);
            iv[0].setTag(num +"");

            //진동과 음향 삽입 (맞췄을때)
            vibrator.vibrate(200);   //0.2 초 진동
            sp.play(soundID_OK,1,1,0,0,1);

            //점수 올리기
            point++;
            tvPoint.setText("점수 : "+point);

        }else{   //맞추지 못했다면
            vibrator.vibrate(new long[]{20,80,20,80,20,80},-1);   //0.2 초 진동
            sp.play(soundID_Error,1,1,0,0,1);

            //점수 내리기
            point--;
            tvPoint.setText("점수 : "+point);
        }//end if



    }//end onClick

    @Override
    protected void onResume() {              //앱이 가동상태에 들어갔을때
        super.onResume();
        //자원획득
        // Vibrator 객체 얻어오기
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //Soundpool 객체 얻어오기
        sp = new SoundPool.Builder().setMaxStreams(5).build();
        soundID_OK = sp.load(Start.this, R.raw.gun3,1);
        soundID_Error= sp.load(Start.this, R.raw.error, 1);

        //미디어객체 생성
        mp = MediaPlayer.create(getApplicationContext(),R.raw.bgm1);
        mp.setLooping(false);       //반복재생 안함
        mp.start();  //배경음악 재생시작

    }//end onResume

    @Override
    protected void onPause() {               //앱이 작동을 멈추었을때
        super.onPause();
        //자원해제
        if(mp!=null) {
            mp.stop();
            mp.release();
        }
    }//end onPause

}//end Activity
