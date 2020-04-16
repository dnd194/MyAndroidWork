package com.lec.android.a004_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {
    TextView tvResult;
    EditText op1,op2;
    Button btnPlus,btnMul,btnDiv,btnMinus;
    String oper1, oper2,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


         op1 = findViewById(R.id.op1);
         op2 = findViewById(R.id.op2);
         tvResult = findViewById(R.id.tvResult);
         btnPlus = findViewById(R.id.btnPlus);
         btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
         btnMinus = findViewById(R.id.btnMinus);

        op1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    op1.setBackgroundColor(Color.YELLOW);
                }else
                    op1.setBackgroundColor(Color.parseColor("#00000000"));
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               oper1 = op1.getText().toString().trim();
               oper2 = op2.getText().toString().trim();
               int num1 = Integer.parseInt(oper1);
               int num2 = Integer.parseInt(oper2);
               int sum = num1+num2;
               result = String.format("%d",sum);
               tvResult.setText(result);

            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oper1 = op1.getText().toString().trim();
                oper2 = op2.getText().toString().trim();
                int num1 = Integer.parseInt(oper1);
                int num2 = Integer.parseInt(oper2);
                int sum = num1-num2;
                result = String.format("%d",sum);
                tvResult.setText(result);
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oper1 = op1.getText().toString().trim();
                oper2 = op2.getText().toString().trim();
                int num1 = Integer.parseInt(oper1);
                int num2 = Integer.parseInt(oper2);
                int sum = num1*num2;
                result = String.format("%d",sum);
                tvResult.setText(result);
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oper1 = op1.getText().toString().trim();
                oper2 = op2.getText().toString().trim();
                int num1 = Integer.parseInt(oper1);
                int num2 = Integer.parseInt(oper2);
                int sum = num1/num2;
                result = String.format("%d",sum);
                tvResult.setText(result);
            }
        });
    }
}
