package com.example.gallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.gallery.showImage";

    ArrayList<CreateList> images = new ArrayList<>();
    private static final int REQUEST_CODE = 1024;
    private final String image_titles[] = {
            "Img1",
            "Img2",
            "Img3",
    };

    private final Integer image_ids[] = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.gallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<CreateList> createLists = prepareData();
        MyAdapter adapter = new MyAdapter(getApplicationContext(), createLists);
        recyclerView.setAdapter(adapter);


    }

    private ArrayList<CreateList> prepareData() {




//        if(!Environment.isExternalStorageManager()){
        if(!Environment.isExternalStorageManager()){

            Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            return new ArrayList<>();
        }
        else{
            String externalPath = Environment.getExternalStorageDirectory().toString() + "/Pictures";
            File directory = new File(externalPath);
            File files[] = directory.listFiles();
            for(int i = 0; i < files.length; i++) {
                CreateList createList = new CreateList();
                String file = files[i].getName();
                String tmp = file.substring(file.length() - 4);
                if(tmp.equals(".jpg") || tmp.equals(".png") || file.substring(file.length() - 5).equals(".jpeg")) {
                    //Toast.makeText(this, externalPath + "/" + file, Toast.LENGTH_SHORT).show();
                    createList.setImage_path(externalPath + "/" + file);
                    createList.setImage_title(file);
                    images.add(createList);
                }
            }
        }

//        for (int i = 0; i < image_titles.length; i++) {
//            CreateList createList = new CreateList();
//            createList.setImage_title(image_titles[i]);
//            createList.setImage_ID(image_ids[i]);
//            images.add(createList);
//        }
        return images;
    }

}