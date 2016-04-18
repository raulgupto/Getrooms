package com.example.rahulgupta.getrooms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    EditText usernameid,genderid;
    TextView welcomeid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        usernameid=(EditText)findViewById(R.id.usernameid);
        genderid=(EditText)findViewById(R.id.genderid);
        welcomeid=(TextView)findViewById(R.id.welcomeid);

        Intent intent=getIntent();
        String price=intent.getStringExtra("price");
        String shared=intent.getStringExtra("shared");
        String location=intent.getStringExtra("location");
        String laundry=intent.getStringExtra("laundry");
        String mess=intent.getStringExtra("mess");
        ///int age=intent.getIntExtra("age",1);


        String message="Rooms Found with Details ";
        welcomeid.setText(message);
        usernameid.setText(price);
        genderid.setText(location);
        //ageid.setText(age+"");

    }
}
