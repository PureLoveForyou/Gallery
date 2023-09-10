package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class showImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        ImageView imageView = findViewById(R.id.imageShow);
        ImageButton ocr = (ImageButton) findViewById(R.id.ocr);
        ImageButton translate = (ImageButton) findViewById(R.id.tranlate);

        String path = getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE);
        imageView.setImageBitmap(BitmapFactory.decodeFile(path));

        ocr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}