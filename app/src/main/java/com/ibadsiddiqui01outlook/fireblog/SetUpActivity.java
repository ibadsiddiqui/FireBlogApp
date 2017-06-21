package com.ibadsiddiqui01outlook.fireblog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SetUpActivity extends AppCompatActivity {


    private ImageButton mSetupImageBtn;
    private EditText mNameField;
    private Button mSubmitBtn;
    private static final int GALLERY_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);

        mSetupImageBtn = (ImageButton)findViewById(R.id.setupProfileImageButton);
        mNameField = (EditText)findViewById(R.id.nameField);
        mSubmitBtn = (Button)findViewById(R.id.setupSubmitButton);

        mSetupImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery_Intent = new Intent();
                gallery_Intent.setAction(Intent.ACTION_GET_CONTENT);
                gallery_Intent.setType("images/*");
                startActivityForResult(gallery_Intent, GALLERY_REQUEST);

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
