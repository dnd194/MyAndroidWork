package com.lec.android.a016_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements SensorEventListener {
    private TextView tv;
    private SensorManager sm;

    Sensor accelerometer;
    Sensor magnetometer;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv = findViewById(R.id.textView1);

        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }//end onCreate

    @Override           //화면이 동작하기 전에 센서 자원 획득
    protected void onResume() {
        super.onResume();
        //센서 값이 변경되었을 때마다 콜백 받기 위한 리스너 등록 SensorEventListener 등록
        sm.registerListener(this,
                accelerometer,          //콜백을 원하는 센서   (가속도)
                SensorManager.SENSOR_DELAY_UI       //지연 시간 2MS  SENSOR_DELAY_UI 의 상수 값이 2
                                                );
        sm.registerListener(this,
                magnetometer,               //콜백을 원하는 센서   (자기센서)
                SensorManager.SENSOR_DELAY_UI       //지연 시간 2MS  SENSOR_DELAY_UI 의 상수 값이 2
                                                );
    }

    @Override           //화면이 빠져나기기 전에 센서 자원 반납
    protected void onPause() {
        super.onPause();
        //센서에 등록된 리스너 해제
        sm.unregisterListener(this);  // 자기자신 반납
    }

    float[] mGravity;
    float[] mGeomagnetic;

    //SensorEventListener 객체의 메소드들
    @Override   //센서 값이 변경될 때마다 호출되는 콜백
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mGravity =event.values;             // 센서 값들은 float[] 로 넘어옴
        if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic = event.values;
        if (mGravity != null && mGeomagnetic != null){
            float [] R = new float[9];
            float [] I = new float[9];
            boolean success =SensorManager.getRotationMatrix(R,I,mGravity,mGeomagnetic);
            if(success){
                float[] orientation = new float[3];
                SensorManager.getOrientation(R, orientation);

                float azimuth = orientation[0];     //z 축 회전방향
                float pitch = orientation[1];       //x 축 회전방향
                float roll = orientation[2];        //y 축 회전방향

                String str= String.format("%10s : %10s : %10s \n%10.2f : %10.2f : %10.2f\n",
                                         "방위각","피치","롤",azimuth, pitch,roll);
                try {
                    Thread.sleep(3000);
                tv.setText(str);
                Log.d("myapp",str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        tv.setText("onSensorChanged");
        Log.d("myapp","onSensorChanged");
    }

    //SensorEventListener  객체의 메소드들
    @Override       //센서의 정확도가 변경되었을 때 호출되는 콜백
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        tv.setText("onAccuracyChanged");
        Log.d("myapp","onAccuracyChanged");
    }//end onAccuracyChanged
}//end Activity
