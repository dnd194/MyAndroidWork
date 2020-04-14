package com.lec.android.a003_listener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Main2Activity extends AppCompatActivity {

    int num,sum;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        LinearLayout ll = findViewById(R.id.ll);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btn0 = findViewById(R.id.btn0);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMul = findViewById(R.id.btnMul);
        Button btnEqul = findViewById(R.id.btnEqul);
        Button btnSub = findViewById(R.id.btnSub);
        Button btnDiv = findViewById(R.id.btnDiv);
        EditText etResult = findViewById(R.id.etResult);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = (String)((Button)v).getText();
//                Log.d("myapp",text);
                num = Integer.parseInt(text);

//                etResult.setText(text);
                etResult.setText(etResult.getText().append((String)((Button)v).getText()));
            }

        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (String)((Button)v).getText();
//                Log.d("myapp",text);
                num = Integer.parseInt(text);
//                etResult.setText(text);
                etResult.setText(etResult.getText().append((String)((Button)v).getText()));
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (String)((Button)v).getText();
//                Log.d("myapp",text);
                num = Integer.parseInt(text);
                etResult.setText(etResult.getText().append((String)((Button)v).getText()));
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (String)((Button)v).getText();
//                Log.d("myapp",text);
                num = Integer.parseInt(text);
                etResult.setText(etResult.getText().append((String)((Button)v).getText()));
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (String)((Button)v).getText();
//                Log.d("myapp",text);
                num = Integer.parseInt(text);
                etResult.setText(etResult.getText().append((String)((Button)v).getText()));
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (String)((Button)v).getText();
//                Log.d("myapp",text);
                num = Integer.parseInt(text);
                etResult.setText(etResult.getText().append((String)((Button)v).getText()));
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (String)((Button)v).getText();
//                Log.d("myapp",text);
                num = Integer.parseInt(text);
                etResult.setText(etResult.getText().append((String)((Button)v).getText()));
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (String)((Button)v).getText();
//                Log.d("myapp",text);
                num = Integer.parseInt(text);
                etResult.setText(etResult.getText().append((String)((Button)v).getText()));
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (String)((Button)v).getText();
//                Log.d("myapp",text);
                num = Integer.parseInt(text);
                etResult.setText(etResult.getText().append((String)((Button)v).getText()));
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (String)((Button)v).getText();
//                Log.d("myapp",text);
                num = Integer.parseInt(text);
                etResult.setText(etResult.getText().append((String)((Button)v).getText()));
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etResult.setText(etResult.getText().append((String)((Button)v).getText()));
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etResult.setText(etResult.getText().append((String)((Button)v).getText()));
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etResult.setText(etResult.getText().append((String)((Button)v).getText()));
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etResult.setText(etResult.getText().append((String)((Button)v).getText()));
            }
        });
        btnEqul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(etResult.getText().toString().contains("+")){
                   int operator = etResult.getText().toString().indexOf("+");
                   String left = etResult.getText().toString().substring(0, operator);
                   int ll = Integer.parseInt(left);

                   String right = etResult.getText().toString().substring(operator + 1, etResult.getText().toString().length());
                   int rr = Integer.parseInt(right);
                   sum = add(ll,rr);
                   String result = String.format("%d",sum);
                  etResult.setText(result);
               }
                if(etResult.getText().toString().contains("-")){
                    int operator = etResult.getText().toString().indexOf("-");
                    String left = etResult.getText().toString().substring(0, operator);
                    int ll = Integer.parseInt(left);

                    String right = etResult.getText().toString().substring(operator + 1, etResult.getText().toString().length());
                    int rr = Integer.parseInt(right);
                    sum = sub(ll,rr);
                    String result = String.format("%d",sum);
                    etResult.setText(result);
                }
                if(etResult.getText().toString().contains("X")){
                    int operator = etResult.getText().toString().indexOf("X");
                    String left = etResult.getText().toString().substring(0, operator);
                    int ll = Integer.parseInt(left);

                    String right = etResult.getText().toString().substring(operator + 1, etResult.getText().toString().length());
                    int rr = Integer.parseInt(right);
                    sum = mul(ll,rr);
                    String result = String.format("%d",sum);
                    etResult.setText(result);
                }
                if(etResult.getText().toString().contains("÷")){
                    int operator = etResult.getText().toString().indexOf("÷");
                    String left = etResult.getText().toString().substring(0, operator);
                    int ll = Integer.parseInt(left);

                    String right = etResult.getText().toString().substring(operator + 1, etResult.getText().toString().length());
                    int rr = Integer.parseInt(right);
                    sum = div(ll,rr);
                    String result = String.format("%d",sum);
                    etResult.setText(result);
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etResult.setText("");
            }
        });
    }
//    String text = (String)((Button)v).getText();
    public int add(int num1, int num2){

        return num1+num2;
    }
    public int sub(int num1, int num2){

        return num1-num2;
    }
    public int mul(int num1, int num2){

        return num1*num2;
    }
    public int div(int num1, int num2){
        return num1 / num2;
    }
}
