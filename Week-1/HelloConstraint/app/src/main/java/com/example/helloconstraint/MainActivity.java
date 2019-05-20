package com.example.helloconstraint;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toastMessage, Toast.LENGTH_SHORT);
        toast.show();
    }


    public void countUp(View view) {
        ++mCount;
        if (mShowCount != null) {
            mShowCount.setText(Integer.toString(mCount));

            Button btn_count = (Button) view;
            // if the count is even, set btn style to primary else to success
            if(mCount % 2 == 0) {
                btn_count.setBackground(getDrawable(R.drawable.btn_primary));
                btn_count.setTextColor(Color.parseColor("#000000"));
//                btn_count.setTextColor(ContextCompat.getColor(context, R.color.grey));
            } else {
                btn_count.setBackground(getDrawable(R.drawable.btn_success));
            }
        }
        Button btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_reset.setBackground(getDrawable(R.drawable.btn_primary));
        btn_reset.setTextColor(Color.parseColor("#000000"));
    }


    public void resetToZero(View view) {
        Button btn_reset = (Button) view;

        // Toast message if the counter is already at O.
        if(mCount == 0) {
            Toast toast = Toast.makeText(this, R.string.resetToast, Toast.LENGTH_SHORT);
            toast.show();
        }

        // Reset counter text to zero along with disabled style reset button and default count button
        if(mShowCount != null) {
            mCount = 0;
            mShowCount.setText((Integer.toString(mCount)));

            btn_reset.setBackground(getDrawable(R.drawable.btn_disabled));
            btn_reset.setTextColor(Color.parseColor("#ffffff"));

            Button btn_count = (Button) findViewById(R.id.btn_count);
            btn_count.setBackground(getDrawable(R.drawable.btn_transparent));
            btn_count.setTextColor(Color.parseColor("#ccffffff"));
        }
    }
}
