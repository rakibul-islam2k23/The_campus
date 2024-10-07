package com.example.the_campus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreenActivity extends AppCompatActivity {


    private static final String TABLE_NAME = "user_onboarding";
    private static final String TABLE_KEY = "onBoarding";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen_activity);

        //don`t touch
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Splash Screen code start here


        LoadSplashScreen();

    }



    private void LoadSplashScreen() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    GoToMainActivity();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },3000);

    }



    private void GoToMainActivity() throws InterruptedException {
        Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}