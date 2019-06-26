package com.example.hunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DaftarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
    }


    public void OTP(View view) {
        Intent gotoOTPActivity = new Intent(DaftarActivity.this, OTPActivity.class);
        startActivity(gotoOTPActivity);
    }

}
