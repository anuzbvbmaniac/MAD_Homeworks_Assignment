package com.example.counterhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView txt_count;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_count = findViewById(R.id.txt_count);

        if (savedInstanceState != null && savedInstanceState.containsKey("CounterValue")) {
            Log.d(LOG_TAG, "In onCreate - Restoring the data from saved bundle");
            txt_count.setText(savedInstanceState.getString("CounterValue"));
            mCount = Integer.parseInt(savedInstanceState.getString("CounterValue"));
        }

    }

    public void increaseCounter(View view) {
        ++mCount;
        if (txt_count != null) {
            txt_count.setText(Integer.toString(mCount));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("CounterValue", txt_count.getText().toString());
    }

}
