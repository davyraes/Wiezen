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

public class LoginActivity extends AuthUserAppCompatActivity {

    private TextView eMailTextView;
    private TextView passWordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void LoginButtonClick(@NonNull View v){
        eMailTextView = findViewById(R.id.LoginEmailTextbox);
        passWordTextView = findViewById(R.id.LoginPassWordTextBox);

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
                            Intent toHome = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(toHome);
                        }
                    }
                });
    }

    public void OnToSignUpButtonClick(@NonNull View v){
        Intent toMain = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(toMain);
    }

    @Override
    protected Boolean NeedsAuth() {
        return false;
    }
}
