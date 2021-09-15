package com.fadidebow.sa2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class LogIn extends AppCompatActivity {
    EditText e1,e2;

    Globalvar gv;
    final String url="http://192.168.1.103/sa2/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        e1=findViewById(R.id.en);//username
        e2=findViewById(R.id.ep);
    }

    public void sign(View v)
    {
        startActivity(new Intent(LogIn.this ,Signup.class));
    }

    public void logbtn(View v)
    {
        final String x=e1.getText().toString().trim();
        final String y=e2.getText().toString().trim();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsn=new JSONObject(s);
                    Boolean r=jsn.getBoolean("f");
                    if(r==true)
                    {//gv= (Globalvar) getApplication();
                       // gv.setName(x);
                        startActivity(new Intent(LogIn.this,Home.class));

                    }

                    else
                        Toast.makeText(LogIn.this, "create acount first ", Toast.LENGTH_SHORT).show();


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
                params.put("email",x);
                params.put("password",y);

                return params;
            }
        };
        RequestQueue r= Volley.newRequestQueue(this);
        r.add(stringRequest);
    }
}
