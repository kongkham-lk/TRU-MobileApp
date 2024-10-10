package com.example.implicitintentapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText inputSearch = findViewById(R.id.inputSearch);
        Button btnSearch = findViewById(R.id.btnGoogle);
        EditText inputTel = findViewById(R.id.inputTel);
        Button btnDial = findViewById(R.id.btnDial);
        EditText inputEmail = findViewById(R.id.inputEmail);
        EditText inputSubject = findViewById(R.id.inputSubject);
        EditText inputEmailContent = findViewById(R.id.inputEmailContent);
        Button btnMail = findViewById(R.id.btnMail);
        Button btnShare = findViewById(R.id.btnShare);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String actionType = "https://";
                String searchEngine = "www.google.com";
                String searchTopic = "/search?q=" + inputSearch.getText().toString();
                String url = actionType + searchEngine + searchTopic;

                // pass in ONE line:      Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                // pass in MULTIPLE line: Intent intent = new Intent();
                //                        intent.setAction(Intent.ACTION_VIEW);
                //                        intent.setData(Uri.parse(url));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String actionType = "tel:";
                String phoneNumber = inputTel.getText().toString();
                String tel = actionType + phoneNumber;

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(tel));
                try
                {
                    startActivity(intent);
                }
                catch (SecurityException s)
                {
                    Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] email = {inputEmail.getText().toString()};
                String subject = inputSubject.getText().toString();
                String content = inputEmailContent.getText().toString();

                // ACTION_SEND and ACTION_SENDTO is for sending data to other app
                // .ACTION_SEND shows different app option, but
                // .ACTION_SENDTO directly open default email app
                Intent intentEmail = new Intent(Intent.ACTION_SEND);
                intentEmail.putExtra(Intent.EXTRA_EMAIL, email); // need to pass in as string of array
                intentEmail.putExtra(Intent.EXTRA_SUBJECT, subject);
                intentEmail.putExtra(Intent.EXTRA_TEXT, content); // Not sure why this display on sharing text on app option display page
                // intentEmail.setData(Uri.parse("mailto:")); // this seems not to work on filter out and won't show any option without setType()
                intentEmail.setType("message/rfc822"); // require this for showing different app option on share screen

                try {
                    startActivity(Intent.createChooser(intentEmail, "Send email..."));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sharedText = "This is my text to send.";

                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, sharedText);
                sendIntent.setType("text/plain"); // require this for showing different app option on share screen

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
    }
}