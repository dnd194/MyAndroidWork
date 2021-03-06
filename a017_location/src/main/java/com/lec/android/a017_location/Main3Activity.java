package com.lec.android.a017_location;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    /**
     * 지오코딩(GeoCoding) : 주소,지명 => 위도(latitude),경도(longitude) 좌표로 변환하는 구글 서비스
     * 위치정보를 얻기위한 권한을 획득 필요, AndroidManifest.xml
     * ACCESS_FINE_LOCATION : 현재 나의 위치를 얻기 위해서 필요함
     * INTERNET : 구글서버에 접근하기위해서 필요함
     */
    TextView tvResult;
    EditText etLatitude, etLongitude;
    EditText etAddress;
    Button btnGeoCoder1, btnGeoCoder2;
    Button btnMap1, btnMap2;
    //지오코딩
    Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tvResult = findViewById(R.id.tvResult); // 결과창
        btnGeoCoder1 = findViewById(R.id.btnGeoCoder1);
        btnGeoCoder2 = findViewById(R.id.btnGeoCoder2);
        btnMap1 = findViewById(R.id.btnMap1);
        btnMap2 = findViewById(R.id.btnMap2);
        etLatitude = findViewById(R.id.etLatitude);
        etLongitude = findViewById(R.id.etLongitude);
        etAddress = findViewById(R.id.etAddress);
        geocoder = new Geocoder(this);


        btnGeoCoder1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //위도, 경도 입력후 주소정보 변환
                double lat = Double.parseDouble(etLatitude.getText().toString());
                double lng = Double.parseDouble(etLongitude.getText().toString());

                // 주소 객체를 담을 리스트 생성
                //  getFromLocation가  Address 타입의 list를 반환해서 담아주기 위함
                List<Address> list = null;
                try {
                    list = geocoder.getFromLocation(
                            lat,     //위도
                            lng,     //경도
                            10   //얻어올 결과 값의 최대 개수
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("myapp","입출력 오류 - 서버에서 주소 변환시 에러 발생");
                }
                if( list != null){
                    if(list.size() ==0 ){   // 리스트가 null 이 아닌데 사이즈가 0 인 경우는 진짜
                        //이상한 곳을 찍엇을 때
                        tvResult.setText("해당되는 주소 정보가 없습니다.");

                    }else {
                        StringBuffer result = new StringBuffer(list.size() + "개의 결과\n");
                        for(Address addr : list){
                            result.append("-----------------------------------------\n");
                            result.append(addr.toString()+"\n");
                        }

                        tvResult.setText(result);
                    }//end if
                }//end if
            }// end onClick
        }); //end setOnClickListener

        btnGeoCoder2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //지명 , 주소
                String str = etAddress.getText().toString();
                // /지명 , 주소  객체를 담을 리스트 생성
                //  getFromLocation가  Address 타입의 list를 반환해서 담아주기 위함
                List<Address> list = null;
                try {
                    list = geocoder.getFromLocationName(
                            str,     //위도
                            10   //얻어올 결과 값의 ''최대 개수''
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("myapp","입출력 오류 - 서버에서 주소 변환시 에러 발생");
                }
                if( list != null){
                    if(list.size() ==0 ){   // 리스트가 null 이 아닌데 사이즈가 0 인 경우는 진짜
                        //이상한 곳을 찍엇을 때
                        tvResult.setText("해당되는 주소 정보가 없습니다.");

                    }else {
                        StringBuffer result = new StringBuffer(list.size() + "개의 결과\n");
                        for(Address addr : list){
                            result.append("-----------------------------------------\n");
                            result.append(addr.getCountryName()+ ", "+ addr.getFeatureName()+
                                    ", "+addr.getLocality());
                        }
                        tvResult.setText(result);
                    }//end if
                }//end if
            }// end onClick
        }); //end setOnClickListener

        btnMap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double lat = Double.parseDouble(etLatitude.getText().toString());
                double lng = Double.parseDouble(etLongitude.getText().toString());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo: "+lat+","+lng));
                startActivity(intent);
            }
        });
        btnMap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = etAddress.getText().toString();

                List<Address> list = null;
                try {
                    list = geocoder.getFromLocationName(
                            str,     //위도
                            10   //얻어올 결과 값의 최대 개수
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("myapp","입출력 오류 - 서버에서 주소 변환시 에러 발생");
                }
                if( list != null){
                    if(list.size() ==0 ){   // 리스트가 null 이 아닌데 사이즈가 0 인 경우는 진짜
                        //이상한 곳을 찍엇을 때
                        tvResult.setText("해당되는 주소 정보가 없습니다.");
                    }else {
                        Address addr = list.get(0);
                        double lat = addr.getLatitude();
                        double lng = addr.getLongitude();
                        String uri = String.format("geo: %f,%f",lat,lng);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(intent);

                    }//end if
                }//end if
            }
        });
    }//end onCreate
}//end Activity
