package com.app.mobileproject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteActivity extends AppCompatActivity {
Button buttonDelete;
EditText TextID;
    String value="";
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        buttonDelete=findViewById(R.id.btnDelete);
        TextID=findViewById(R.id.DeleteID);
         value = (String)TextID.getText().toString();
        databaseReference =
                FirebaseDatabase.getInstance().getReference(InsertActivity.Database_Path);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {


                    buttonDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            databaseReference =
                                    FirebaseDatabase.getInstance().getReference(InsertActivity.Database_Path).child(value);
                        databaseReference.removeValue();

                            Toast.makeText(getApplicationContext(),
                                    " Deleted Successfully ",
                                    Toast.LENGTH_LONG).show();



                        }

                    });



                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });



    }
}
