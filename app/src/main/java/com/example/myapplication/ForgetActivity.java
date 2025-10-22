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

public class ForgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtEmail = findViewById(R.id.edtForgetEmail);
                String strEmail = edtEmail.getText().toString();

                SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

                String savedEmail = getSharedPreferences("UserPrefs", MODE_PRIVATE).getString("email", "");
                ;
                if (strEmail.equals("")) {
                    Toast.makeText(ForgetActivity.this, "enter email", Toast.LENGTH_SHORT).show();
                } else {
                    if (strEmail.equals(savedEmail)) {
                            Intent intent = new Intent(ForgetActivity.this, ResetActivity.class);
                            startActivity(intent);
                    } else {

                        TextView tvMessage = findViewById(R.id.tvMessage);
                        tvMessage.setText("Invalid ! register again");
                    }
                }
            }
        });

        TextView tvBackTOLogin=findViewById(R.id.tvBackToLogin);
        tvBackTOLogin.setOnClickListener(v -> {
            Intent intent=new Intent(ForgetActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}