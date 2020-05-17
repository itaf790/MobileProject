package com.app.mobileproject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateActivity extends AppCompatActivity {
    Button buttonUpdate;

    String value;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;
    TextView ID;
    EditText Name;
    EditText Price;
    EditText Storage;
    EditText Specification;
    String dbname,dbprice,dbstorage,dbspecification,dburl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


         Name  = (EditText) findViewById(R.id.upname);
         Price  = (EditText) findViewById(R.id.upprice);
         Storage  = (EditText) findViewById(R.id.upstorage);
         Specification  = (EditText) findViewById(R.id.upspecification);
         value=getIntent().getStringExtra("updateID").toString();

            buttonUpdate=findViewById(R.id.btnUpdate);
         ID = findViewById(R.id.updateID);
            ID.setText(value);

            databaseReference =
                    FirebaseDatabase.getInstance().getReference(InsertActivity.Database_Path);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                        dbname = snapshot.child(value).child("imageName").getValue(String.class);
                        dbprice = snapshot.child(value).child("imagePrice").getValue(String.class);
                        dbstorage = snapshot.child(value).child("imageStorage").getValue(String.class);
                        dbspecification = snapshot.child(value).child("imageSpecification").getValue(String.class);
                        dburl = snapshot.child(value).child("imageURL").getValue(String.class);
                        Name.setText(dbname);
                        Price.setText(dbprice);
                        Storage.setText(dbstorage);
                        Specification.setText(dbspecification);
                        buttonUpdate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                        String TempImageName = Name.getText().toString().trim();
                        String TempImagePrice = Price.getText().toString().trim();
                        String TempImageStorage = Storage.getText().toString().trim();
                        String TempImageSpecification = Specification.getText().toString().trim();




                        Toast.makeText(getApplicationContext(),
                                " Updated Successfully ",
                                Toast.LENGTH_LONG).show();

                                uploadinfo imageUploadInfo = new uploadinfo(
                                        TempImageName,TempImagePrice,TempImageStorage,TempImageSpecification,dburl);
                                databaseReference.child(String.valueOf(value)).setValue(imageUploadInfo);







                            }

                        });



                    }



                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressDialog.dismiss();
                }
            });
    }
}
