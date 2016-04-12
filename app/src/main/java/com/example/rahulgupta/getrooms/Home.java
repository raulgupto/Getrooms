package com.example.rahulgupta.getrooms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        Bundle intent=getIntent().getExtras();
        if (intent==null){
            return;

        }
        String name=intent.getString("name");
        int age=intent.getInt("age", 1);
        String username=intent.getString("username");


        final String message=name+" Welcome to your Homepage";
        welcomeid.setText(message);
        usernameid.setText(username);
        ageid.setText(age+"");

    }
}
