package com.lec.android.a023_camera;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

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

    String[] permissions = {Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    final int REQUEST_CODE =101;

    ImageView iv;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //권한 획득
        if(Build.VERSION.SDK_INT > 23){
            if(checkSelfPermission(String.valueOf(permissions)) == PackageManager.PERMISSION_DENIED){
                requestPermissions(permissions, REQUEST_CODE); //권한 요청하기
            }
        }
        iv = findViewById(R.id.iv);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //촬영하는 메서드
                takePicture();
            }
        });
    }//end onCreate

    public void takePicture(){
        if(file == null ){
            file = createFile();
        }//end if
        //위 FILE 객체로부터 Uri 객체 만들기
        Uri fileUri = FileProvider.getUriForFile(this,
                                        "org.techtown.capture.intent.fileprovider",
                                            file     );
        //사진 촬영 앱가동 (묵시적 인텐트) implicit
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);   //사진 찍기용 묵시적 인렌트
        intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
        //위 엑티비티가 가용한지 여부를 체크 한뒤 ( !=null )
        if(intent.resolveActivity(getPackageManager()) != null ){
            startActivityForResult(intent,101);
        }

    }//end takePicture

    private File createFile(){
        String fileName = "capture.jpg";
        File storageDir = Environment.getExternalStorageDirectory();  //부모
        File outFile = new File (storageDir, fileName);
        return outFile;
    }// END createFile

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //사진 촬영 앱의 사진 결과를 받아오게 되면 수행
        if(requestCode == 101 && resultCode == RESULT_OK){
            //저장된 사진 이미지파일을 --> BitMap 객체로 만들고 imageview 로 띄움
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;   // 좀 작은 사이즈로 resample 을 위한 samplesize
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(),options);
            iv.setImageBitmap(bitmap);
        }
    }//end onActivityResult

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults.length <= 0){
                    Toast.makeText(this,"권한획득실패",Toast.LENGTH_SHORT).show();
                    //onDestroy or finish()
                    return;
                }
                String result = "";
                for(int i =0; i<grantResults.length; i++){
                    if(grantResults[i]== PackageManager.PERMISSION_GRANTED){
                        result +="권한 획득 성공 : "+permissions[i]+"\n";
                    }else {
                        result +="권한 획득 실패 : "+permissions[i]+"\n";
                    }//end if
                }//end for
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                Log.d("myapp", result);
                break;
        }//end switch
    }// end onRequestPermissionsResult

}//end Activity
