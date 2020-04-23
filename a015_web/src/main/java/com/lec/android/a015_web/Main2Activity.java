package com.lec.android.a015_web;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
// #1 WebView 사용하여 웹 페이지 보여주기
// #2 묵시적 Intent 사용하여 웹 브라우져 띄우기

public class Main2Activity extends AppCompatActivity {
    WebView wv;
    EditText etUrl;
    Button btnWebView, btnBrowser;
    String url;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etUrl = findViewById(R.id.etUrl);
        wv = findViewById(R.id.wv);
        btnWebView = findViewById(R.id.btnWebView);
        btnBrowser = findViewById(R.id.btnBrowser);

        //WebView
        wv.getSettings().setJavaScriptEnabled(true);    //Javascrpit 사용 여부 ---> default 값은 false 임
        btnWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = etUrl.getText().toString().trim();
                wv.loadUrl(url);   //웹 페이지 url 로 읽어오기
                wv.setWebChromeClient(new WebChromeClient());
                //이거 안해주면 알림창 안뜸   ex) alert
                wv.setWebViewClient(new WebViewClient()); //안하면 html 내부에서 다른 페이지로 이동할 수가 없다.

            }
        });

        //브라우져
        btnBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = etUrl.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

    }//end onCreate

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK)&& wv.canGoBack()){
            wv.goBack();  //직전 페이지로 돌아감
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}//end Activity
