package com.unimi.mobidev.onderoad.firebase;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.iid.FirebaseInstanceId;
import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.model.TravelInfo;
import com.unimi.mobidev.onderoad.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Giuseppe Fabio Trentadue on 09/06/2017.
 */

public class FirebaseUtils {
    public static FirebaseAuth getAuth(){
        return FirebaseAuth.getInstance();
    }

    public static FirebaseUser getCurrentUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public static DatabaseReference getDatabaseReference(String category){
        return FirebaseDatabase.getInstance().getReference().child(category);
    }

    public static String getFirebaseToken(){
        return FirebaseInstanceId.getInstance().getToken();
    }

    public static void addPassenger(TravelInfo travelDisplayed, String travelDisplayedKey){
        FirebaseUser currentUser = FirebaseUtils.getCurrentUser();
        final User passenger = new User(currentUser.getUid(), currentUser.getDisplayName(), currentUser.getEmail(),FirebaseUtils.getFirebaseToken());

        FirebaseUtils.getDatabaseReference("travels").child(travelDisplayedKey).runTransaction(new Transaction.Handler(){
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                TravelInfo t = mutableData.getValue(TravelInfo.class);

                if(t == null){
                    return Transaction.success(mutableData);
                }

                if(t.getPassengersTravel() == null){
                    ArrayList<User> passengerList = new ArrayList<>();
                    passengerList.add(passenger);
                    t.setPassengersTravel(passengerList);
                }else{

                    t.getPassengersTravel().add(passenger);
                }

                mutableData.setValue(t);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                // Transaction completed
                System.out.println("postTransaction:onComplete:" + databaseError);
            }
        });
    }

    public static void removePassenger(TravelInfo travelDisplayed, String travelDisplayedKey){
        FirebaseUser currentUser = FirebaseUtils.getCurrentUser();
        final User passenger = new User(currentUser.getUid(), currentUser.getDisplayName(), currentUser.getEmail(),FirebaseUtils.getFirebaseToken());

        FirebaseUtils.getDatabaseReference("travels").child(travelDisplayedKey).runTransaction(new Transaction.Handler(){
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                TravelInfo t = mutableData.getValue(TravelInfo.class);

                if(t == null){
                    return Transaction.success(mutableData);
                }

                ArrayList<User> passengerList = t.getPassengersTravel();
                if (passengerList.size() == 1){
                    passengerList.remove(0);
                    passengerList = null;
                }
                else{
                    FirebaseUser currentUser = FirebaseUtils.getCurrentUser();
                    User passenger = new User(currentUser.getUid(), currentUser.getDisplayName(), currentUser.getEmail(),FirebaseUtils.getFirebaseToken());
                    passengerList.remove(passenger);
                }

                t.setPassengersTravel(passengerList);

                mutableData.setValue(t);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                // Transaction completed
                System.out.println("postTransaction:onComplete:" + databaseError);
            }
        });
    }

    public static void deleteTravel(String travelDisplayedKey) {
        FirebaseUtils.getDatabaseReference("travels").child(travelDisplayedKey).runTransaction(new Transaction.Handler(){
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                TravelInfo t = mutableData.getValue(TravelInfo.class);

                if(t == null){
                    return Transaction.success(mutableData);
                }

                mutableData.setValue(null);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                // Transaction completed
                System.out.println("postTransaction:onComplete:" + databaseError);
            }
        });
    }

    public static void sendNotification(final Context context, boolean isToDriver, String[] tokens,String msg){
        String title = "Nuova Notifica Onderoad";

        JSONObject obj = null;
        JSONObject objData = null;
        JSONObject dataobjData = null;

        try {
            obj = new JSONObject();
            objData = new JSONObject();

            if(isToDriver){
                obj.put("to", tokens[0]);
                //obj.put("priority", "high");
            }
            else{
                JSONArray passengers = new JSONArray(tokens);

                obj.put("registration_ids", passengers);
                //obj.put("priority", "high");
            }

            objData.put("body", msg);
            objData.put("title", title);
            objData.put("priority", "high");

            dataobjData = new JSONObject();
            dataobjData.put("text", msg);
            dataobjData.put("title", title);

            obj.put("notification", objData);
            obj.put("data", dataobjData);
            Log.e("!_@rj@_@@_PASS:>", obj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, context.getString(R.string.notification_server_address), obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("!_@@_SUCESS", response + "");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("!_@@_Errors--", error + "");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "key=" + context.getString(R.string.notification_server_key));
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        int socketTimeout = 1000 * 60;// 60 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);
        requestQueue.add(jsObjRequest);
    }
}
