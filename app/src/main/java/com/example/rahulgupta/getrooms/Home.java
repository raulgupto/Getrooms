package com.example.rahulgupta.getrooms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    TextView usernameid,genderid;
    TextView welcomeid,messid,sharedid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        usernameid=(TextView) findViewById(R.id.usernameid);
        genderid=(TextView) findViewById(R.id.genderid);
        welcomeid=(TextView)findViewById(R.id.welcomeid);
        messid=(TextView)findViewById(R.id.messid);
        sharedid=(TextView)findViewById(R.id.sharedid);

        Intent intent=getIntent();
        String price=intent.getStringExtra("price");
        String shared=intent.getStringExtra("shared");
        String location=intent.getStringExtra("location");
        String laundry=intent.getStringExtra("laundry");
        String mess=intent.getStringExtra("mess");
        ///int age=intent.getIntExtra("age",1);


        String message="Rooms Found with Details ";
        String m1="Shared as:"+shared;
        welcomeid.setText(message);
        usernameid.setText(price);
        genderid.setText(location);
        sharedid.setText(m1);
        String m2="Mess available:"+mess;
        messid.setText(m2);


        //ageid.setText(age+"");

    }
}
