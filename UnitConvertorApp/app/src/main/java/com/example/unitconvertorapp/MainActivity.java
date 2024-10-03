package com.example.unitconvertorapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    String[] unitType = { "fahrenheit", "celsius" };

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
                checkSpelling(inputFrom, inputTo);
                checkUnitInput(inputFrom, inputTo, inputValue, btnConvert);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        inputTo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkSpelling(inputFrom, inputTo);
                checkUnitInput(inputFrom, inputTo, inputValue, btnConvert);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        inputValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkSpelling(inputFrom, inputTo);
                checkUnitInput(inputFrom, inputTo, inputValue, btnConvert);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String from = inputFrom.getText().toString();
                String to = inputTo.getText().toString();
                String value = inputValue.getText().toString();
                int value_int = Integer.parseInt(value);

                if (from.equals(unitType[0]) && to.equals(unitType[1])) {
                    String output = String.valueOf((float) (value_int - 32) * 5 / 9);
                    result.setText(output);
                }
                else {
                    String output = String.valueOf((float) (value_int * 9 / 5) + 32);
                    result.setText(output);
                }

                inputFrom.setText("");
                inputTo.setText("");
                inputValue.setText("");
                enableButton(btnConvert, false);
            }
        });
    }

    private void checkUnitInput(EditText inputFrom, EditText inputTo, EditText inputValue, Button btnConvert) {
        String from = inputFrom.getText().toString();
        String to = inputTo.getText().toString();
        String value = inputValue.getText().toString();

        if (from.isEmpty() || to.isEmpty()) {
            if (!from.isEmpty() && to.isEmpty())
                inputTo.setError(getResources().getString(R.string.toastMissingValueError));
            if (from.isEmpty() && !to.isEmpty())
                inputFrom.setError(getResources().getString(R.string.toastMissingValueError));
        } else {
            if (Arrays.asList(unitType).contains(from) && Arrays.asList(unitType).contains(to)) {
                if (value.isEmpty())
                    inputValue.setError(getResources().getString(R.string.toastMissingValueError));
                if (!from.equals(to))
                    enableButton(btnConvert, true);
                else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.toastSameUnitError), Toast.LENGTH_SHORT).show();
                    enableButton(btnConvert, false);
                }
            } else {
                enableButton(btnConvert, false);
            }
        }
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

    private void checkSpelling(EditText inputFrom, EditText inputTo) {
        String from = inputFrom.getText().toString();
        String to = inputTo.getText().toString();

        if (!from.isEmpty() && !Arrays.asList(unitType).contains(from))
            inputFrom.setError("Please Check The Spelling!");
        if (!to.isEmpty() && !Arrays.asList(unitType).contains(to))
            inputTo.setError("Please Check The Spelling!");
    }
}