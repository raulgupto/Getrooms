package com.example.rahulgupta.getrooms;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

public class searchrooms extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Spinner roomtypeid,laundryid,messid,sharedid;
    ArrayAdapter roomtype,mess,laundry,shared;
    EditText locationid,priceid;
    Button searchid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchrooms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        priceid = (EditText) findViewById(R.id.priceid);
        locationid = (EditText) findViewById(R.id.locationid);
        roomtypeid = (Spinner) findViewById(R.id.roomtypeid);
        searchid=(Button)findViewById(R.id.searchid);
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

        messid = (Spinner) findViewById(R.id.messid);
        mess = ArrayAdapter.createFromResource(this, R.array.mess, android.R.layout.simple_spinner_item);
        messid.setAdapter(mess);

        messid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //onSelect spinner
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        laundryid = (Spinner) findViewById(R.id.laundryid);
        laundry = ArrayAdapter.createFromResource(this, R.array.laundry, android.R.layout.simple_spinner_item);
        laundryid.setAdapter(laundry);

        laundryid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //onSelect spinner
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sharedid = (Spinner) findViewById(R.id.sharedid);
        shared = ArrayAdapter.createFromResource(this, R.array.shared, android.R.layout.simple_spinner_item);
        sharedid.setAdapter(shared);

        sharedid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //onSelect spinner
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //searchbutton listener
        searchid.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final String roomtype=roomtypeid.getSelectedItem().toString();
                String location=locationid.getText().toString();
                final int price= Integer.parseInt(priceid.getText().toString());
                String laundry=laundryid.getSelectedItem().toString();
                String mess=messid.getSelectedItem().toString();
                String shared=sharedid.getSelectedItem().toString();



                Response.Listener<String> responselistener=new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){

                        try {
                            JSONObject jsonResponse=new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");
                            if (success){

                                int price=jsonResponse.getInt("price");
                                String shared=jsonResponse.getString("shared");
                                String location=jsonResponse.getString("location");
                                String laundry=jsonResponse.getString("laundry");
                                String mess=jsonResponse.getString("mess");


                                Intent intent=new Intent(searchrooms.this,Home.class);
                                intent.putExtra("price",price);
                                intent.putExtra("shared",shared);

                                intent.putExtra("location",location);
                                intent.putExtra("laundry",laundry);
                                intent.putExtra("mess",mess);
                                searchrooms.this.startActivity(intent);

                            }
                            else{
                                AlertDialog.Builder builder= new AlertDialog.Builder(searchrooms.this);
                                builder.setMessage("search Failed")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                SearchRequest searchRequest=new SearchRequest(price,shared,location,laundry,mess,responselistener);

                RequestQueue queue= Volley.newRequestQueue(searchrooms.this);
                queue.add(searchRequest);

            }
       });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Post Ad of pg/flat", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent=new Intent(searchrooms.this,postpg.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        this.setTitle("Search Rooms");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.searchrooms, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.searchroomsid) {
            Intent intent=new Intent(searchrooms.this,searchrooms.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.settingsid) {

        } else if (id == R.id.logout) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
