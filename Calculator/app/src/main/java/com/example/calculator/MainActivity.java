package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText inputnumber;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;
    private Button buttonMinus;
    private Button buttonMul;
    private Button buttonDiv;
    private Button buttonDot;
    private Button buttonPlus;
    private Button buttonSqr;
    private Button buttonSqrt;
    private Button buttonC;
    private Button buttonBack;
    private Button buttonEqual;


    private double number1;
    private double number2;
    private double result;
    private double check;
    private double dot;
    enum Sign{
        PlUS,MINUS,MUL,DIV
    }
    private Sign sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputnumber = findViewById(R.id.Input);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button0 = findViewById(R.id.button0);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonMul = findViewById(R.id.buttonMul);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonDot = findViewById(R.id.buttonDot);
        buttonSqrt = findViewById(R.id.buttonSqrt);
        buttonSqr = findViewById(R.id.buttonSqr);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonEqual = findViewById(R.id.buttonEqual);
        buttonC = findViewById(R.id.buttonC);
        buttonC.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button0.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonMul.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
        buttonSqr.setOnClickListener(this);
        buttonSqrt.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
    }
    public void CheckLength(){
        if (inputnumber.length()>8){
            check=0;
        }else{
            check=1;
        }
    }
    @Override
    public void onClick(View view){
        CheckLength();
        switch (view.getId()){
            case R.id.button1:{
                if (check==1) {
                    inputnumber.append("1");
                }
                break;
            }
            case R.id.button2:{
                if (check==1) {
                    inputnumber.append("2");
                }
                break;
            }
            case R.id.button3:{
                if (check==1) {
                    inputnumber.append("3");
                }
                break;
            }
            case R.id.button4:{
                if (check==1) {
                    inputnumber.append("4");
                }
                break;
            }
            case R.id.button5:{
                if (check==1) {
                    inputnumber.append("5");
                }
                break;
            }
            case R.id.button6:{
                if (check==1) {
                    inputnumber.append("6");
                }
                break;
            }
            case R.id.button7:{
                if (check==1) {
                    inputnumber.append("7");
                }
                break;
            }
            case R.id.button8:{
                if (check==1) {
                    inputnumber.append("8");
                }
                break;
            }
            case R.id.button9:{
                if (check==1) {
                    inputnumber.append("9");
                }
                break;
            }
            case R.id.button0:{
                if (inputnumber.length()>0 && check==1) {
                    inputnumber.append("0");
                }
                break;
            }
            case R.id.buttonDot:{
                if (dot!=1) {
                    inputnumber.append(".");
                    dot = 1;
                }
                break;
            }
            case R.id.buttonC:{
                inputnumber.setText("");
                break;
            }
            case R.id.buttonPlus:{
                if ( dot==1) {
                    inputnumber.append("0");
                }
                dot=0;
                number1 = Double.parseDouble(inputnumber.getText().toString());
                inputnumber.setText("");
                sign = Sign.PlUS;
                break;
            }
            case R.id.buttonMinus:{
                if ( dot==1) {
                    inputnumber.append("0");
                }
                dot=0;
                number1 = Double.parseDouble(inputnumber.getText().toString());
                inputnumber.setText("");
                sign = Sign.MINUS;
                break;
            }
            case R.id.buttonMul:{
                if ( dot==1) {
                    inputnumber.append("0");
                }
                dot=0;
                number1 = Double.parseDouble(inputnumber.getText().toString());
                inputnumber.setText("");
                sign = Sign.MUL;
                break;
            }
            case R.id.buttonDiv:{
                if ( dot==1) {
                    inputnumber.append("0");
                }
                dot=0;
                number1 = Double.parseDouble(inputnumber.getText().toString());
                inputnumber.setText("");
                sign = Sign.DIV;
                break;
            }
            case R.id.buttonSqr:{
                dot=0;
                number1 = Double.parseDouble(inputnumber.getText().toString());
                number1 = number1*number1;
                if((number1 == Math.floor(number1)) && !Double.isInfinite(number1)) {
                    inputnumber.setText(String.valueOf((int)(number1)));
                }else {
                    inputnumber.setText(String.valueOf((Math.floor((number1) * 1000000)) / (1000000)));
                }
                break;
            }
            case R.id.buttonSqrt:{
                dot=0;
                number1 = Double.parseDouble(inputnumber.getText().toString());
                number1 = Math.sqrt(number1);
                if((number1 == Math.floor(number1)) && !Double.isInfinite(number1)) {
                    inputnumber.setText(String.valueOf((int)(number1)));
                }else {
                    inputnumber.setText(String.valueOf((Math.floor((number1) * 1000000)) / (1000000)));
                }
                break;
            }
            case R.id.buttonEqual:{
                if ( dot==1) {
                    inputnumber.append("0");
                }
                dot=0;
                number2 = Double.parseDouble(inputnumber.getText().toString());
                if (sign == Sign.PlUS){
                    result =  number1+number2;
                }else if (sign == Sign.MINUS){
                    result = number1-number2;
                }else if (sign == Sign.MUL){
                    result = number1*number2;
                }else if (sign == Sign.DIV){
                    result = number1/number2;
                }
                if((result == Math.floor(result)) && !Double.isInfinite(result)) {
                    inputnumber.setText(String.valueOf((int) result));
                }else {
                    inputnumber.setText(String.valueOf((Math.floor(result * 1000000)) / (1000000)));
                }
                break;
            }
        }
    }

}
