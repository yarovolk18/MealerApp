package com.example.mealerapp.pages;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.mealerapp.R;

public class UpLoadVoidCheque extends Activity implements View.OnClickListener{
    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView imageToUpload;
    Button bUpLoadImage;
    EditText uploadImageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_load_void_cheque);

        // Image To Upload
        imageToUpload = (ImageView) findViewById(R.id.imageToUpload);
        // Button to Upload Image
        bUpLoadImage = (Button) findViewById(R.id.bUploadImage);
        // Text to upload image name
        uploadImageName = (EditText) findViewById(R.id.etUploadName);
        imageToUpload.setOnClickListener(this);

        Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.upload);
        imageToUpload.setImageBitmap(bImage);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.imageToUpload:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;

            case R.id.bUploadImage:
                break;

        }
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            imageToUpload.setImageURI(selectedImage);
        }
    }
}