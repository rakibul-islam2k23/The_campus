package com.example.the_campus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
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

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TextView logInTextView;
    TextInputEditText gmail;
    TextInputEditText password;
    Button signInButton;
    TextView forget;
    ProgressBar progressBar;


    //firebase working
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        firebaseAuth = FirebaseAuth.getInstance();

        logInTextView = findViewById(R.id.logInTextView);
        gmail = findViewById(R.id.loginGmail);
        password = findViewById(R.id.loginPassword);
        signInButton = findViewById(R.id.signInButton);
        forget = findViewById(R.id.loginForget);
        progressBar = findViewById(R.id.progressbar);



        logInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFromFirebase();
            }
        });
    }

    private void LoginFromFirebase() {
        String loginGmail = Objects.requireNonNull(gmail.getText()).toString().trim();
        String loginPassword = Objects.requireNonNull(password.getText()).toString().trim();

        if (loginGmail.isEmpty()) {
            gmail.setError("Enter gmail ");
            gmail.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(loginGmail).matches()) {
            gmail.setError("Invalid gmail");
            gmail.requestFocus();
        } else if (loginPassword.isEmpty()) {
            password.setError("Enter password");
            password.requestFocus();
        } else if (loginPassword.length() < 6) {
            password.setError("Password must be more then 6");
            password.requestFocus();
        } else{
            progressBar.setVisibility(View.VISIBLE);
            signInButton.setVisibility(View.GONE);
            signInButton.setClickable(false);

            firebaseAuth.signInWithEmailAndPassword(loginGmail,loginPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                signInButton.setVisibility(View.VISIBLE);
                                signInButton.setClickable(true);
                                Intent intent = new Intent(LoginActivity.this,MotherActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                progressBar.setVisibility(View.GONE);
                                signInButton.setVisibility(View.VISIBLE);
                                signInButton.setClickable(true);
                                Toast.makeText(LoginActivity.this, ""
                                        + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }


    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(LoginActivity.this,MotherActivity.class);
            startActivity(intent);
            finish();
        }
    }
}