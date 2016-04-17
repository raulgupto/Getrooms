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
        String name=intent.getStringExtra("name");
        String gender=intent.getStringExtra("gender");
        ///int age=intent.getIntExtra("age",1);
        String username=intent.getStringExtra("username");


        String message=name+" Welcome to your Homepage";
        welcomeid.setText(message);
        usernameid.setText(username);
        genderid.setText(gender);
        //ageid.setText(age+"");

    }
}
