package com.example.hunter;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;
    final String LOGIN_URL = "http://192.168.100.2/hunterbinus/hunterapi/public/api/dologin";
    public static String token_now = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        textInputEmail = findViewById(R.id.text_input_email);
        textInputPassword = findViewById(R.id.text_input_password);


    }

    public void rememberMe(View view) {
        boolean checked = ((CheckBox) findViewById(R.id.checkBox)).isChecked();
        if (checked) {
        } //proses ingat saya
    }


    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();

        String regex = "^[A-Za-z0-9+_.-]+@(.+)$"; // boleh alfanumerik
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailInput);

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Email tidak boleh kosong");
            return false;
        } else if (!matcher.matches()) {
            textInputEmail.setError("Format email salah");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Password tidak boleh kosong");
            return false;
        } else if (passwordInput.length() < 8) {
            textInputPassword.setError("Password minimal 8 karakter");
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validatePassword()) {
            return;
        }
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();
        String type = "login";
        checkLogin();
//        BackgroundAsync sync = new BackgroundAsync(this);
//        sync.execute(type, emailInput, passwordInput);
//        String input = "Email: " + textInputEmail.getEditText().getText().toString();
//        input += "\n";
//        input += "Password: " + textInputPassword.getEditText().getText().toString();
//
//        Toast.makeText(this, input,Toast.LENGTH_SHORT).show();
//        menu(v);
//        rememberMe(v);
    }

    public void signUp(View v) {
        Intent gotoSignupActivity = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(gotoSignupActivity);
    }

    public void forgotPassword(View v) {
        Intent gotoForgotpasswordActivity = new Intent(LoginActivity.this, ForgotpasswordActivity.class);
        startActivity(gotoForgotpasswordActivity);
    }

    public void menu(View v) {
        Intent gotoMenuActivity = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(gotoMenuActivity);
    }

    void checkLogin() {
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        StringRequest getRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //String response yg di parameter maksudnya json atau response yang dikirim dari API
                        // kalo bener dia kesini
                        try {
                            //dari string response kita bkin jadi JSONObject
                            JSONObject jsonObject = new JSONObject(response);
                            Log.i("response API", jsonObject.toString());
                            //{'id_user':'9','role':1} diubah menjadi json object
                            //yang mana kita panggil berdasarkan parameternya

                            //ngambil parameter id_user
//                            String res = jsonObject.getString("id_user");
//                            Log.i("response success", res);
                            //ngambil parameter role
//                            JSONObject object = new JSONObject("userData");
//                            Log.i("Role",object.getString("role"));
//                            String token = jsonObject.getString("token");
//                            String role = jsonObject.getString("role");
                            String nama_lengkap = jsonObject.getString("nama_lengkap");
                            //String kode = object.getString("kode");
                            //String dv = object.getString("dv");
                            //String email = object.getString("email");
                            //String date_login = object.getString("date_login");
                            //Log.i("Rolesnya",role);


                            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                            intent.putExtra("nama_lengkap", nama_lengkap);
                            //intent.putExtra("role",i);
                            startActivity(intent);

//                            if (role.equals("2") && !token.isEmpty()) {
//                                token_now = token;
//                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                intent.putExtra("nama_lengkap", nama_lengkap);
//                                //intent.putExtra("role",i);
//                                startActivity(intent);
//                            }
                        } catch (JSONException e) {
                            Log.i("error json object", e.toString());
                        }

                    }
                },
                new Response.ErrorListener() {
                    //kalo error dia kesini
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString() );
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams()
            {
                //masukin data sesuai dengan parameter API
                String username = textInputEmail.getEditText().getText().toString().trim();
                String password = textInputPassword.getEditText().getText().toString().trim();
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", username);
                params.put("password", password);
                params.put("date_login", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));

                return params;
            }
        };

        queue.add(getRequest);
    }


}