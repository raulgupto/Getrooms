package com.example.rahulgupta.getrooms;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class signup extends AppCompatActivity implements View.OnClickListener{



    EditText passwordid,nameid,emailid,contactnumberid,usernameid,ageid;
    EditText txtDate;
    Spinner genderspinnerid;
    Button submitbuttonid;
    Spinner categoryspinnerid;
    ArrayAdapter gender,category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        passwordid=(EditText)findViewById(R.id.passwordid);
        contactnumberid=(EditText)findViewById(R.id.contactnumberid);
        emailid=(EditText)findViewById(R.id.emailid);
        nameid=(EditText)findViewById(R.id.nameid);
        usernameid=(EditText)findViewById(R.id.usernameid);
        ageid=(EditText)findViewById(R.id.ageid);

        genderspinnerid=(Spinner)findViewById(R.id.genderspinnerid);
        gender= ArrayAdapter.createFromResource(this, R.array.genderarray, android.R.layout.simple_spinner_item);
        gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderspinnerid.setAdapter(gender);
        categoryspinnerid=(Spinner)findViewById(R.id.categoryspinnerid);
        category=ArrayAdapter.createFromResource(this,R.array.categoryarray,android.R.layout.simple_spinner_item);
        category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryspinnerid.setAdapter(category);




        genderspinnerid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //code here
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        categoryspinnerid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //code here
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_LONG).show();
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });



        submitbuttonid=(Button)findViewById(R.id.submitbuttonid);
        submitbuttonid.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String name=nameid.getText().toString();
                String password=passwordid.getText().toString();
                String username=usernameid.getText().toString();

                final String contactnumber=contactnumberid.getText().toString();
                String gender=genderspinnerid.getSelectedItem().toString();
                String category=categoryspinnerid.getSelectedItem().toString();
                String dateofbirth=txtDate.getText().toString();


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
                RegisterRequest registerRequest=new RegisterRequest(name,username,password,contactnumber,gender,dateofbirth,responseListener);
                RequestQueue queue= Volley.newRequestQueue(signup.this);
                queue.add(registerRequest);
            }
        });



    }


    public void onStart(){
        super.onStart();


        txtDate = (EditText)findViewById(R.id.setdateid);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View view, boolean hasfocus){
                if(hasfocus){
                    DateDialog dialog=new DateDialog(view);
                    FragmentTransaction ft =getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");

                }
            }

        });
    }


    @Override
    public void onClick(View v) {


    }

}
