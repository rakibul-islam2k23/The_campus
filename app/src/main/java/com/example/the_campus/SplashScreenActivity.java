package com.example.the_campus;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextPaint;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreenActivity extends AppCompatActivity {


    private static final String TABLE_NAME = "user_onboarding";
    private static final String TABLE_KEY = "onBoarding";


    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen_activity);

        textView = findViewById(R.id.textView);


        textView.setText("NGPI");

        TextPaint paint = textView.getPaint();
        float width = paint.measureText("NGPI");

        Shader textShader = new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        Color.parseColor("#74ebd5"),
                        Color.parseColor("#ACB6E5"),
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);

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