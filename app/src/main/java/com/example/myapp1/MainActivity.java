package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{

    String expression;
    int operand1, operand2;
    String operator;
    String [] operators = {"+", "-", "/", "*"};
    TextView question;

    public String formExpression()
    {
        Random rand = new Random();
        operand1 = rand.nextInt(100);
        operator = operators[rand.nextInt(4)];
        operand2 = rand.nextInt(100);
        return expression = Integer.toString(operand1)+ operator + Integer.toString(operand2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question = findViewById(R.id.Question);
        question.setText(formExpression()+"=? ");
    }
}
