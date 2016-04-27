package com.example.rahulgupta.getrooms;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {


    EditText usernameid, passwordid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button loginbutton = (Button) findViewById(R.id.loginbutton);
        final Button signupbutton = (Button) findViewById(R.id.signupbutton);
        final EditText passwordid = (EditText) findViewById(R.id.passwordid);
        final EditText usernameid = (EditText) findViewById(R.id.usernameid);


        if (signupbutton != null) {
            signupbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent signupintent = new Intent(MainActivity.this, signup.class);
                    MainActivity.this.startActivity(signupintent);

                }
            });
        }


        if (loginbutton != null) {
            loginbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String username=usernameid.getText().toString();
                    String password=passwordid.getText().toString();

                    Response.Listener<String> responselistener=new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response){

                            try {
                                JSONObject jsonResponse=new JSONObject(response);
                                boolean success=jsonResponse.getBoolean("success");
                                if (success){

                                    String name=jsonResponse.getString("name");
                                    String gender=jsonResponse.getString("gender");
                                    String contactnumber=jsonResponse.getString("contactnumber");
                                    String dateofbirth=jsonResponse.getString("dateofbirth");
                                    String email=jsonResponse.getString("email");
                                    //int age=jsonResponse.getInt("age");

                                    Intent intent=new Intent(MainActivity.this,Home.class);
                                    intent.putExtra("name",name);
                                    intent.putExtra("username",username);
                                   // intent.putExtra("age", age);
                                    intent.putExtra("gender",gender);
                                    intent.putExtra("contactnumber",contactnumber);
                                    intent.putExtra("dateofbirth",dateofbirth);
                                    intent.putExtra("email",email);
                                    MainActivity.this.startActivity(intent);

                                }
                                else{
                                    AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                                    builder.setMessage("Login Failed")
                                            .setNegativeButton("Retry",null)
                                            .create()
                                            .show();


                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };

                    LoginRequest loginRequest=new LoginRequest(username,password,responselistener);

                    RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
                    queue.add(loginRequest);


                }
            });
        }

    }


    private boolean doubleBackToExitPressedOnce;
    private Handler mHandler = new Handler();

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if (mHandler != null) { mHandler.removeCallbacks(mRunnable); }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        mHandler.postDelayed(mRunnable, 2000);
    }
}
