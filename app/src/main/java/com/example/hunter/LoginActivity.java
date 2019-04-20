package com.example.hunter;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        android.support.v7.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.hunter_header_bar);
        setSupportActionBar(toolbar);

        textInputEmail = findViewById(R.id.text_input_email);
        textInputPassword = findViewById(R.id.text_input_password);


}

    public void rememberMe(View view) {
        boolean checked = ((CheckBox) findViewById(R.id.checkBox)).isChecked();
            if(checked) {} //proses ingat saya
    }


    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();

        String regex = "^[A-Za-z0-9+_.-]+@(.+)$"; // boleh alfanumerik
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailInput);

        if (emailInput.isEmpty()){
            textInputEmail.setError("Email tidak boleh kosong");
            return false;
        }
        else if(!matcher.matches()){
            textInputEmail.setError("Format email salah");
            return false;
        }
        else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()){
            textInputPassword.setError("Password tidak boleh kosong");
            return false;
        }
        else if(passwordInput.length() < 8){
            textInputPassword.setError("Password minimal 8 karakter");
            return false;
        }
        else {
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
        BackgroundAsync sync = new BackgroundAsync(this);
        sync.execute(type, emailInput, passwordInput);
//        String input = "Email: " + textInputEmail.getEditText().getText().toString();
//        input += "\n";
//        input += "Password: " + textInputPassword.getEditText().getText().toString();
//
//        Toast.makeText(this, input,Toast.LENGTH_SHORT).show();
//        menu(v);
//        rememberMe(v);
    }

    public void signUp(View v) {
        Intent gotoSignupActivity = new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(gotoSignupActivity);
    }

    public void forgotPassword(View v){
        Intent gotoForgotpasswordActivity = new Intent(LoginActivity.this, ForgotpasswordActivity.class);
        startActivity(gotoForgotpasswordActivity);
    }

    public void menu(View v){
        Intent gotoMenuActivity = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(gotoMenuActivity);
    }


}
