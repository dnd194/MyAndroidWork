package com.lec.android.a015_web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/*
        *  xml , json 파싱 연습
        *
        *  ■서울시 지하철 역사 정보
        http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-12753&srvType=A&serviceKind=1&currentPageNo=1

        샘플url

        XML 버젼
        http://swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/xml/stationInfo/1/5/서울

        JSON 버젼
        http://swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/json/stationInfo/1/5/서울

        */
public class Main3Activity extends AppCompatActivity {
    EditText editText;
    Button btnXML, btnJson, btnParse;
    static TextView tvResult;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        editText = findViewById(R.id.editText);
        btnJson = findViewById(R.id.btnJSON);
        btnParse = findViewById(R.id.btnParse);
        btnXML = findViewById(R.id.btnXML);
        tvResult = findViewById(R.id.tvResult);
        final String Jsonurl = "http:swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/json/stationInfo/1/5/";
        final String Xmlurl = "http:swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/xml/stationInfo/1/5/";
        btnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String task = Jsonurl + editText.getText().toString().trim();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(task);
                    }
                }).start();
            }
        });
        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String compare = tvResult.getText().toString();

                try {
                    if(compare.contains("<")){
                        parseXML(compare);
                    }else {
                    parseJson(compare);
                    }
                    tvResult.setText("");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        btnXML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String task = Xmlurl + editText.getText().toString().trim();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(task);
                    }
                }).start();
            }
        });
    }//end onCreate

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

                int reponseCode = conn.getResponseCode();  // response 코드를 받아와서 성공하면 200
                Log.d("myapp", "" + reponseCode);
                if (reponseCode == HttpURLConnection.HTTP_OK) {  //HTTP_OK == 200
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    while (true) {
                        line = reader.readLine();
                        if (line == null) break;
                        sb.append(line + "\n");
                    }  // 스타일리쉬 세팅맨
                }
            }
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
        handler.post(new Runnable() {
            @Override
            public void run() {
                tvResult.setText(sb.toString());
            }
        });
    }//end request

    public static void parseJson(String jsonText) throws JSONException {
        Handler handler2 = new Handler();
        JSONObject jObj = new JSONObject(jsonText); // 곧바로 String --->>> JsonObject 형태로 파싱된다
        // response 받은게 object 이기 때문

        JSONArray row = jObj.getJSONArray("stationList"); // json의 구성원?? 이름으로 뽑아냄

        for (int i = 0; i < row.length(); i++) {
            JSONObject station = row.getJSONObject(i); // row의 object 구성원을 station 이라는 변수에 담음
            int rowNum = station.getInt("rowNum");
            final String statnNm = station.getString("statnNm");
            final String subwayId = station.getString("subwayId");
            final String subwayNm = station.getString("subwayNm");

            Log.d("myapp", statnNm + " " + subwayId + " " + subwayNm);
            handler2.post(new Runnable() {
                @Override
                public void run() {
//                    tvResult.setText(statnNm + " "+ subwayId + " "+ subwayNm);

                    tvResult.append(statnNm + " "+ subwayId + " "+ subwayNm+"\n");

                }
            });
        }

    }//end parseJson
    public static void parseXML(String xmlText) {

        // ''''XML''' 파싱 위에 import 보면 '''''xml'''' 이라 되어있음
        DocumentBuilderFactory dbFactory; // DocumentBuilderFactory 를 경유해야 DocumentBuilder 를 만들 수 있다.
        DocumentBuilder dBuilder;

        // DOM parser
        try {
            dbFactory = DocumentBuilderFactory.newInstance(); // 기본생성자가 따로 없다
            dBuilder = dbFactory.newDocumentBuilder();

            // String -> InputStream 변환
            InputStream in = new ByteArrayInputStream(xmlText.getBytes("utf-8"));

            // InputStream -> DOM객체 생성
            Document dom = dBuilder.parse(in); // 여기 Document 는 jsoup 가 아니라 ''''''org.w3c''''''''' 임

            // DOM 최상위 document element 추출 뽑아낼때 최상위 객체를 뽑아야함
            Element docElement = dom.getDocumentElement(); // DOM 의 최상위 element ==> Document (root)를 뽑아냄

            // 파싱하기 전 normalize
            docElement.normalize(); // 흩어진 text node 들을 정렬하는 등의 절차
            // xml 파싱하기 전에 꼭 normalize() 해줘야 한다.
            // DOM 내의 데이터 파싱

            NodeList nList = docElement.getElementsByTagName("row"); // 특정태그들을 가진 요소들을 '''''태그이름'''''으로 뽑아냄 " row "
            System.out.println("<row> 의 개수 : " + nList.getLength());

            System.out.println();
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);

//						System.out.println("node type : "+node.getNodeType());   1 = element   == Element_node 로 상수화 되어있음

                if (node.getNodeType() != Node.ELEMENT_NODE) { // 1이 아닐 경우 제낀다.
                    continue;
                }
                Element rowElement = (Element) node; // 노드를 element 타입으로 캐스팅 node 가 부모 ----- Element 가 자식

                String rowNum = rowElement.getElementsByTagName("rowNum").item(0).getChildNodes().item(0).getNodeValue()
                        .trim();
                // 태그가 rowNum 인 것 중의 첫번째 를 뽑는 것 거기서 자식 노드에서 getNodeValue 를 사용하여 text를 뽑아내는 코드

               final String statnNm = rowElement.getElementsByTagName("statnNm").item(0).getChildNodes().item(0)
                        .getNodeValue().trim();

               final String subwayId = rowElement.getElementsByTagName("subwayId").item(0).getChildNodes().item(0)
                        .getNodeValue().trim();

               final String subwayNm = rowElement.getElementsByTagName("subwayNm").item(0).getChildNodes().item(0)
                        .getNodeValue().trim();
//						System.out.println(LINE_NUM);//확인을 위한 출력
//						System.out.println(LINE_NUM + " : " + SUB_STA_NM+" : "+RIDE_PASGR_NUM+ " : "+ALIGHT_PASGR_NUM);
                Handler handler3 = new Handler();
                handler3.post(new Runnable() {
                    @Override
                    public void run() {
//                    tvResult.setText(statnNm + " "+ subwayId + " "+ subwayNm);
                        tvResult.append(statnNm + " "+ subwayId + " "+ subwayNm+"\n");
                    }
                });
            }
        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (UnsupportedEncodingException e) { // 인코딩이 틀렸을 경우

            e.printStackTrace();
        } catch (SAXException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }// end parseXML


}//end Activity
