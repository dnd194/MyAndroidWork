package com.lec.android.a023_camera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
/*  오늘날 스마트폰의 카메라 용도
   단순 사진 촬영을 넘어, 바코드, 문자 인식, AR (증각현실)등 광범위하게 사용

   카메라로 사진찍는 방법 2가지
   방법 1. Intent 로 단말기의 앱 실행한후 촬영결과 받아와서 처리하기
   방법 2. 앱 화면에 직접 카메라 미리 보기 실행후 직접 사진 촬영하여 처리하기

   -------------------------------------------------------------------------
   방법 1. Intent 로 단말기의 앱 실행한후 촬영결과 받아와서 처리하기

   메니페스트 설정 :
       CAMERA, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE
       uses-feature 세팅
   저장 경로 설정하기 : external-path 리소스 사용
   ContentProvider 세팅 : FILE_PROVIDER_PATHS 설정
   권한 획득하기 (API23+)
   인텐트 생성 MediaStore.ACTION_IMAGE_CAPTURE
   인텐트에 저장 경로 세팅 : MediaStore.EXTRA_OUTPUT

   -------------------------------------------------------------------------

   참고]
      안드로이드 7.0 부터는 file:// 로 시작하는 Uri 정보를 다른 앱에서는 접근 불가
       반드시 content:// 로 시작하는 내용제공자 를 사용하도록 바뀌었슴!
*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
