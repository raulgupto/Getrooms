package com.example.rahulgupta.getrooms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class display_listview extends AppCompatActivity {

    String json_String;
    JSONObject jsonObject;
    JSONArray jsonArray;
    room_info_adapter roomInfoAdapter;
    ListView listView;
    String json_string;
    String str;
    String location, shared, laundry,mess,price;
   // Button backbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
     //   backbutton=(Button)findViewById(R.id.backbutton);
        // Intent i = new Intent();


//        StrictMode.ThreadPolicy Policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(Policy);
//
//        RequestPackage requestPackage = new RequestPackage();
//        requestPackage.setMethod("GET");
//
//        requestPackage.setParam("bORc", str);
//
//        requestPackage.setUri("http://192.168.43.66/pecolx/fetch_bookdetails.php");
//        SignupTask signupTask = new SignupTask();
//        signupTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestPackage);
//
//        while(json_string==null) {// Toast.makeText(getApplicationContext(), "First get json", Toast.LENGTH_SHORT).show();
//        }


        // Intent i = new Intent(this, display_listview.class);
        //  i.putExtra("json_data", json_string);
//            i.putExtra("message",)
        //startActivity(i);
        setContentView(R.layout.activity_display_listview2);
        listView = (ListView) findViewById(R.id.listview);
        roomInfoAdapter = new room_info_adapter(this, R.layout.roomdetails_layout);
        listView.setAdapter(roomInfoAdapter);
        Intent intent=getIntent();
        json_String = intent.getStringExtra("jsonstring");



        try {
            //    jsonObject = new JSONObject("{json_String}");
            jsonObject = new JSONObject(json_String.substring(json_String.indexOf("{"), json_String.lastIndexOf("}") + 1));
            jsonArray = jsonObject.getJSONArray("result");
            int count = 0;

            while (count <jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                location = "Location"+": "+jo.getString("location");
                shared = "Shared"+": "+jo.getString("shared");
                laundry = "Laundry"+": "+jo.getString("laundry");
                mess="Mess"+": "+jo.getString("mess");
                price="Price"+": "+jo.getString("price");

                //   DownloadImage obj=new DownloadImage();
                // obj.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                Room_details info = new Room_details(location,shared,laundry,mess,price);
                roomInfoAdapter.add(info);
                count++;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Onbackbutton pressed
//        backbutton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(),"Touch back to reach Search Page", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });





//        str = getIntent().getStringExtra("message");



    }




//
//    public class DownloadImage extends AsyncTask<Void,Integer,String> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values[0]);
//            setProgressBarVisibility(true);
//            setProgress(values[0]);
//        }
//
//        @Override
//        protected String doInBackground(Void... params) {
//            Log.i("path",path);
//            String Url = "http://192.168.43.66/" + path;
//            try {
//                URLConnection connection = new URL(Url).openConnection();
//                connection.setConnectTimeout(1000 * 30);
//                connection.setReadTimeout(1000 * 30);
//              //  bitmap = (BitmapFactory.decodeStream((InputStream) connection.getContent(), null, null));
//                Log.i("pavcv", "vdv");
//                //  Bitmap c=x.createScaledBitmap(x, 160, 160, true);
//                //bitmapstring= BitmaptoBinary(c);
//                //return bitmapstring;
//                //  BitmaptoBinary();
//            } catch (Exception e) {
//                e.printStackTrace();
//
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String bitmapString) {
//
////            super.onPostExecute(bitmapString);
////            Intent i=new Intent(getActivity(),imagetest.class);
////         //   i.putExtra("BitmapString",bitmapstring);
////            startActivity(i);
//////            if(bitmap !=null)
//////            {
//////                x.setImageBitmap(bitmap);
//////            }
//
//        }
//    }


}
