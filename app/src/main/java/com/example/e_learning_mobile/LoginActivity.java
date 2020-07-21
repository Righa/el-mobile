package com.example.e_learning_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

public class LoginActivity extends AppCompatActivity {
    protected String email;
    protected String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron);

        Button login = findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email_view = findViewById(R.id.login_email);
                EditText password_view = findViewById(R.id.login_password);

                email = String.valueOf(email_view.getText());
                password = String.valueOf(password_view.getText());

                logMeIn();

            }
        });
    }

    private void logMeIn() {
        final String login_url = String.format("%s%s", WelcomeActivity.CARRY_HOST, "login");

        StringRequest loginRequest = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("success")) {
                        WelcomeActivity.CARRY_TOKEN = jsonObject.getString("token");
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        JSONObject user = jsonObject.getJSONObject("user");
                        WelcomeActivity.CURRENT_USER = new User(user.getString("id"), user.getString("id"), user.getString("first_name"), user.getString("last_name"), user.getString("email"), user.getString("role"), user.getString("birthday"), user.getString("gender"));
                        intent.putExtra("NAME", user.getString("first_name"));
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "login error: "+ error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map params = new HashMap();
                params.put("email", email);
                params.put("password", password);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(loginRequest);
    }
}
