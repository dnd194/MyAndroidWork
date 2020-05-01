package com.lec.android.amu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//http://openapi.seoul.go.kr:8088/616a757750646e643736526f415644/json/CardSubwayStatsNew/1/5/20200329
// 인증키 6577656a42646e6437366b496a4343
// http://openapi.seoul.go.kr:8088/6577656a42646e6437366b496a4343/json/MonthlyAverageAirQuality/1/5/200406  json 형식
//MonthlyAverageAirQuality 서비스명
//http://data.seoul.go.kr/dataList/OA-2217/S/1/datasetView.do
public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    static AirAdapter adapter; //
    Button btnSearch;
    EditText etDate;
    static TextView test;
    static TextView test2;
    EditText etRegion;
    //    final String JsonUrl="http://openapi.seoul.go.kr:8088/616a757750646e643736526f415644/json/CardSubwayStatsNew/1/5/20200329";
    final String JsonUrl = "http://openapi.seoul.go.kr:8088/6577656a42646e6437366b496a4343/json/MonthlyAverageAirQuality/1/5/";
    static String a = "";
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSearch = findViewById(R.id.btnSearch);
        etDate = findViewById(R.id.etDate);
        etRegion = findViewById(R.id.etRegion);
        test = findViewById(R.id.test);
        test2 = findViewById(R.id.test2);
        rv = findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);  //그리드 레이아웃
        rv.setLayoutManager(layoutManager);

        adapter = new AirAdapter();
//        initAdapter(adapter);       //데이터 확인용
        rv.setAdapter(adapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String comUrl = JsonUrl + etDate.getText().toString().trim() + "/" + etRegion.getText().toString().trim();
                // 날짜와 지역구 모두 포함된 url
//                Log.d("myapp",""+comUrl);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(comUrl);
                    }
                }).start();
                //////////////////////
                try {
                    final String b = test.getText().toString().trim();
                    Log.d("myapp", "1 " + b);
                    parseJson(b);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
               test2.setText("");
            }
        });
    }//end OnCreate

    //protected void initAdapter(AirAdapter adapter){
//        for(int i =0; i<10; i++){
//            int idx = Sample.next();
//            adapter.addItem(new Air(Sample.FACEID[idx],Sample.DUST[idx],Sample.FINEDUST[idx]));
//        }
//    }//end initAdapter   샘플데이터 게시용
    public void request(String urlStr) {
        final StringBuilder sb = new StringBuilder();    //StringBuilder에 담을꺼임

        BufferedReader reader = null;
        HttpURLConnection conn = null;

        try {
            URL url = new URL(urlStr);

            conn = (HttpURLConnection) url.openConnection();
            if (conn != null) {

                conn.setConnectTimeout(5000); // connect 가 수립되는시간 5초가 지나가면 에러(socketTimeOutException)
                conn.setUseCaches(false);    // 캐시 사용 안함
                conn.setRequestMethod("GET");  //GET 방식  request

                conn.setDoInput(true);  // URLConnection 을 입력으로 사용 (true) , (false) -> 출력
                int Code = conn.getResponseCode();  // response 코드를 받아와서 성공하면 200

//                Log.d("myapp", "" + conn.getResponseCode());
                if (Code == HttpURLConnection.HTTP_OK) {  //HTTP_OK == 200
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    while (true) {
                        line = reader.readLine();
                        if (line == null) break;
//                        sb.append(line + "\n");
                        a = sb.append(line + "\n").toString();
//                        Log.d("myapp",""+a);   //찍히는 거 확인용
                        ///////////////////////////////////////
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                test.setText(a);
                            }
                        });// //end handler
                        ////////////////////////////////////////////
                    }  // 스타일리쉬 세팅맨  //end while
                    ///////////////////////////////////////////////////////////////////////
                }//end if
            }//end if
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
                if (conn != null) conn.disconnect();  //connection 은 disconnect
            } catch (IOException e) {
                e.printStackTrace();
            }
        }//end finally
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                test.setText(a);
//            }
//        });// //end handler

    }//end request

    public static void parseJson(String jsonText) throws JSONException {
//        Handler handler2 = new Handler(Looper.getMainLooper());
        Handler handler2 = new Handler();
        JSONObject jObj = new JSONObject(jsonText);    // 곧바로 String  --->>> JsonObject 형태로 파싱된다
        // response 받은게 object 이기 때문

        JSONArray row = jObj.getJSONObject("MonthlyAverageAirQuality").getJSONArray("row");  // json의 구성원?? 이름으로 뽑아냄
//        Log.d("myapp","행의 개수"+row);
        for (int i = 0; i < row.length(); i++) {
            JSONObject atmosphere = row.getJSONObject(i);          // row의 object 구성원을 station 이라는 변수에 담음
            final String MSRSTE_NM = atmosphere.getString("MSRSTE_NM");
            final int PM10 = atmosphere.getInt("PM10");
            final int PM25 = atmosphere.getInt("PM25");
//            test2.append(MSRSTE_NM +" "+ PM10 + " "+ PM25);
            //받아올 변수 추가 가능
            handler2.post(new Runnable() {
                @Override
                public void run() {

                    test2.append(MSRSTE_NM + " " + PM10 + " " + PM25);
                }
            });
            adapter.addItem(0, new Air(MSRSTE_NM, String.format("%d", PM10), String.format("%d", PM25)));
            adapter.notifyDataSetChanged();
        }
    }//end parseJson


}//end activity
