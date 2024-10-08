package com.example.greetingapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText nameInput = findViewById(R.id.nameInput);
        Button btnGreet = findViewById(R.id.btnGreeting);

        btnGreet.setEnabled(false);

        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String inputField = nameInput.getText().toString();
                if (inputField.isEmpty())
                    btnGreet.setEnabled(false);
                else
                    btnGreet.setEnabled(true);
            }
        });

        btnGreet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tempIntent = new Intent(getApplicationContext(), GreetingActivity.class);
                tempIntent.putExtra(getResources().getString(R.string.key_nameInput), nameInput.getText().toString());
                startActivity(tempIntent);
            }
        });
    }
}