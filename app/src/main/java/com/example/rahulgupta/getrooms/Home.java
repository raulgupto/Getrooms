package com.example.rahulgupta.getrooms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    EditText usernameid,ageid;
    TextView welcomeid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        usernameid=(EditText)findViewById(R.id.usernameid);
        ageid=(EditText)findViewById(R.id.ageid);
        welcomeid=(TextView)findViewById(R.id.welcomeid);

        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        int age=intent.getIntExtra("age",1);
        String username=intent.getStringExtra("username");


        String message=name+" Welcome to your Homepage";
        welcomeid.setText(message);
        usernameid.setText(username);
        ageid.setText(age+"");

    }
}
