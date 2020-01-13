package com.example.wiezen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import androidx.annotation.NonNull;

public class MainActivity extends AuthUserAppCompatActivity {

    private TextView eMailTextView;
    private TextView passWordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnToLoginButtonClick(@NonNull View v){
        Intent toLogin = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(toLogin);
    }

    public void OnSignUpButtonClick(@NonNull View v) {

        eMailTextView = findViewById(R.id.SignUpEmailTextbox);
        passWordTextView = findViewById(R.id.SignUpPassWordTextBox);
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
        else {
            mFirebaseAuth
                    .createUserWithEmailAndPassword(email, pwd)
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Signup unsuccesfull", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent toHome = new Intent(MainActivity.this, HomeActivity.class);

                                startActivity(toHome);
                            }
                        }
                    });
        }
    }

    @Override
    protected Boolean NeedsAuth() {
        return false;
    }
}
