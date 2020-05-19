package com.app.mobileproject;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class InsertActivity extends AppCompatActivity {
    Button insert, choose;
    EditText Name,Price,Storage,Specification ;
    ImageView imgview;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;
    static String Storage_Path = "Uploads/";
    static String Database_Path = "Mobile_Database";
    int ImageUploadId =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);


        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);
        choose = (Button)findViewById(R.id.Choose);
        insert= (Button)findViewById(R.id.Insert);
        Name = (EditText)findViewById(R.id.Name);
        Price = (EditText)findViewById(R.id.Price);
        Storage = (EditText)findViewById(R.id.Storage);
        Specification = (EditText)findViewById(R.id.Specification);
        imgview = (ImageView)findViewById(R.id.imageView);
        progressDialog = new ProgressDialog(InsertActivity.this);// context name as per your project name


        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);

            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                UploadImageFileToFirebaseStorage();

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                imgview.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }


    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    public void UploadImageFileToFirebaseStorage() {
        // Checking whether FilePathUri Is empty or not.
        if (FilePathUri != null) {
            // Setting progressDialog Title.
            progressDialog.setTitle("Image is Uploading...");
            // Showing progressDialog.
            progressDialog.show();
            // Creating second StorageReference.
            final StorageReference storageReference2 =
                    storageReference.child(Storage_Path +
                            System.currentTimeMillis() + "." +
                            GetFileExtension(FilePathUri));
            // Adding CompleteListener to second StorageReference.
            storageReference2.putFile(FilePathUri).continueWithTask(
                    new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task)
                                throws Exception {
                            if (!task.isSuccessful()){
                                throw task.getException();
                            }
                            // Setting progressDialog Title.
                            progressDialog.setTitle("Image is Uploading...");
                            return storageReference2.getDownloadUrl();

                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        // Getting image download Url
                        Uri downUri = task.getResult();
                    }
                }
            }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    String TempImageName = Name.getText().toString().trim();
                    String TempImagePrice = Price.getText().toString().trim();
                    String TempImageStorage = Storage.getText().toString().trim();
                    String TempImageSpecification = Specification.getText().toString().trim();
                    // Hiding the progressDialog after done uploading.
                    progressDialog.dismiss();
                    // Showing toast message after done uploading.
                    Toast.makeText(getApplicationContext(),
                            "Image Uploaded Successfully ",
                            Toast.LENGTH_LONG).show();
                    @SuppressWarnings("VisibleForTests")

                    uploadinfo imageUploadInfo = new uploadinfo(
                            TempImageName,TempImagePrice,TempImageStorage,TempImageSpecification,uri.toString());
                    // Getting image upload ID.

                    // Adding image upload id s child element into databaseReference.
                    databaseReference.child(String.valueOf(ImageUploadId+1)).setValue(imageUploadInfo);
                    ImageUploadId =ImageUploadId+1;

                }
            })
                    // If something goes wrong
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Hiding the progressDialog.
                            progressDialog.dismiss();
                            // Showing exception error message.
                            Toast.makeText(InsertActivity.this,
                                    exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        }
        else {
            Toast.makeText(InsertActivity.this,

                    "Please Select Image ",
                    Toast.LENGTH_LONG).show();
        }
    }
}

