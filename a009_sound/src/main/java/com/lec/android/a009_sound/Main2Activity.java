package com.lec.android.a009_sound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

public class Main2Activity extends AppCompatActivity {

    private ImageView btnPlay;
    private ImageView btnPause;
    private ImageView btnResume;
    private ImageView btnStop;
    SeekBar sb; // 음악 재생위치를 나타내는 시크바
    // 긴 음악 (ex 배경음악) 은 mediaPlayer를 사용한다
    MediaPlayer mp;  //미디어플레이어는 자원이므로 닫아줘야한다. onPause에서
                        //release() 사용

    int pos ;  //재생위치를 담을 변수 생성
    boolean isTracking = false;

   //재생위치에 따라 seekbar가 움직이도록 하는 Thread class 정의
    class MyThread extends Thread {
        @Override
        public void run() {

            while(mp.isPlaying()){     // 현재 재생 중이라면
                pos = mp.getCurrentPosition();  //현재 재생중인 위치 millsec (int)
                if(!isTracking){
                    sb.setProgress(pos);  //--> onProgressChanged 를 호출함
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //등장인물 매칭
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnResume = findViewById(R.id.btnResume);
        btnStop = findViewById(R.id.btnStop);
        sb = findViewById(R.id.sb);

        btnPlay.setVisibility(View.VISIBLE);
        btnPause.setVisibility(View.INVISIBLE);
        btnResume.setVisibility(View.INVISIBLE);
        btnStop.setVisibility(View.INVISIBLE);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            //sb값이 변경될때 마다 호출
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //음악이 끝까지 연주 완료됬다면 --> 맥스 값이 곡의 길이와 같다면 
                if(seekBar.getMax() == progress && !fromUser){
                    btnPlay.setVisibility(View.VISIBLE);
                    btnPause.setVisibility(View.INVISIBLE);
                    btnResume.setVisibility(View.INVISIBLE);
                    btnStop.setVisibility(View.INVISIBLE);
                    if(mp != null) mp.stop();
                }
            }

            //드래그 시작(트랙킹) 하면 호출
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isTracking = true;
            }

            //드래그 마치면 (트랙킹 종료) 하면 호출
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                pos = seekBar.getProgress(); //시크바의 위치를 pos 에 담음
                if(mp!=null) mp.seekTo(pos);  //null 이 아니면 이동

                isTracking = false;
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Media Player 객체 초기화, 재생
                mp = MediaPlayer.create(getApplicationContext(), R.raw.chacha);
                mp.setLooping(false); //반복의 여부 true 이면 무한반복

                //재생이 끝나면 호출되는 callback 메서드
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Log.d("myapp","연주종료"+ mp.getCurrentPosition() + "/"+ mp.getDuration());
                                                 //곡의 현재 위치 int mill단위임     //곡의 길이 int  mill단위임
                        btnPlay.setVisibility(View.VISIBLE);
                        btnPause.setVisibility(View.INVISIBLE);
                        btnResume.setVisibility(View.INVISIBLE);
                        btnStop.setVisibility(View.INVISIBLE);

                    }
                });
                mp.start(); ///노래재생 시작
                // 노래 시작시 음악의 재생시간을 알아내야 seekbar의 max값을 수정할 수 있음
                int duration = mp.getDuration();
                sb.setMax(duration);

                //그리고 seekbar의 쓰레드 시작
                new MyThread().start();

                btnPlay.setVisibility(View.INVISIBLE);   //버튼이 시작되면 안보이게되도록
                btnPause.setVisibility(View.VISIBLE);
//                btnResume.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.VISIBLE);

           }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //음악종료 시 0으로 됨
                pos = 0 ;
                if(mp!=null){
                    mp.stop(); //재생 멈춤
                    mp.seekTo(0);  //시크바 이동
                    mp.release(); //자원해제
                    mp = null;
               }//end if

                sb.setProgress(0); // 시크바도 초기위치로 재설정해줌
                btnPlay.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.INVISIBLE);
                btnResume.setVisibility(View.INVISIBLE);
                btnStop.setVisibility(View.INVISIBLE);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = mp.getCurrentPosition(); // 현재 재생중이던 위치 저장
                mp.pause();  // 미디어플레이어 일시정지
                btnPause.setVisibility(View.INVISIBLE);
                btnResume.setVisibility(View.VISIBLE);
            }
        });

        //멈춘 시점부터 재시작  ==> Thread 다시 시작해줘야함
        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.seekTo(pos);  //아까 저장해둔 재생위치 pos로 이동
                mp.start(); //재생시작
                new MyThread().start(); //시크바 스레드 다시 시작
                //멈췄던 위치부터 다시 시작 이미 mp.seekTo로 이동해있음
                btnPause.setVisibility(View.VISIBLE);
                btnResume.setVisibility(View.VISIBLE);

            }
        });







    }//end onCreate

    protected void onPause(){
        super.onPause();
        if(mp!=null){
            mp.release();   //mp 자원해제
        }//end if

        btnPlay.setVisibility(View.VISIBLE);
        btnPause.setVisibility(View.INVISIBLE);
        btnResume.setVisibility(View.INVISIBLE);
        btnStop.setVisibility(View.INVISIBLE);

    }//end onPause

}//end Activity









