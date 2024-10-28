package com.example.the_campus;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MotherActivity extends AppCompatActivity {


    LinearLayout homeLayout,classTimeLayout,jobLayout,profileLayout;
    ImageView home_icon,classTime_icon,job_icon,profile_icon;
    TextView home_text,classTime_text,job_text,profile_text;

    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolBarOfMain;
    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mother);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawerLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        toolBarOfMain = findViewById(R.id.toolBarOfMother);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        bottomNavigationView = findViewById(R.id.bottomNavigation);


        setSupportActionBar(toolBarOfMain);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        toolBarOfMain.setVisibility(View.VISIBLE);




//        navigation drawer design
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MotherActivity.this
                ,drawerLayout,toolBarOfMain
                ,R.string.OpenNavigationDrawer,R.string.CloseNavigationDrawer);

        //After instantiating your ActionBarDrawerToggle
        toggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.drawermenu_icon,MotherActivity.this.getTheme());
        toggle.setHomeAsUpIndicator(drawable);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        drawerLayout.addDrawerListener(toggle);
        navigationView.bringToFront();
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int navItemValue = item.getItemId();
                if(navItemValue == R.id.home){
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.frameLayout,HomeFragment.class,null)
                            .commit();
                    drawerLayout.closeDrawers();
                }
                else if(navItemValue == R.id.buyBooks){
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.frameLayout, HomeFragment.class,null)
                            .commit();
                    drawerLayout.closeDrawers();
                }
                else if(navItemValue == R.id.sellBooks){
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.frameLayout, HomeFragment.class,null)
                            .commit();
                    drawerLayout.closeDrawers();
                }
                else if(navItemValue == R.id.teacher){
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.frameLayout,HomeFragment.class,null)
                            .commit();
                    drawerLayout.closeDrawers();
                }
                else if(navItemValue == R.id.videoLectures){
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.frameLayout,HomeFragment.class,null)
                            .commit();
                    drawerLayout.closeDrawers();
                }
                else if(navItemValue == R.id.saved){
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.frameLayout,HomeFragment.class,null)
                            .commit();
                    drawerLayout.closeDrawers();
                }
                else if(navItemValue == R.id.payment){
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.frameLayout,HomeFragment.class,null)
                            .commit();
                    drawerLayout.closeDrawers();
                }
                else if(navItemValue == R.id.setting){
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.frameLayout,HomeFragment.class,null)
                            .commit();
                    drawerLayout.closeDrawers();
                }
                else{
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.frameLayout,HomeFragment.class,null)
                            .commit();
                    drawerLayout.closeDrawers();
                }




                return true;
            }
        });


        LoadHomeFragment();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    LoadHomeFragment();
                } else if (item.getItemId() == R.id.classTime) {
                    LoadClassTimeFragment();
                } else if (item.getItemId() == R.id.job) {
                    LoadJobFragment();
                } else if (item.getItemId() == R.id.profile) {
                    LoadProfileFragment();
                }else {
                    LoadHomeFragment();
                }
                return true;
            }
        });



    }

    private void LoadProfileFragment() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frameLayout,ProfileFragment.class,null)
                .commit();


    }

    private void LoadJobFragment() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frameLayout,JobFragment.class,null)
                .commit();


    }

    private void LoadClassTimeFragment() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frameLayout,ClassTimeFragment.class,null)
                .commit();


    }

    private void LoadHomeFragment() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frameLayout,HomeFragment.class,null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

}