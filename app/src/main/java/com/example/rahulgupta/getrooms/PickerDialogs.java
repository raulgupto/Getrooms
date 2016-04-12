package com.example.rahulgupta.getrooms;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by Rahul Gupta on 31-03-2016.
 */
public class PickerDialogs extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DateSettings dateSettings=new DateSettings(getActivity());
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog;
        dialog=new DatePickerDialog(getActivity(),dateSettings,year,month,day);
        return dialog;
    }
}
