package com.example.rahulgupta.getrooms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class searchactivity extends AppCompatActivity {
    Spinner roomtypeid;
    ArrayAdapter roomtype;
    EditText locationid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchactivity);
        locationid = (EditText) findViewById(R.id.locationid);
        roomtypeid = (Spinner) findViewById(R.id.roomtypeid);
        roomtype = ArrayAdapter.createFromResource(this, R.array.roomtype, android.R.layout.simple_spinner_item);
        roomtypeid.setAdapter(roomtype);

        roomtypeid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //onSelect spinner
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
