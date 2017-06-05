package com.ibadsiddiqui01outlook.fireblog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostActivity extends AppCompatActivity {
    private ImageButton mSelectImage;
    private EditText mPostTitle;
    private EditText mPostDesc;
    private Button mPostSubmitBtn;

    private Uri mImageUri = null;   // for image url

    private static final int GALLERY_REQUEST = 1;   // accessing gallery

    private StorageReference mFireBaseStorage;  //firebase storage for images

    private ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mFireBaseStorage = FirebaseStorage.getInstance().getReference();    // Initialize firebase storage
        mProgress = new ProgressDialog(this);
        mSelectImage = (ImageButton)findViewById(R.id.imageSelect); // Image View for Selecting images

        mPostDesc = (EditText)findViewById(R.id.descField); // Description for image
        mPostTitle = (EditText)findViewById(R.id.titleField); // Title for image
        mPostSubmitBtn = (Button)findViewById(R.id.submitBtn); // Submit Button

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");   // goto gallery
                startActivityForResult(galleryIntent,GALLERY_REQUEST);  // change activity to gallery
            }
        });

        // Post blog on submit button click
        mPostSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting(); // post the image
            }
        });


    }

    private void startPosting() {
        mProgress.setMessage("Posting to Blog...");
        mProgress.show();

        String title_Val = mPostTitle.getText().toString().trim();  // trims the title of image
        String desc_val = mPostDesc.getText().toString().trim();    // trims the image description

        if(!TextUtils.isEmpty(title_Val) && !TextUtils.isEmpty(desc_val) && mImageUri != null){
            StorageReference filePath = mFireBaseStorage.child("#Log_Images").child(mImageUri.getLastPathSegment());    // lastpathsegement will return name of image

            filePath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri downloadUri = taskSnapshot.getDownloadUrl();    // provides dwonload uri
                    mProgress.dismiss();
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){  // Goes to Gallery
            mImageUri = data.getData();  // get image from gallery
            mSelectImage.setImageURI(mImageUri);    // set the image to the image button
        }
    }

}
