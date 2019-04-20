package com.example.hunter;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;
    private TextInputLayout textInputConfirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textInputEmail = findViewById(R.id.text_input_email);
        textInputPassword = findViewById(R.id.text_input_password);
        textInputConfirmpassword = findViewById(R.id.text_input_confirmpassword);
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
        String passwordReInput = textInputConfirmpassword.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()){
            textInputPassword.setError("Password tidak boleh kosong");
            return false;
        }
        else if(!passwordReInput.equals(passwordInput)){
            textInputConfirmpassword.setError("Password tidak sesuai");
            textInputPassword.setError("Password tidak sesuai");
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

        String input = "Email: " + textInputEmail.getEditText().getText().toString();
        input += "\n";
        input += "Password: " + textInputPassword.getEditText().getText().toString();

        Toast.makeText(this, input,Toast.LENGTH_SHORT).show();

        signUp(v);
    }

    public void signUp(View v) {
        Intent gotoSignupActivity = new Intent(SignupActivity.this,MenuActivity.class);
        startActivity(gotoSignupActivity);
    }


}
