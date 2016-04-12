package com.example.rahulgupta.getrooms;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.Toast;

/**
 * Created by Rahul Gupta on 31-03-2016.
 */
public class DateSettings implements DatePickerDialog.OnDateSetListener {
    Context context;
    public DateSettings(Context context){
        this.context=context;
    }
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(context,"Selected Date: "+dayOfMonth+" / "+monthOfYear+" / "+year,Toast.LENGTH_LONG).show();

    }
}
