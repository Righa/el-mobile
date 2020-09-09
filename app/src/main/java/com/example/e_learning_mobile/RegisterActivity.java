package com.example.e_learning_mobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class RegisterActivity extends AppCompatActivity {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String password_confirmation;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron);

        Button register = findViewById(R.id.btn_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
               //startActivity(intent);

                EditText first_name_view = findViewById(R.id.register_first_name);
                EditText last_name_view = findViewById(R.id.register_last_name);
                EditText email_view = findViewById(R.id.register_email);
                EditText password_view = findViewById(R.id.register_password);
                EditText password_confirm_view = findViewById(R.id.confirm_password);
                CheckBox is_teacher = findViewById(R.id.teacher_check);

                first_name = String.valueOf(first_name_view.getText());
                last_name = String.valueOf(last_name_view.getText());
                email = String.valueOf(email_view.getText());
                password = String.valueOf(password_view.getText());
                password_confirmation = String.valueOf(password_confirm_view.getText());
                role = (is_teacher.isChecked()) ? "teacher" : "learner" ;

                signMeUp();
            }
        });
    }

    private void signMeUp() {
        final String register_url = String.format("%s%s", WelcomeActivity.CARRY_HOST, "/api/register");

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        StringRequest loginRequest = new StringRequest(Request.Method.POST, register_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("success")) {
                        WelcomeActivity.CARRY_TOKEN = jsonObject.getString("token");
                        Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
                        JSONObject user = jsonObject.getJSONObject("user");
                        WelcomeActivity.CURRENT_USER = new User(user.getString("id"), user.getString("first_name"), user.getString("last_name"), user.getString("email"), user.getString("role"));
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "login error: "+ error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map params = new HashMap();
                params.put("first_name", first_name);
                params.put("last_name", last_name);
                params.put("email", email);
                params.put("role", role);
                params.put("password", password);
                params.put("password_confirmation", password_confirmation);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(loginRequest);
    }
}
