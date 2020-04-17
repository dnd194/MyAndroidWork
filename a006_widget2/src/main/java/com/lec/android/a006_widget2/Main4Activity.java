package com.lec.android.a006_widget2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {
    TextView tvResult ;
    RatingBar rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        rb = findViewById(R.id.ratingBar);
        tvResult = findViewById(R.id.tvResult);
        //xml에 rating바 관련 주석이 있음
        //레이팅의 값이 변할 때 호출되는 콜백
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tvResult.setText("rating : "+rating);
            }
        });

    }
}
