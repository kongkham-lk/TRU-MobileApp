package com.example.calculatorapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    enum OPERATION {
            ADD,
            MINUS,
            MULTIPLY,
            DIVIDE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText numTxt1 = findViewById(R.id.txtInput_num1);
        EditText numTxt2 = findViewById(R.id.txtInput_num2);
        Button btnAdd = findViewById(R.id.btn_add);
        Button btnMinus = findViewById(R.id.btn_minus);
        Button btnMultiply = findViewById(R.id.btn_multiply);
        Button btnDivide = findViewById(R.id.btn_divide);
        TextView result = findViewById(R.id.txt_result);
        Button btnClear = findViewById(R.id.btn_clear);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(4);

        btnClear.setEnabled(false);

        numTxt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                enableBtnClear(btnClear, numTxt1, numTxt2);
            }
        });

        numTxt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                enableBtnClear(btnClear, numTxt1, numTxt2);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeNumbers(numTxt1, numTxt2, result, OPERATION.ADD);
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeNumbers(numTxt1, numTxt2, result, OPERATION.MINUS);
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeNumbers(numTxt1, numTxt2, result, OPERATION.MULTIPLY);
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeNumbers(numTxt1, numTxt2, result, OPERATION.DIVIDE);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numTxt1.setText("");
                numTxt2.setText("");
                btnClear.setEnabled(false);
            }
        });
    }

    private void enableBtnClear(Button btnClear, EditText numTxt1, EditText numTxt2) {
        if (numTxt1.getText().toString().matches("") && numTxt2.getText().toString().matches(""))
            btnClear.setEnabled(false);
        else
            btnClear.setEnabled(true);
    }

    private void computeNumbers(EditText numTxt1, EditText numTxt2, TextView result, OPERATION operation) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(4);

        double num1 = Double.parseDouble(numTxt1.getText().toString());
        double num2 = Double.parseDouble(numTxt2.getText().toString());
        if (operation == OPERATION.ADD) {
            double sum = num1 + num2;
            result.setText("The sum is: " + df.format(sum));
        }
        else if (operation == OPERATION.MINUS) {
            double sum = num1 - num2;
            result.setText("The differece is: " + df.format(sum));
        }
        else if (operation == OPERATION.MULTIPLY) {
            double sum = num1 * num2;
            result.setText("The product is: " + df.format(sum));
        }
        else {
            if (num2 != 0) {
                double sum = num1 / num2;
                result.setText("The quotient is: " + df.format(sum));
            }
            else
                result.setText("Error!!!");
        }
    }
}