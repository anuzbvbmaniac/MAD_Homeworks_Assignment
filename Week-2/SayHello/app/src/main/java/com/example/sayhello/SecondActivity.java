package com.example.sayhello;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //set previous counter number to text field
        Intent i = getIntent();
        String iCounter = i.getStringExtra("CounterText");

        TextView textCounter = findViewById(R.id.counter);

        textCounter.setText(iCounter);
    }
}
