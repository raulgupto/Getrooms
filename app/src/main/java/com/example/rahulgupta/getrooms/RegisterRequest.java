package com.example.rahulgupta.getrooms;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rahul Gupta on 09-04-2016.
 */
public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL="http://192.168.43.196/register.php";
    private Map<String,String>params;

    public RegisterRequest(String name,String username,int age,String password,String contactnumber,Response.Listener<String>Listener){
        super(Request.Method.POST,REGISTER_REQUEST_URL,Listener,null);
        params=new HashMap<>();
        params.put("name",name);
        params.put("age",age+"");
        params.put("username",username);
        params.put("password",password);
        params.put("contactnumber",contactnumber);

    }
    @Override
    public Map<String,String>getParams(){
        return params;

    }

}
