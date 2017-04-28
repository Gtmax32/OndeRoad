package com.unimi.mobidev.onderoad.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TimePicker;

import com.unimi.mobidev.onderoad.R;

import java.util.Calendar;
import java.util.Locale;

public class TimePickerModification extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public TimePickerModification() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Button timeButton = (Button) getActivity().findViewById(R.id.modifyTimeButton);
        String time = timeButton.getText().toString();
        String splitTime[] = time.split(":");

        int hour = Integer.parseInt(splitTime[0]);
        int minute = Integer.parseInt(splitTime[1]);

        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Button timeButton = (Button) getActivity().findViewById(R.id.modifyTimeButton);
        String time;

        time = String.format(Locale.ITALIAN, "%02d", hourOfDay) + ":" + String.format(Locale.ITALIAN, "%02d", minute);
        timeButton.setText(time);
    }


}
