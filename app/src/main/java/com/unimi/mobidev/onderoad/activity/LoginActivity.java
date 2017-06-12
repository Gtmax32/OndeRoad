package com.unimi.mobidev.onderoad.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.firebase.FirebaseUtils;
import com.unimi.mobidev.onderoad.model.User;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private Intent loginIntent;
    private CallbackManager cm = null;

    private ProgressDialog authenticationProgressDialog;

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseUtils.getCurrentUser() != null) {
            System.out.println("Utente già autenticato!");
            loginIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(loginIntent);
        }

    }

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

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {

                Toast.makeText(this, "I permessi sono necessari per migliorare\nl'efficienza dell'applicazione!", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, a);

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, a);
            }

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.nameToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        // TODO: Inserire l'icona dell'applicazione
        getSupportActionBar().setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_beach_access_black_24px, null));

        authenticationProgressDialog = new ProgressDialog(this);
        authenticationProgressDialog.setMessage(getApplicationContext().getResources().getString(R.string.login_progress_message));
        authenticationProgressDialog.setCancelable(false);

        cm = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.loginButton);

        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));

        loginButton.registerCallback(cm, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                authenticationProgressDialog.show();
                handleFacebookAccessToken(loginResult.getAccessToken());

                /*System.out.println("In onSuccess...");
                appData = getSharedPreferences("UserData", Context.MODE_PRIVATE);

                currentToken = loginResult.getAccessToken();

                AuthCredential credential = FacebookAuthProvider.getCredential(currentToken.getToken());

                mAuth.signInWithCredential(credential).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            System.out.println("Name: " + user.getDisplayName() + "\nEmail" + user.getEmail() + "\nEverything: " + user.toString());
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                GraphRequest request = GraphRequest.newMeRequest(currentToken, userCallBackInfo);
                Bundle parameters = new Bundle();
                parameters.putString("fields", "first_name,middle_name,last_name,email,id");
                request.setParameters(parameters);
                request.executeAsync();

                loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(loginIntent);*/
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
        /*} else {
            appData = getSharedPreferences("UserData", Context.MODE_PRIVATE);

            GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), userCallBackInfo);
            Bundle parameters = new Bundle();
            parameters.putString("fields", "first_name,middle_name,last_name,email,id");
            request.setParameters(parameters);
            request.executeAsync();

            loginIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(loginIntent);
        }*/
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        FirebaseUtils.getAuth().signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            updateUI(FirebaseUtils.getCurrentUser());
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            System.out.println("ID: " + user.getUid() + "\nName: " + user.getDisplayName() + "\nEmail: " + user.getEmail() + "\nToken: " + FirebaseUtils.getFirebaseToken() + "\nEverything: " + user.toString());

            User current = new User(user.getUid(), user.getDisplayName(), user.getEmail(),FirebaseUtils.getFirebaseToken());

            FirebaseUtils.getDatabaseReference("users").child(user.getUid()).setValue(current);

            authenticationProgressDialog.hide();
            loginIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(loginIntent);
        } else {
            Toast.makeText(getApplicationContext(), "Errore nella registrazione dell'utente!", Toast.LENGTH_SHORT).show();
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
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    /*private GraphRequest.GraphJSONObjectCallback userCallBackInfo = new GraphRequest.GraphJSONObjectCallback() {
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
    };*/
}
