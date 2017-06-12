package com.unimi.mobidev.onderoad.firebase;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.unimi.mobidev.onderoad.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Giuseppe Fabio Trentadue on 09/06/2017.
 */

public class FirebaseTokenManager extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        System.out.println("Refreshed token: " + refreshedToken);

        //sendRegistrationToServer(refreshedToken);
    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        FirebaseUser currentUser = FirebaseUtils.getCurrentUser();
        User newUser = new User(currentUser.getUid(),currentUser.getDisplayName(),currentUser.getEmail(),token);

        Map<String,Object> dataToUpdate = new HashMap<>();
        dataToUpdate.put(currentUser.getUid(),newUser);

        FirebaseUtils.getDatabaseReference("users").updateChildren(dataToUpdate);
    }

}
