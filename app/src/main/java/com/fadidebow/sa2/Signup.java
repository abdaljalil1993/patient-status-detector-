package com.fadidebow.sa2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    final String url="http://192.168.1.107/sa2/signup.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        e1=findViewById(R.id.name);//username
        e2=findViewById(R.id.em);
        e3=findViewById(R.id.pass);
        e4=findViewById(R.id.address);
    }

public void sign(View v)
{
    startActivity(new Intent(Signup.this ,LogIn.class));
}
    public void logbtn(View v)
    {
        final String x=e1.getText().toString().trim();
        final String y=e2.getText().toString().trim();
        final String z=e3.getText().toString().trim();
        final String w=e4.getText().toString().trim();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsn=new JSONObject(s);
                    Boolean r=jsn.getBoolean("fb");
                    if(r==true)
                    {
                        Toast.makeText(Signup.this, "create account successfully", Toast.LENGTH_SHORT).show();
                        e1.setText("");
                        e2.setText("");
                        e3.setText("");
                        e4.setText("");
                    }

                    else
                        Toast.makeText(Signup.this, "create acount Failed ", Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("name",x);
                params.put("email",y);
                params.put("password",z);
                params.put("address",w);

                return params;
            }
        };
        RequestQueue r= Volley.newRequestQueue(this);
        r.add(stringRequest);
    }
}
