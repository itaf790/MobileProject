package com.app.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    Button submit;
    int choice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        submit = (Button) findViewById(R.id.submit);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.view){
                    choice=0;
                }
                if (checkedId == R.id.add){
                    choice=1;
                }
                if(checkedId == R.id.update){
                    choice=2;
                }
                if (checkedId == R.id.delete){
                    choice=3;
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (choice)
                {
                    case 0:
                        openViewActivity();
                        break;

                    case 1:
                        openInsertActivity();
                        break;

                    case 2:
                        openUpdateActivity();
                        break;
                    case 3:
                        openDeleteActivity();
                        break;
                }

            }
        });
    }

    public void openViewActivity(){
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }
    public void openDeleteActivity(){
        Intent intent = new Intent(this, DeleteActivity.class);
        startActivity(intent);
    }
    public void openUpdateActivity(){
        Intent intent = new Intent(this, IDActivity.class);
        startActivity(intent);
    }
    public void openInsertActivity(){
        Intent intent = new Intent(this, InsertActivity.class);
        startActivity(intent);
    }

}




