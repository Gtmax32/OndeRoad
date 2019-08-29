package com.unimi.mobidev.onderoad.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import com.unimi.mobidev.onderoad.R;

import java.util.Calendar;
import java.util.Locale;

public class TimePickerCreation extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public TimePickerCreation() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Button timeButton = getActivity().findViewById(R.id.timeButton);
        String time;

        time = String.format(Locale.ITALIAN, "%02d", hourOfDay) + ":" + String.format(Locale.ITALIAN, "%02d", minute);
        timeButton.setText(time);
    }


}
