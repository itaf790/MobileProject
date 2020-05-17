package com.app.mobileproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class IDActivity extends AppCompatActivity {
    EditText TextID;
    Button Next;
    String value1="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_d);

        Next = findViewById(R.id.btnID);
        TextID=findViewById(R.id.textid);


        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value1 = (String)TextID.getText().toString();
                Intent intent = new Intent(IDActivity.this, UpdateActivity.class);
                intent.putExtra("updateID", value1);
                startActivity(intent);
            }


        });
    }}