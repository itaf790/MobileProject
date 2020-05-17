package com.app.mobileproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;

public class DetailsActivity extends AppCompatActivity {
private String name;
    private String price;
    private String storage;
    private String specification;
    private String image;

DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);






        Intent intent = getIntent();

        TextView Name  = (TextView) findViewById(R.id.name);
        TextView Price  = (TextView) findViewById(R.id.price);
        TextView Storage  = (TextView) findViewById(R.id.storage);
        TextView Specification  = (TextView) findViewById(R.id.specification);
        ImageView Image = (ImageView)findViewById(R.id.IMAGE);


        name=getIntent().getStringExtra("name").toString();
        price=getIntent().getStringExtra("price").toString();
        storage=getIntent().getStringExtra("storage").toString();
        specification=getIntent().getStringExtra("specification").toString();
        image=getIntent().getStringExtra("image").toString();


        Name.setText(name);
        Price.setText(price);
        Storage.setText(storage);
        Specification.setText(specification);
        Glide.with(this ).load(image).override(300, 300) .into(Image);



    }
}
