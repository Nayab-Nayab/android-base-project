package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvMessage=findViewById(R.id.tvMessage);
        tvMessage.setOnClickListener(v -> {
            Intent intent=new Intent(RegistrationActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        });

    }
        public void signIn(View v){
            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            EditText name, email, password, cnfmpassword;

            name = findViewById(R.id.edtName);
            String strName = name.getText().toString();

            email = findViewById(R.id.edtEmail);
            String strEmail = email.getText().toString();

            password = findViewById(R.id.edtPassword);
            String strpassword = password.getText().toString();

            cnfmpassword = findViewById(R.id.edtCnfrmPassword);
            String strcnfmpassword = cnfmpassword.getText().toString();

            Button btnSignIn = findViewById(R.id.btnSignIn);
            if (strName.equals("")) {
                Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
                return;
            } else if (strEmail.equals("")) {
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
            } else if (strpassword.equals("")) {
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
            } else if (strcnfmpassword.equals("")) {
                Toast.makeText(this, "Enter confirm password", Toast.LENGTH_SHORT).show();
            } else {

                if (strpassword.equals(strcnfmpassword)) {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("name", strName);
                    editor.putString("email", strEmail);
                    editor.putString("password", strpassword);
                    editor.putString("confirm password", strcnfmpassword);
                    editor.apply();

                    Toast.makeText(this, "Regitration successfull!!", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(this, "password should be match", Toast.LENGTH_SHORT).show();
                }
            }
        }


}