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

public class ResetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reset);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button btnUpdate = findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText edtNewPassword = findViewById(R.id.edtNewPassword);
                String strPassword = edtNewPassword.getText().toString();

                EditText edtCP = findViewById(R.id.edtCP);
                String strCnfmpassword= edtCP.getText().toString();

                if (strPassword.equals("")) {
                    Toast.makeText(ResetActivity.this, "enter Password", Toast.LENGTH_SHORT).show();
                }
               else if (strCnfmpassword.equals("")) {
                    Toast.makeText(ResetActivity.this, "enter Password", Toast.LENGTH_SHORT).show();
                }
                else if( !(strPassword.equals(strCnfmpassword))){
                    Toast.makeText(ResetActivity.this, "Password donot match", Toast.LENGTH_SHORT).show();
                }
                else if(strPassword.equals(strCnfmpassword)){

                    SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    
                    SharedPreferences.Editor editor=prefs.edit();
                    editor.putString("password",strPassword);
                    editor.apply();

                    Toast.makeText(ResetActivity.this, "Password updated succesfully!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ResetActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView tvMessage=findViewById(R.id.tvMessage);
        tvMessage.setOnClickListener(v -> {
            Intent intent=new Intent(ResetActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        });


    }
}