package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText numTxt1 = findViewById(R.id.txtInput_num1);
        EditText numTxt2 = findViewById(R.id.txtInput_num2);
        Button btnAdd = findViewById(R.id.btn_add);
        TextView result = findViewById(R.id.txt_result);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1 = Integer.parseInt(numTxt1.getText().toString());
                int num2 = Integer.parseInt(numTxt2.getText().toString());
                int sum = num1 + num2;
                result.setText("The Sum is: " + sum);
            }
        });
    }
}