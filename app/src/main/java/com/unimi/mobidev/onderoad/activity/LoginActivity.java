package com.unimi.mobidev.onderoad.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.unimi.mobidev.onderoad.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private Intent loginIntent;
    private CallbackManager cm = null;
    private SharedPreferences appData;

    private Bundle userInfo;

    private AccessToken currentToken;

    @TargetApi(21)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_login);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            int a = 0;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, a);

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.nameToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_beach_access_black_24px, null));

        cm = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.loginButton);

        if (AccessToken.getCurrentAccessToken() == null) {
            loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));

            loginButton.registerCallback(cm, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    System.out.println("In onSuccess...");
                    appData = getSharedPreferences("UserData", Context.MODE_PRIVATE);

                    currentToken = loginResult.getAccessToken();

                    GraphRequest request = GraphRequest.newMeRequest(currentToken, userCallBackInfo);
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "first_name,middle_name,last_name,email,id");
                    request.setParameters(parameters);
                    request.executeAsync();

                    loginIntent = new Intent(getApplicationContext(), MainActivity.class);
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
        } else {
            appData = getSharedPreferences("UserData", Context.MODE_PRIVATE);

            GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), userCallBackInfo);
            Bundle parameters = new Bundle();
            parameters.putString("fields", "first_name,middle_name,last_name,email,id");
            request.setParameters(parameters);
            request.executeAsync();

            loginIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(loginIntent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        cm.onActivityResult(requestCode, resultCode, data);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    System.out.println("In onRequestPermissionsResult...");
                    System.out.println("Permission Granted!");


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private GraphRequest.GraphJSONObjectCallback userCallBackInfo = new GraphRequest.GraphJSONObjectCallback() {
        @Override
        public void onCompleted(JSONObject object, GraphResponse response) {
            userInfo = new Bundle();
            SharedPreferences.Editor editor = appData.edit();

            try {
                String first_name = object.getString("first_name");
                String middle_name = object.getString("middle_name");
                String last_name = object.getString("last_name");
                String ID = object.getString("id");
                String email = object.getString("email");

                System.out.println("User info:" +
                        "\nFirst name: " + first_name +
                        "\nMiddle name: " + middle_name +
                        "\nLast name: " + last_name +
                        "\nEmail: " + email +
                        "\nID: " + ID);

                editor.putString("First name", first_name);
                editor.putString("Middle name", middle_name);
                editor.putString("Last name", last_name);
                editor.putString("Email", email);
                editor.putString("ID", ID);
                editor.apply();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
