package com.unimi.mobidev.onderoad.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.unimi.mobidev.onderoad.R;
import com.unimi.mobidev.onderoad.model.TravelInfo;
import com.unimi.mobidev.onderoad.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TravelInfoActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int CREATE_ACTIVITY_REQUEST = 2;
    private TravelInfo travelDisplayed;
    private String travelDisplayedKey;

    private ImageView passengerDriverImage;

    private TextView passengersActualInfo;
    private TextView priceActualInfo;
    private TextView surfboardActualInfo;

    private TextView dateTimeActualInfo;
    private TextView priceTotalInfo;
    private TextView carSupportActualInfo;

    private EditText noteActualText;

    private boolean isOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.travelInfoToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.info_travel);

        //Serve per inserire la freccia per tornare indietro
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        travelDisplayed = (TravelInfo) this.getIntent().getSerializableExtra("TravelInfo");
        travelDisplayedKey = (String) this.getIntent().getSerializableExtra("TravelKey");

        System.out.println("In TravelInfoActivity: " + travelDisplayed.toString());

        isOwner = FirebaseAuth.getInstance().getCurrentUser().getUid().equals(travelDisplayed.getOwnerTravel().getIdUser());

        passengerDriverImage = (ImageView) findViewById(R.id.carInfoImage);

        if (isOwner)
            passengerDriverImage.setImageResource(R.drawable.ic_action_driver);
        else
            passengerDriverImage.setImageResource(R.drawable.ic_action_passenger);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.actualTravelMap);
        mapFragment.getMapAsync(this);

        int passengersNumber;

        if (travelDisplayed.getPassengersTravel() == null)
            passengersNumber = 0;
        else
            passengersNumber = travelDisplayed.getPassengersTravel().size();

        float actualPrice = travelDisplayed.getPriceTravel() / (passengersNumber + 1);
        String temp = travelDisplayed.formatDataDeparture() + " - " + travelDisplayed.formatTimeDeparture();

        passengersActualInfo = (TextView) findViewById(R.id.passengersFractionDataText);
        priceActualInfo = (TextView) findViewById(R.id.actualPriceDataText);
        surfboardActualInfo = (TextView) findViewById(R.id.surfboardFractionData);

        dateTimeActualInfo = (TextView) findViewById(R.id.actualDateTravel);
        dateTimeActualInfo.setText(temp);

        priceTotalInfo = (TextView) findViewById(R.id.totalPriceDataInfo);
        carSupportActualInfo = (TextView) findViewById(R.id.carSupportDataInfo);

        noteActualText = (EditText) findViewById(R.id.actualNoteText);
        noteActualText.setKeyListener(null);

        passengersActualInfo.setText(passengersNumber + "/" + travelDisplayed.getCarTravel().getPassengersNumber());

        temp = actualPrice + " €";

        priceActualInfo.setText(temp);

        temp = travelDisplayed.getCarTravel().getSurfboardNumber() + "";

        surfboardActualInfo.setText(temp);

        temp = travelDisplayed.getPriceTravel() + " €";

        priceTotalInfo.setText(temp);

        carSupportActualInfo.setText(travelDisplayed.getCarTravel().getSurfboardType());

        noteActualText.setText(travelDisplayed.getNoteTravel());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        double destinationLatitude = travelDisplayed.getSpotDestination().getLatitudeSpot();
        double destinationLongitude = travelDisplayed.getSpotDestination().getLongitudeSpot();

        Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(destinationLatitude, destinationLongitude)).title(travelDisplayed.getSpotDestination().getTitle()));
        marker.showInfoWindow();

        //TODO: Controllare il valore dello zoom
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(destinationLatitude, destinationLongitude), 14.0f));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (isOwner)
            inflater.inflate(R.menu.travel_info_driver_menu, menu);
        else {
            String key = FirebaseAuth.getInstance().getCurrentUser().getUid();
            boolean condition = !(travelDisplayed.isPassenger(key) && travelDisplayed.isFull());
            inflater.inflate(R.menu.travel_info_passenger_menu, menu);
            menu.findItem(R.id.addSubMenu).setEnabled(condition);
            menu.findItem(R.id.addSubMenu).setVisible(condition);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.modifySubMenu:
                System.out.println("Opening modification menu...");
                Toast.makeText(this.getApplicationContext(), "Cooming soon!", Toast.LENGTH_SHORT).show();
                //modifyTravel();
                return true;

            case R.id.spotMapSubMenu:
                System.out.println("Opening navigation menu...");
                showSpotMap(travelDisplayed.getSpotDestination().getLatitudeSpot(), travelDisplayed.getSpotDestination().getLongitudeSpot(), travelDisplayed.getSpotDestination().getTitle());
                return true;

            case R.id.deleteSubMenu:
                System.out.println("Deleting current travel...");

                AlertDialog.Builder builderDelete = new AlertDialog.Builder(this);
                builderDelete.setMessage(R.string.deleting_travel_message)
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteTravel();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDelete = builderDelete.create();
                alertDelete.show();

                return true;

            case R.id.shareSubMenu:
                System.out.println("Opening sharing menu...");
                shareTravel();
                return true;

            case R.id.passContactSubMenu:
                sendPassengersEmail(travelDisplayed.getPassengersTravel());
                return true;

            case R.id.addSubMenu:
                System.out.println("Adding a passengers...");

                AlertDialog.Builder builderAdd = new AlertDialog.Builder(this);
                builderAdd.setMessage(R.string.adding_like_passenger_message)
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                addPassenger();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertAdd = builderAdd.create();
                alertAdd.show();

                return true;

            case R.id.startMapSubMenu:
                showSpotMap(travelDisplayed.getAddressDeparture().getLatitudeInfo(), travelDisplayed.getAddressDeparture().getLongitudeInfo(), travelDisplayed.getAddressDeparture().getStreetInfo());
                return true;

            case R.id.driverContactSubMenu:
                sendDriverEmail();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void showSpotMap(double latitude, double longitude, String text) {
        String toParse = "geo:0,0?q= " + latitude + "," + longitude + "(" + text + ")";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(toParse));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void deleteTravel() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("travels").child(travelDisplayedKey);
        ref.removeValue();

        Toast.makeText(getApplicationContext(),"Il viaggio verso " + travelDisplayed.getSpotDestination().getTitle() + " è stato cancellato.",Toast.LENGTH_SHORT).show();
        finish();
    }

    private void shareTravel() {
        Uri traveDeepLink = buildDeepLink(Uri.parse(getResources().getString(R.string.app_deeplink) + travelDisplayedKey));
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Vieni a surfare?\n" + traveDeepLink);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void modifyTravel() {
        Intent modifyIntent = new Intent(this.getApplication(), ModifyActivity.class);
        modifyIntent.putExtra("TravelInfo", travelDisplayed);
        startActivityForResult(modifyIntent, CREATE_ACTIVITY_REQUEST);
    }

    public void sendPassengersEmail(ArrayList<User> userList) {
        if (userList == null)
            Toast.makeText(getApplicationContext(), R.string.mail_error_message, Toast.LENGTH_SHORT).show();
        else{
            int passengersNumber = userList.size();
            int i = 0;

            String[] addresses = new String[passengersNumber];

            for (User u : userList){
                addresses[i] = u.getEmailUser();
                i++;
            }

            Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
            mailIntent.setData(Uri.parse("mailto:"));
            mailIntent.putExtra(Intent.EXTRA_EMAIL, addresses);
            mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Comunicazione");
            if (mailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mailIntent);
            }
        }
    }

    private void addPassenger() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        User passenger = new User(currentUser.getUid(), currentUser.getDisplayName(), currentUser.getEmail());

        if(travelDisplayed.getPassengersTravel() == null){
            ArrayList<User> passengerList = new ArrayList<>();
            passengerList.add(passenger);
            this.travelDisplayed.setPassengersTravel(passengerList);
        }else{

            this.travelDisplayed.getPassengersTravel().add(passenger);
        }

        Map<String,Object> dataToUpdate = new HashMap<>();
        dataToUpdate.put(this.travelDisplayedKey,this.travelDisplayed);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("travels");
        ref.updateChildren(dataToUpdate);

        Toast.makeText(getApplicationContext(),getString(R.string.passengers_added_perfectly_message) + " " + travelDisplayed.getSpotDestination().getTitle() + ".",Toast.LENGTH_SHORT).show();

        invalidateOptionsMenu();

        this.recreate();
    }

    private void sendDriverEmail() {
        User owner = this.travelDisplayed.getOwnerTravel();

        Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
        mailIntent.setData(Uri.parse("mailto:" + owner.getEmailUser()));
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Comunicazione");
        if (mailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mailIntent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (requestCode == CREATE_ACTIVITY_REQUEST) {
            if (resultCode == RESULT_OK) {
                travelDisplayed = (TravelInfo) data.getExtras().get("TravelInfo");
                System.out.println("Receiving data from ModifyActivity..." + travelDisplayed.toString());

                finish();
                this.getIntent().putExtra("TravelInfo", travelDisplayed);
                startActivity(this.getIntent());

            }
        }
        else*/
        /*if (requestCode == INVITATION_REQUEST) {
            if (resultCode == RESULT_OK) {
                System.out.println("Invitation succesfull");
            }
        }*/
    }

    public Uri buildDeepLink(Uri deepLink) {
        // Get this app's package name.
        String packageName = getApplicationContext().getPackageName();

        // Build the link with all required parameters
        Uri.Builder builder = new Uri.Builder()
                .scheme("https")
                .authority(getResources().getString(R.string.unique_app_code) + ".app.goo.gl")
                .path("/")
                .appendQueryParameter("link", deepLink.toString())
                .appendQueryParameter("apn", packageName);

        // Return the completed deep link.
        return builder.build();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
