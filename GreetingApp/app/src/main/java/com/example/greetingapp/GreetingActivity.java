package com.example.greetingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.Date;

public class GreetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_greeting);

        Intent passData = getIntent();
        String nameInputPassing = passData.getStringExtra(getResources().getString(R.string.key_nameInput));

        TextView greetingText = findViewById(R.id.displayGreeting);
        TextView greetingTextEnding = findViewById(R.id.greetingTextEnding);
        Button btnBack = findViewById(R.id.btnBack);

        String[] endingGreetingList = {"YOU SUCKS!!!", "YOU ARE GAY!!!"};
        String[] validName = {"j", "jay", "kongkham", "kong", "luangkhot", "luang", "jian"};

        long timeMilli = new Date().getTime();
        int i = (int)timeMilli % endingGreetingList.length;
        String ending = nameInputPassing == null || nameInputPassing.isEmpty()
                            ? "" : Arrays.asList(validName).contains(nameInputPassing.toLowerCase())
                                ? "YOU ARE THE BEST!!!" : endingGreetingList[i];

        greetingText.setText(nameInputPassing == null || nameInputPassing.isEmpty()
                                ? "Please enter a name!!!" : "Hey, " + nameInputPassing + ".");
        greetingTextEnding.setText(ending);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tempIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(tempIntent);
            }
        });
    }
}