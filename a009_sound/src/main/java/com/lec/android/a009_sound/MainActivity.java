package com.lec.android.a009_sound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.net.rtp.AudioStream;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * 음향: SoundPool
 * 짧은 음향 리소스(들)을 SoundPool 에 등록(load)하고, 원할때마다 재생(play)
 * <p>
 * res/raw 폴더 만들고  음향 리소스 추가하기
 */

// 바이너리(binary) 파일은 raw 폴더에 추가 시킨다
//우 클릭 android resource directory
public class MainActivity extends AppCompatActivity {

    //Soundpool 이란 android MEDIA
    private SoundPool sp;

    //음악을 담을 배열 생성
    private final int[] SOUND_RES = {R.raw.gun, R.raw.gun3, R.raw.gun7};

    //음향 id 값
    int[] soundId = new int [SOUND_RES.length];  //위에 배열의 길이만큼 배열 생성


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPlay1 = findViewById(R.id.btnPlay);
        Button btnPlay2 = findViewById(R.id.btnPlay2);
        Button btnPlay3 = findViewById(R.id.btnPlay3);
        Button btnStop = findViewById(R.id.btnStop);

        //Soundpool 객체생성

        //롤리팝 이후로 deprecate 됨 그래서 버전 체크 해줘야함
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //API21 이상에서는 아래와 같이 Soundpool 생성
            new SoundPool.Builder().setMaxStreams(10).build();
            //SoundPool.Builder()이거는 soundpool 을 생성한게 아니라 Builder를 생성한거임
            // builder를 먼저 생성 이후 build()를 사용하여 soundpool 생성

        } else {
            sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            //1번째 매개변수, 재생음향 최대 개수, 재생미디어 타입,  3번째매개변수는 안쓰임 SOUNDPOOL 다큐먼트한번 보면됨
        }//end else

        // Soundpool 에 음향 리소스들을 load
        for(int i = 0 ; i<SOUND_RES.length; i++){
            soundId[i] = sp.load(this, SOUND_RES[i], 1);
            //1. 화면의 제어권자,  2. 음원파일 리소스     3. 재생우선순위
            //  load 는 sound 를 재생할 id 값을 return 그래서 위에서 id 배열 을 생성해줌

        }//end for

        btnPlay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //play의 매개변수 6개
                sp.play(soundId[0],         //준비한 sound 리소스 id
                        1,        //왼쪽볼륨 float
                        1,      //오른쪽 볼륨 float
                        0,          //우선순위 int
                        0,              //반복 횟수  0: 반복 안함,  -1 : 무한반복
                        1f  );            //재생속도 float 0.5(절반) ~ 2.0(2배속)

            }
        });  //end btn1

        btnPlay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //play의 매개변수 6개
                sp.play(soundId[1],         //준비한 sound 리소스 id
                        1,        //왼쪽볼륨 float   0.0 ~ 1.0
                        1,      //오른쪽 볼륨 float
                        0,          //우선순위 int
                        2,              //반복 횟수  0: 반복 안함,  -1 : 무한반복
                        2f  );            //재생속도 float 0.5(절반) ~ 2.0(2배속)
            }
        });//end btn2

        btnPlay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //play의 매개변수 6개
                sp.play(soundId[2],         //준비한 sound 리소스 id
                        1,        //왼쪽볼륨 float
                        1,      //오른쪽 볼륨 float
                        0,          //우선순위 int
                        -1,              //반복 횟수  0: 반복 안함,  -1 : 무한반복
                        0.5f  );            //재생속도 float 0.5(절반) ~ 2.0(2배속)

            }
        });//end btn3

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0 ; i<soundId.length; i++){
                    sp.stop(soundId[i]);
                }//end for
            }
        }); //end btnstop

    }//end onCreate

}//end class









