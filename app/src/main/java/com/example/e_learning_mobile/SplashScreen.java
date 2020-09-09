package com.example.e_learning_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SplashScreen extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //check-auth
        try {
            sharedPreferences = getSharedPreferences(WelcomeActivity.CARRY_BUCKET, MODE_PRIVATE);
            token = sharedPreferences.getString("mi_old_token", "no");
            checkAuth();

        } catch (NullPointerException e) {
            Intent intent = new Intent(SplashScreen.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void checkAuth() {
        final String check_auth_url = String.format("%s%s", WelcomeActivity.CARRY_HOST, "/api/check_auth");

        StringRequest authRequest = new StringRequest(Request.Method.GET, check_auth_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("success")) {
                        WelcomeActivity.CARRY_TOKEN = token;

                        JSONObject user = jsonObject.getJSONObject("user");
                        WelcomeActivity.CURRENT_USER = new User(user.getString("id"), user.getString("first_name"), user.getString("last_name"), user.getString("email"), user.getString("role"));

                        Intent intent = new Intent(SplashScreen.this, DashboardActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Intent intent = new Intent(SplashScreen.this, WelcomeActivity.class);
                        intent.putExtra("token_message", jsonObject.getString("message"));
                        startActivity(intent);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MI_TAG_OH", error.getMessage());
            }
        }){
            @Override
            public Map<String, String> getHeaders(){
                Map params = new HashMap();
                params.put("Authorization", "Bearer " + token);
                return params;
            }
        };

        authRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 8000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 0;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(authRequest);
    }
}
