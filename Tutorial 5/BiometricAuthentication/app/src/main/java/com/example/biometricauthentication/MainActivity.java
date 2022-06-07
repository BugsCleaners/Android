package com.example.biometricauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import androidx.biometric.BiometricPrompt;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    //UI Views
    private TextView authStatusTv;
    private Button authBtn;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //initialise UI Views
        authStatusTv= findViewById(R.id.authStatusTv);
        authBtn = findViewById(R.id.authBtn);


    //init bio metric

    executor = ContextCompat.getMainExecutor(this);
    biometricPrompt = new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
        @Override
        public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
            super.onAuthenticationError(errorCode, errString);
            //error authenticating, stop tasks that require authenticating
            authStatusTv.setText("Authentication error: "+errString);
            Toast.makeText(MainActivity.this, "Authentication error: "+errString, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
         //authentication succeed, continue tasks that require authentication
         authStatusTv.setText("Authentication Succeed..!");
            Toast.makeText(MainActivity.this, "Authentication Succeed..!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            //authentication succeed, continue tasks that require authentication
            authStatusTv.setText("Authentication Failed..!");
            Toast.makeText(MainActivity.this, "Authentication Failed..!", Toast.LENGTH_SHORT).show();



        }
    });

      //setup title,description on auth dialog
       promptInfo = new BiometricPrompt.PromptInfo.Builder()
        .setTitle("Biometric Authentication")
        .setSubtitle("login using fingerprint authentication")
        .setNegativeButtonText("User App Password")
        .build();
        //Handle authBtn click, start authentication
    authBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           //show auth dialog
            biometricPrompt.authenticate(promptInfo);
        }
    });

    }
}