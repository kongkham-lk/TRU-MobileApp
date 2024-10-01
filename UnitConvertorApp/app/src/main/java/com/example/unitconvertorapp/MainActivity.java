package com.example.unitconvertorapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    String[] unitType = { "Farenhein", "Celsius" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText inputFrom = findViewById(R.id.input_from);
        EditText inputTo = findViewById(R.id.input_to);
        EditText inputValue = findViewById(R.id.input_value);
        Button btnConvert = findViewById(R.id.convert_btn);
        TextView result = findViewById(R.id.result);

        enableButton(btnConvert, false);

        inputFrom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkUnitInput(inputFrom, inputTo, btnConvert);
            }
        });

        inputTo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkUnitInput(inputFrom, inputTo, btnConvert);
            }
        });
    }

    private void checkUnitInput(EditText inputFrom, EditText inputTo, Button btnConvert) {
        String from = inputFrom.getText().toString();
        String to = inputTo.getText().toString();
        if ((Arrays.asList(unitType).contains(from) || Arrays.asList(unitType).contains(to))) {
            if (!from.equals(to))
                enableButton(btnConvert, true);
            else {
                if (Arrays.asList(unitType).contains(from) || Arrays.asList(unitType).contains(to))
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.toastError), Toast.LENGTH_SHORT).show();
                enableButton(btnConvert, false);
            }
        } else
            enableButton(btnConvert, false);

    }

    private void enableButton(Button btnConvert, boolean isEnable) {
        if (!isEnable) {
            btnConvert.setEnabled(false);
            btnConvert.setBackgroundColor(getResources().getColor(R.color.blueFate));
            btnConvert.setTextColor(getResources().getColor(R.color.darkGray));
        } else {
            btnConvert.setEnabled(true);
            btnConvert.setBackgroundColor(getResources().getColor(R.color.blue));
            btnConvert.setTextColor(getResources().getColor(R.color.white));
        }
    }
}