package com.unimi.mobidev.onderoad.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;

import com.unimi.mobidev.onderoad.R;

import java.util.Calendar;
import java.util.Locale;

public class DateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public DateFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Button dateButton = (Button) getActivity().findViewById(R.id.dateButton);
        String date;

        Calendar c = Calendar.getInstance();
        c.set(year,monthOfYear,dayOfMonth);

        date = dayOfMonth + " " + c.getDisplayName(Calendar.MONTH,Calendar.LONG,Locale.ITALIAN);
        dateButton.setText(date);
    }
}
