package com.example.myapplication;

import android.annotation.SuppressLint;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etName = (EditText) findViewById(R.id.etName);
                EditText etPassword = (EditText) findViewById(R.id.etPassword);
                TextView tvMessage = findViewById(R.id.tvMessage);
                etName.setText("");
                etPassword.setText("");
                tvMessage.setText("");
                Toast.makeText(MainActivity.this, "Fields are cleared!", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });

        Button btnForget=findViewById(R.id.btnForget);
        btnForget.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ForgetActivity.class);
            startActivity(intent);
        });
    }
    public void login(View v) {
        EditText etName = findViewById(R.id.etName);
        String strName = etName.getText().toString();
        EditText etPassword = findViewById(R.id.etPassword);
        String strPassword = etPassword.getText().toString();
        String savedEmail= getSharedPreferences("UserPrefs",MODE_PRIVATE).getString("email","");;
        String savedPassword= getSharedPreferences("UserPrefs",MODE_PRIVATE).getString("password","");;

        if(strName.equals("")) {
            Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(strPassword.equals("")) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
        }
        else {

            TextView tvMessage = findViewById(R.id.tvMessage);
            // Hard-code username and password in if condition here for displaying login success or failed message.
            // Later on you can modify this code and add database connectivity for login authentication.
            if(strName.equals(savedEmail)&&strPassword.equals(savedPassword)){
                Intent intent=new Intent(MainActivity.this,LandingActivity.class);
                startActivity(intent);

            }
            else{
                tvMessage.setText("Invalid credentials");
            }
        }
    }
}