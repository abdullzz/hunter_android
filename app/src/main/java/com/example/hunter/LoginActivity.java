package com.example.hunter;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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

public class LoginActivity extends AppCompatActivity {

    final String LOGIN_URL = "https://hunter.co.id/hunter/api/users/login";
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEmail = findViewById(R.id.text_input_email);
        textInputPassword = findViewById(R.id.text_input_password);
    }

    public void DaftarActivity(View v){
        Intent gotoDaftarActivity = new Intent(LoginActivity.this, DaftarActivity.class);
        startActivity(gotoDaftarActivity);
    }

    public void ForgotActivity(View v){
        Intent gotoForgotActivity = new Intent(LoginActivity.this, ForgotActivity.class);
        startActivity(gotoForgotActivity);
    }

    public void checkLogin(View v){
        Intent gotoMainActivity = new Intent(LoginActivity.this, MainActivity.class);
        gotoMainActivity.putExtra("id_user", "21");
        gotoMainActivity.putExtra("nama_lengkap", "Abdullah Said Mashabi Abdullah");
        gotoMainActivity.putExtra("alamat", "Jl. Cakra Muliya Blok T.72 Cinere, Limo, Depok");
        gotoMainActivity.putExtra("no_ktp", "12345678910121416");
        gotoMainActivity.putExtra("no_hp", "0811133505");
        gotoMainActivity.putExtra("point", "1250");
        gotoMainActivity.putExtra("picture", "12412.jpg");
        startActivity(gotoMainActivity);
    }

    public void proceedLogin(View v){
//        Intent gotochecklogin = new Intent(LoginActivity.this, MainActivity.class);
//        startActivity(gotochecklogin);

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

                            String userData = jsonObject.optString("data");
                            JSONObject parseUserData = new JSONObject(userData);




//                            Log.i("User Data", parseUserData.getString("nama_lengkap"));
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
                            String id_user = getIntent().getStringExtra("id_user");
                            String nama_lengkap = parseUserData.getString("nama_lengkap");
                            String alamat = parseUserData.getString("alamat");
                            String no_ktp = parseUserData.getString("no_ktp");
                            String no_hp = parseUserData.getString("no_hp");
                            String point = parseUserData.getString("point");
                            String picture = parseUserData.getString("picture");


//                            Log.i("Nama Lengkap", nama_lengkap);
//                            Log.i("Alamat", alamat);
//                            Log.i("Nomor KTP", no_ktp);
//                            Log.i("Nomor HP", no_hp);

                            //String kode = object.getString("kode");
                            //String dv = object.getString("dv");
                            //String email = object.getString("email");
                            //String date_login = object.getString("date_login");
                            //Log.i("Rolesnya",role);


                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("id_user", id_user);
                            intent.putExtra("nama_lengkap", nama_lengkap);
                            intent.putExtra("alamat", alamat);
                            intent.putExtra("no_ktp", no_ktp);
                            intent.putExtra("no_hp", no_hp);
                            intent.putExtra("point", point);
                            intent.putExtra("picture", picture);

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


                return params;
            }
        };

        queue.add(getRequest);

    }



}
