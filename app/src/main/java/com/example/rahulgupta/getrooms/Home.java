package com.example.rahulgupta.getrooms;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import android.widget.Toast;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    String hi, name,username,contactnumber,dateofbirth,email,gender;


    public String getname(){
            return name;
        }
        public String getusername(){
            return username;
        }
        public String getgender(){
            return gender;
        }
        public String getcontactnumber(){
            return contactnumber;
        }
        public String getdateofbirth(){
            return dateofbirth;
        }
        public String getemail(){
            return email;
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        find
        Intent intent=getIntent();
        hi=intent.getStringExtra("hi");
        name = intent.getStringExtra("name");
        username=intent.getStringExtra("username");
        contactnumber=intent.getStringExtra("contactnumber");
        gender=intent.getStringExtra("gender");
        dateofbirth=intent.getStringExtra("dateofbirth");
        email = intent.getStringExtra("email");

        ///int age=intent.getIntExtra("age",1);



//
//
        //upto here


        FragmentManager fm= getFragmentManager();
        final FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.framelayout,new Home_fragment(),"Starter");
        fragmentTransaction.addToBackStack("search");
        fragmentTransaction.commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction fragmentTransaction1=getFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.framelayout,new Postrooms_fragment(),"Postrooms");
                    fragmentTransaction1.commit();
                    Snackbar.make(view, "Post Ad", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.setDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
            else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
            FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout,new Searchrooms_fragment(),"Searchrooms");
            fragmentTransaction.commit();

        } else if (id == R.id.settingsid) {
             Toast.makeText(getBaseContext(),"Will be added Later", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.logout) {

            Intent myIntent = new Intent(Home.this, MainActivity.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// clear back stack
            startActivity(myIntent);
            finish();

        } else if (id == R.id.nav_share) {
            Toast.makeText(getBaseContext(),"Will be added Later", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {
            Toast.makeText(getBaseContext(),"Will be added Later", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

}

