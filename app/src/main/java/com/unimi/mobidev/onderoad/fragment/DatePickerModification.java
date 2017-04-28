package com.unimi.mobidev.onderoad.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;

import com.unimi.mobidev.onderoad.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DatePickerModification extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public DatePickerModification() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();

        Button dateButton = (Button) getActivity().findViewById(R.id.modifyDateButton);
        String splitDate[] = dateButton.getText().toString().split(" ");

        int day = Integer.parseInt(splitDate[0]);

        int year = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH);

        //Se ci troviamo a dicembre, ma la data del viaggio corrisponde a gennaio,
        //vuol dire che l'anno del viaggio è successivo all'anno corrente
        if (currentMonth == Calendar.DECEMBER && splitDate[1].equals("gennaio")) {
            year++;
        }

        try {
            c.setTime(new SimpleDateFormat("MMMM", Locale.ITALIAN).parse(splitDate[1]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int month = c.get(Calendar.MONTH);

        c.set(year,month,day);

        c.add(Calendar.DAY_OF_YEAR, -1);
        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        dialog.getDatePicker().setMinDate(c.getTimeInMillis());
        c.add(Calendar.DAY_OF_YEAR, 2);
        dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        return dialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Button dateButton = (Button) getActivity().findViewById(R.id.modifyDateButton);
        String date;

        Calendar c = Calendar.getInstance();
        c.set(year, monthOfYear, dayOfMonth);

        date = dayOfMonth + " " + c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ITALIAN);
        dateButton.setText(date);
    }
}
