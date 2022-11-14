package com.example.myapp1;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    boolean[] arithmeticExceptionFlag = {false, false, false, false, false};
    int operand1, operand2;
    int correctOption;
    Double [] correctAnswers, userAnswers;
    String operator;
    String [] operators = {"+", "-", "/", "*"};
    TextView question, resultText;
    Button option1, option2;
    int questionsAsked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question = findViewById(R.id.Question);
        option1 = findViewById(R.id.Option1);
        option2 = findViewById(R.id.Option2);
        resultText = findViewById(R.id.Result);
        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        questionsAsked=1;
        question.setText(questionsAsked++ +".   "+formExpression()+"=?");
        fillOptions();
        repeat();
        //System.out.println("In onClick");
    }

    private String formExpression()
    {
        Random rand = new Random();
        operand1 = rand.nextInt(100);
        operator = operators[rand.nextInt(4)];
        operand2 = rand.nextInt(100);
        return operand1 + operator + operand2;
    }

    @SuppressLint("SetTextI18n")
    private void fillOptions()
    {
        Random rnd = new Random();
        correctOption = rnd.nextInt(2);
        double ans = performOperation();
        correctAnswers[questionsAsked-1] = ans;
        switch (correctOption)
        {
            case 0:
                if (arithmeticExceptionFlag[questionsAsked-1])
                {
                    option1.setText("Undefined");
                    correctAnswers[questionsAsked-1] = 0.0;
                }
                else
                {
                    option1.setText(Double.toString(ans));
                    double vary = ans - rnd.nextInt(10);
                    option2.setText(Double.toString(vary));
                }
                break;
            case 1:
                if (arithmeticExceptionFlag[questionsAsked-1])
                {
                    option2.setText("Undefined");
                    correctAnswers[questionsAsked-1] = 0.0;
                }
                else
                {
                    double vary = ans - rnd.nextInt(10);
                    option1.setText(Double.toString(vary));
                    option2.setText(Double.toString(ans));
                }
                break;
        }

    }

    private double performOperation()
    {
        switch (operator)
        {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "/":
                if (operand2 == 0)
                {
                    arithmeticExceptionFlag[questionsAsked-1] = true;
                    return 0;
                }
                return operand1 / operand2;
            case "*":
                return operand1*operand2;
        }
        return 0;
    }

    private void repeat()
    {
        question.setText(questionsAsked++ + formExpression()+"=?");
        fillOptions();
    }

    @Override
    public void onClick(View view) {
        //System.out.println("In onClick");
        switch (view.getId()) {
            case R.id.Option1:
                if (correctOption == 0)
                    resultText.setText("Correct!");
                else
                    resultText.setText("Wrong!");
                userAnswers[questionsAsked-1] = Double.parseDouble(option1.getText().toString());
                break;

            case R.id.Option2:
                if (correctOption == 1)
                    resultText.setText("Correct!");
                else
                    resultText.setText("Wrong!");
                userAnswers[questionsAsked-1] = Double.parseDouble(option2.getText().toString());
                break;
        }
        if (questionsAsked<=5)
            repeat();
    }
}
