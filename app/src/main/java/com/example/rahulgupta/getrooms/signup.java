package com.example.rahulgupta.getrooms;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class signup extends AppCompatActivity implements View.OnClickListener{



    EditText passwordid,nameid,emailid,contactnuberid,usernameid,ageid;
    Spinner genderspinnerid;
    Button setdateid,submitbuttonid;
    Spinner categoryspinnerid;
    ArrayAdapter gender,category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        passwordid=(EditText)findViewById(R.id.passwordid);
        contactnuberid=(EditText)findViewById(R.id.contactnumberid);
        emailid=(EditText)findViewById(R.id.emailid);
        nameid=(EditText)findViewById(R.id.nameid);
        usernameid=(EditText)findViewById(R.id.usernameid);
        ageid=(EditText)findViewById(R.id.ageid);

        setdateid=(Button)findViewById(R.id.setdateid);

        genderspinnerid=(Spinner)findViewById(R.id.genderspinnerid);
        gender= ArrayAdapter.createFromResource(this, R.array.genderarray, android.R.layout.simple_spinner_item);
        gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderspinnerid.setAdapter(gender);
        categoryspinnerid=(Spinner)findViewById(R.id.categoryspinnerid);
        category=ArrayAdapter.createFromResource(this,R.array.categoryarray,android.R.layout.simple_spinner_item);
        category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryspinnerid.setAdapter(category);

        submitbuttonid=(Button)findViewById(R.id.submitbuttonid);
        submitbuttonid.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String name=nameid.getText().toString();
                String password=passwordid.getText().toString();
                String username=usernameid.getText().toString();
                int age=Integer.parseInt(ageid.getText().toString());
                String contactnumber=contactnuberid.getText().toString();

                Response.Listener<String> responseListener=new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse=new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");
                            if (success){
                                Intent intent=new Intent(signup.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder= new AlertDialog.Builder(signup.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest=new RegisterRequest(name,username,age,password,contactnumber,responseListener);
                RequestQueue queue= Volley.newRequestQueue(signup.this);
                queue.add(registerRequest);
            }
        });


        genderspinnerid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        categoryspinnerid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_LONG).show();
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }
    public void setDate(View view){
        PickerDialogs pickerDialogs=new PickerDialogs();
        pickerDialogs.show(getFragmentManager(),"Select Date");
    }



    @Override
    public void onClick(View v) {


    }

}
