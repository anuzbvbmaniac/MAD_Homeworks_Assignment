package com.example.android.todoapp.introview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.android.todoapp.R;
import com.example.android.todoapp.tasks.TasksActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {


    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    ImageButton btn_next;
    int position = 0;
    Button btn_start;
    Animation btnAnim;
    ImageView img_textLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // check if the onboarding or Intro screen has already been opened
        if (restorePredData()) {
            Intent mainActivity = new Intent(getApplicationContext(), TasksActivity.class);
            startActivity(mainActivity);
            finish();
        }

        //make activity full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        setContentView(R.layout.activity_intro);

        //init views
        tabIndicator = findViewById(R.id.tab_indicator);
        btn_next = findViewById(R.id.btn_next);
        img_textLogo = findViewById(R.id.img_textlogo);
        btn_start = findViewById(R.id.btn_start);


        //fill list screen
        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Track your work.\n" +
                "Get results.", "Add your tasks or works and get result through the app\n" +
                "if the work has been completed or is on pending.", R.drawable.onboarding1));

        mList.add(new ScreenItem("Create tasks and assign\n" +
                "them to colleagues.", "Create your personal tasks or group tasks which could\n" +
                "be assigned to other members.", R.drawable.onboarding2));

        mList.add(new ScreenItem("Always stay organized with \n" +
                "projects and teams.", "Your every tasks, projects and team members are properly\n" +
                "organized for easy and better usability.", R.drawable.onboarding3));

        mList.add(new ScreenItem("Get notified when work\n" +
                "happens.", "Always get notified for your tasks when its happening\n" +
                "according to the priority and timings that you set.", R.drawable.onboarding4));




        //setup viewpager
        screenPager = findViewById(R.id.screen_pager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        //setup tablayout with viewpager
        tabIndicator.setupWithViewPager(screenPager);

        //next button click listener
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();

                if(position < mList.size()) {
                    position++;
                    screenPager.setCurrentItem(position);
                }

                // if page reach at last then again start from page 1
                if(position == mList.size()) {
                    position = 0;
                    screenPager.setCurrentItem(position);
                }
            }
        });


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(getApplicationContext(), TasksActivity.class);
                startActivity(mainActivity);

                //save the boolean value to storage so next time when user opens the app
                //we know he is already passde by the onboarding screen
                //using sharedPreferences for this process
                savePrefsData();
                finish();
            }
        });

    }

    private boolean restorePredData() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = sharedPreferences.getBoolean("isIntroopened", false);
        return isIntroActivityOpenedBefore;
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroopened", true);
        editor.commit();
    }
}
