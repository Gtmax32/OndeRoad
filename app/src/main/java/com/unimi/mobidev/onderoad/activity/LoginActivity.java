package com.unimi.mobidev.onderoad.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.unimi.mobidev.onderoad.R;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    private LoginButton loginButton;
    private Intent loginIntent;
    private CallbackManager cm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.nameToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setIcon(getDrawable(R.drawable.ic_beach_access_black_24dp));

        cm = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.loginButton);

        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));

        loginButton.registerCallback(cm, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String name = "";

                System.out.println("In onSuccess...");

                Profile user = Profile.getCurrentProfile();
                if (user != null) {
                    name = user.getName();

                    System.out.println("Facebook data: " + name);
                }

                loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                loginIntent.putExtra("User_name", name);
                startActivity(loginIntent);
            }

            @Override
            public void onCancel() {
                System.out.println("In onCancel...");
            }

            @Override
            public void onError(FacebookException exception) {
                System.out.println("In onError...");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        cm.onActivityResult(requestCode, resultCode, data);
    }
}
