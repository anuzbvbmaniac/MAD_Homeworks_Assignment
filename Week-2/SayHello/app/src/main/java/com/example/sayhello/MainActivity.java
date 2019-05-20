package com.example.sayhello;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String COUNTER_TEXT = "com.example.android.sayhello.extra.MESSAGE";
    private int mCount = 0;
    private TextView mShowCount;

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.textCount);

        // making status bar transparent
        if(Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }



//        // on long click listener
//        button = (Button) findViewById(R.id.btn_count);
//        button.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                //toast message if the count is already at 0
//                if(mCount == 0) {
//                    Toast toast = Toast.makeText(this, R.string.resetToast, Toast.LENGTH_SHORT);
//                }
//            }
//        });
    }

    public void sayHello(View view) {
        Intent i = new Intent(this, SecondActivity.class);

        String counter = mShowCount.getText().toString();
        i.putExtra("CounterText", counter);
        startActivity(i);
    }

    public void counterUp(View view) {
        Button btn_CounterUp =(Button) view;

        ++mCount;
        if(mShowCount != null) {
            mShowCount.setText(Integer.toString(mCount));

            // change the background of button respective with odd and even numbers
            if(mCount % 2 == 0) {
                btn_CounterUp.setBackground(getDrawable(R.drawable.btn_primary));
            } else {
                btn_CounterUp.setBackground(getDrawable(R.drawable.btn_secondary));
            }
        }

        //enable zero button when counter is increased
        Button btn_reset = (Button)findViewById(R.id.btn_reset);
        btn_reset.setBackground(getDrawable(R.drawable.btn_primary));
        btn_reset.setTextColor(Color.parseColor("#ffffff"));
    }


    public void resetToZero(View view) {
        Button btn_reset = (Button) view;

        //toast if already zt zero
        if(mCount == 0) {
            Toast toast = Toast.makeText(this, getString(R.string.reset_toast), Toast.LENGTH_SHORT);
            toast.show();
        }

        //reset counter to zero with button style change
        if(mShowCount != null) {
            mCount = 0;
            mShowCount.setText((Integer.toString(mCount)));

            btn_reset.setBackground(getDrawable(R.drawable.btn_disabled));
            btn_reset.setTextColor(Color.parseColor("#3AB3DD"));

            Button btn_CounterUp = (Button)findViewById(R.id.btn_count);
            btn_CounterUp.setBackground(getDrawable(R.drawable.btn_primary));
        }
    }
}
