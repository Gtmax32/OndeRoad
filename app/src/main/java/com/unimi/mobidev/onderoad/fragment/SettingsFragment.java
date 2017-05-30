package com.unimi.mobidev.onderoad.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.unimi.mobidev.onderoad.R;

public class SettingsFragment extends Fragment{

    private TextView nameTextView;
    private TextView surnameTextView;
    private TextView emailTextView;

    private CheckBox firstSetting;
    private CheckBox secondSetting;
    private CheckBox thirdSetting;

    private SharedPreferences appData;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String first_name = "", middle_name = "", last_name = "", email = "";
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        appData = this.getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);

        nameTextView = (TextView) v.findViewById(R.id.userNameData);
        nameTextView.setText(appData.getString("First name", first_name) + " " + appData.getString("Middle name", middle_name));

        surnameTextView = (TextView) v.findViewById(R.id.userSurnameData);
        surnameTextView.setText(appData.getString("Last name", last_name));

        emailTextView = (TextView) v.findViewById(R.id.userMailData);
        emailTextView.setText(appData.getString("Email", email));

        firstSetting = (CheckBox) v.findViewById(R.id.firstSetting);
        firstSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsFragment.this.getContext(),"First Setting Checkbox clicked!",Toast.LENGTH_SHORT).show();
            }
        });

        secondSetting = (CheckBox) v.findViewById(R.id.secondSetting);
        secondSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsFragment.this.getContext(),"Second Setting Checkbox clicked!",Toast.LENGTH_SHORT).show();
            }
        });

        thirdSetting = (CheckBox) v.findViewById(R.id.thirdSetting);
        thirdSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsFragment.this.getContext(),"Third Setting Checkbox clicked!",Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
