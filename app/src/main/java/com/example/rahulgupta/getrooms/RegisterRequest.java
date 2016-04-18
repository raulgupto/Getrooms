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
    private static final String REGISTER_REQUEST_URL = "http://192.168.43.196/register.php";
    private Map<String,String>params;

    public RegisterRequest(String name,String username,String password,String contactnumber,String gender,String dateofbirth,String email,Response.Listener<String>Listener){
        super(Request.Method.POST,REGISTER_REQUEST_URL,Listener,null);
        params=new HashMap<>();
        params.put("name",name);
        params.put("username",username);
        params.put("password",password);
        params.put("contactnumber",contactnumber);
        params.put("gender",gender);
        params.put("dateofbirth",dateofbirth);
        params.put("email",email);

    }
    @Override
    public Map<String,String>getParams(){
        return params;

    }

}
