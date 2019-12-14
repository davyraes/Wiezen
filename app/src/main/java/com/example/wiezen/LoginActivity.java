package com.example.wiezen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextView eMailTextView;
    private TextView passWordTextView;
    private Button LoginButton;
    private Button toSignupButton;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFireBaseUser = mFirebaseAuth.getCurrentUser();
                if(mFireBaseUser != null){
                    Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
            }
        };

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eMailTextView = v.findViewById(R.id.LoginEmailTextbox);
                passWordTextView = v.findViewById(R.id.LoginPassWordTextBox);

                String email = eMailTextView.getText().toString();
                String pwd = passWordTextView.getText().toString();
                if (email.isEmpty()){
                    eMailTextView.setError("Email missing");
                    eMailTextView.requestFocus();
                }
                else if (pwd.isEmpty()){
                    passWordTextView.setError("password Empty");
                    eMailTextView.requestFocus();
                }

                mFirebaseAuth
                        .signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Login unsuccesfull", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                }
                            }
                        });
            }
        });

        toSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }
}
