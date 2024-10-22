package com.example.the_campus;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private TextView regSignInTextView;
    private TextInputEditText regUsername,regGmail,regPassword,regConfirmPassword;
    private Button regSignUpButton;
    private ProgressBar progressbar;



    String userName;
    String gmail;
    String password;
    String confirmPassword;



    //firebase
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        //firebase
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("campus");




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        regSignInTextView = findViewById(R.id.regSignInTextView);
        regSignUpButton = findViewById(R.id.regSignUpButton);
        regUsername = findViewById(R.id.regUsername);
        regGmail = findViewById(R.id.regGmail);
        regPassword = findViewById(R.id.regPassword);
        regConfirmPassword = findViewById(R.id.regConfirmPassword);
        progressbar = findViewById(R.id.progressbar);

        regSignInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });



        regSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserRegisterAccount();
            }
        });



    }


    private void UserRegisterAccount() {

        userName = Objects.requireNonNull(regUsername.getText()).toString().trim();
        gmail = Objects.requireNonNull(regGmail.getText()).toString().trim();
        password = Objects.requireNonNull(regPassword.getText()).toString();
        confirmPassword = Objects.requireNonNull(regConfirmPassword.getText()).toString();

        if (userName.isEmpty()){
            regUsername.setError("Enter user Name");
            regUsername.requestFocus();
        }else if (gmail.isEmpty()){
            regGmail.setError("Enter gmail");
            regGmail.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(gmail).matches()) {
            regGmail.setError("Invalid gmail");
            regGmail.requestFocus();
        } else if (password.isEmpty()) {
            regPassword.setError("Enter password");
            regPassword.requestFocus();
        } else if (password.length() < 6) {
            regPassword.setError("Password must be more then 6");
            regPassword.requestFocus();
        } else if (!confirmPassword.equals(password)) {
            regConfirmPassword.setError("Password don`t matching");
            regConfirmPassword.requestFocus();
        }else {
            progressbar.setVisibility(View.VISIBLE);
            regSignUpButton.setVisibility(View.GONE);
            regSignUpButton.setClickable(false);
            PushRegDataToFirebase(gmail,password);
        }
        
    }

    private void PushRegDataToFirebase(String gmail, String password) {

        mAuth.createUserWithEmailAndPassword(gmail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressbar.setVisibility(View.GONE);
                    regSignUpButton.setVisibility(View.VISIBLE);
                    regSignUpButton.setClickable(true);
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                    PushRegDataToRealtime();
                }else {
                    progressbar.setVisibility(View.GONE);
                    regSignUpButton.setVisibility(View.VISIBLE);
                    regSignUpButton.setClickable(true);
                    Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void PushRegDataToRealtime() {

        String userUid;
        userUid = FirebaseAuth.getInstance().getUid();
        RegModelClass regModelClass = new RegModelClass(userName,gmail,password,userUid);
        databaseReference.child(userUid).child("profileInfo").setValue(regModelClass);
    }


}