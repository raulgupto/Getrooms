package com.example.rahulgupta.getrooms;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity {

    TextInputLayout emaillayout, datelayout,namelayout,usernamelayout,passwordlayout,contactnumberlayout;
    EditText passwordid,nameid,emailid,contactnumberid,usernameid;
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
        //ageid=(EditText)findViewById(R.id.ageid);

        emaillayout=(TextInputLayout)findViewById(R.id.emaillayout);
        namelayout=(TextInputLayout)findViewById(R.id.namelayout);
        usernamelayout=(TextInputLayout)findViewById(R.id.usernamelayout);
        datelayout=(TextInputLayout)findViewById(R.id.datelayout);
        passwordlayout=(TextInputLayout)findViewById(R.id.passwordlayout);
       contactnumberlayout =(TextInputLayout)findViewById(R.id.contactnumberlayout);

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
//                if (gender.equals("SELECT GENDER")) {
//
//                } else {
//                    Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_LONG).show();
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        categoryspinnerid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //code here
               // Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_LONG).show();
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });



        submitbuttonid=(Button)findViewById(R.id.submitbuttonid);

        submitbuttonid.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                final String name = nameid.getText().toString();
                String password = passwordid.getText().toString();
                String username = usernameid.getText().toString();
                final String email=emailid.getText().toString();
                final String contactnumber = contactnumberid.getText().toString();
                String gender = genderspinnerid.getSelectedItem().toString();
                String category = categoryspinnerid.getSelectedItem().toString();
                String dateofbirth = txtDate.getText().toString();



                if (name.equals("")) {
                    namelayout.setError(getString(R.string.emptyfield));
                    requestFocus(nameid);
                    Toast.makeText(getApplicationContext(), "field cannot be empty", Toast.LENGTH_SHORT).show();

                }
                else if(username.equals("")){
                    usernamelayout.setError(getString(R.string.emptyfield));
                    requestFocus(usernameid);
                    Toast.makeText(getApplicationContext(), "field cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if(email.equals("")||!isEmailValid(email)){
                    emaillayout.setError(getString(R.string.erroremail));
                    requestFocus(emailid);
//                    Toast.makeText(getApplicationContext(), "field cannot be empty", Toast.LENGTH_SHORT).show();

                }
                else if (password.equals("")||(!isPasswordValid(password))){
                    passwordlayout.setError(getString(R.string.errorpassword));

                    requestFocus(passwordid);
//                    Toast.makeText(getApplicationContext(), "field cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if (contactnumber.equals("")){

                   contactnumberlayout.setError(getString(R.string.emptyfield));
                    requestFocus(nameid);
                }


                else  if(dateofbirth.equals("")){
                    datelayout.setError(getString(R.string.emptyfield));

                    requestFocus(txtDate);
//                    Toast.makeText(getApplicationContext(), "field cannot be empty", Toast.LENGTH_SHORT).show();
                }

                else if(gender.equals("SELECT GENDER"))
                {
                    Toast.makeText(getApplicationContext(), "Select gender", Toast.LENGTH_SHORT).show();
                }
//                else if(){
////                    emaillayout.setError(getString(R.string.email));
//                    requestFocus(emailid);
//                    Toast.makeText(getApplicationContext(), "Enter Valid email ", Toast.LENGTH_SHORT).show();
//                }
//                else if ()){
////                    passwordlayout.setError(getString(R.string.errorpassword));
////                    requestFocus(passwordid);
//                    Toast.makeText(getApplicationContext(), "Password format 8char-long-A-a-@..$", Toast.LENGTH_SHORT).show();
//                }
                else {

                    Response.Listener<String> responseListener = new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    Intent intent = new Intent(signup.this, MainActivity.class);
                                    Toast.makeText(getApplicationContext(), "SignUP sucessful! Now login", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(signup.this);
                                    builder.setMessage("Register Failed")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    RegisterRequest registerRequest = new RegisterRequest(name, username, password, contactnumber, gender, dateofbirth,email, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(signup.this);
                    queue.add(registerRequest);
                }
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



    public boolean isEmailValid(String email)
    {
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        final Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isPasswordValid(String password)
    {
        //8-char,Uppercase,Lowercase,Special char
        //"^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}"
        //8-char ,alpha
        //"^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$"
        final String PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        final Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
