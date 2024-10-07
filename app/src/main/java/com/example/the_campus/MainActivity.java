package com.example.the_campus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    ViewPager slideViewPager;
    LinearLayout dotLayout;
    ImageButton leftImageButton, rightImageButton;
    Button skipButton;
    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;
    LottieAnimationView languageSwitch;
    boolean languageSwitchOn = false;

    private static final String TABLE_NAME = "user_onboarding";
    private static final String TABLE_KEY = "onBoarding";
    boolean onboadingValue = false;


    private int[] animations = {
            R.raw.noticefeed_lottie, // Place your Lottie animation files in res/raw/
            R.raw.smartattendents_lottie,
            R.raw.assignment_loffie
    };

    private String[] titles = {

            "Notice Feed",
            "Smart Attendance",
            "Jobs & Assignment"
    };

    private String[] descriptions = {
            "Diploma All type Notice within 1 hour!.Its work on important updates, such as exam schedules, event announcements, club meetings, and administrative notifications",
            "Transform your attendance process with Smart Attendance!Say goodbye to manual roll calls and hello to real-time data insights!.Experience a seamless, hassle-free attendance system.",
            "All JOB and ASSIGNMENT notifications from your department teachers, along with their ANSWERS, are available for you to view at any time."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        slideViewPager = findViewById(R.id.viewPagerOfMainAc);
        leftImageButton = findViewById(R.id.leftButtonMainAc);
        skipButton = findViewById(R.id.skipButtonMainAc);
        rightImageButton = findViewById(R.id.rightButtonMainAc);
        dotLayout = findViewById(R.id.linearLayout);
        languageSwitch = findViewById(R.id.languageSwitch);

        LoadLastOnBoarding();


        languageSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (languageSwitchOn) {
                    languageSwitch.setMinAndMaxProgress(0.5f, 1.0f);
                    languageSwitch.playAnimation();
                    languageSwitchOn = false;
                } else {
                    languageSwitch.setMinAndMaxProgress(0.0f, 0.5f);
                    languageSwitch.playAnimation();
                    languageSwitchOn = true;
                }
            }
        });

        leftImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GetItem(0) > 0) {
                    slideViewPager.setCurrentItem(GetItem(-1), true);
                }
            }
        });

        rightImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GetItem(0) < 2) {
                    slideViewPager.setCurrentItem(GetItem(1), true);
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    SaveLastOnBoarding();
                }
            }
        });
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                SaveLastOnBoarding();
            }
        });

        viewPagerAdapter = new ViewPagerAdapter(this, animations, titles, descriptions);
        slideViewPager.setAdapter(viewPagerAdapter);

        slideViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                SetIndicator(position);
                if (position > 0) {
                    leftImageButton.setVisibility(View.VISIBLE);
                } else {
                    leftImageButton.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private boolean NetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());

                if (networkCapabilities != null) {
                    if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                        return true;
                    }
                    else if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                        return true;
                    }else {
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
                    }
                }

            }else{
                ConnectivityManager connectivityManager1 = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager1.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnectedOrConnecting();
            }
        }
        return false;
    }

    private void SaveLastOnBoarding() {
        SharedPreferences sharedPreferences = getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TABLE_KEY, String.valueOf(onboadingValue));
        editor.apply();
    }

    private void LoadLastOnBoarding() {
        SharedPreferences sharedPreferences = getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE);
        String value = sharedPreferences.getString("onBoarding", "Data Not found");
        if (value.equals("false")) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {

        }
    }


    private int GetItem(int i) {
        return slideViewPager.getCurrentItem() + i;
    }

    public void SetIndicator(int position) {
        dots = new TextView[3];
        dotLayout.removeAllViews();
        Animation animation = AnimationUtils.loadAnimation(
                getApplicationContext(),
                R.anim.zoomin);

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(30);
            dots[i].setTextColor(getResources().getColor(R.color.off_white, getApplicationContext().getTheme()));
            dotLayout.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.main_color, getApplicationContext().getTheme()));
        dots[position].setAnimation(animation);
    }


}