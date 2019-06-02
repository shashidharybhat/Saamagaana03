package com.wordpress.shashidharybhat.saamagaana03;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.lang.reflect.Array;

import static android.os.Environment.DIRECTORY_DCIM;
import static android.os.Environment.DIRECTORY_DOCUMENTS;
import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class Download_Files extends AppCompatActivity {

    private Button downloadPdf, downloadMp3;
    Context abhyasa;
    private String[] names = new String[10];
    private String aud = ".mp3";
    private String pdf = ".pdf";

    public Download_Files(Context context) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_download__files);
        abhyasa = context;
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(Download_Files.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(Download_Files.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 5
                );

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

    }

    public void letsDo(int list, int type) {
        String ext = "";
        String filename = "";
        switch (list) {
            case 1:
                filename = "sarali-varisai";
                break;
            case 2:
                filename = "sobhillu";
                break;
        }
        switch (type) {
            case 1:
                ext = pdf;
                break;
            case 2:
                ext = aud;
                break;
        }


        download(filename, ext);

    }


    //Check if internet is present or not

    public void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {


        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);4
        request.setDestinationInExternalPublicDir("/Saamagaana", fileName + fileExtension);
        long downloadId = downloadmanager.enqueue(request);

    }

    public void download(final String filename, final String ext) {
        File apkStorage = null;
        File outputFile = null;
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference stor = firebaseStorage.getReferenceFromUrl("gs://fir-test1-db197.appspot.com/");
        StorageReference ref = stor.child(filename.concat(ext));


        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFile(abhyasa, filename, ext, DIRECTORY_DOWNLOADS, url);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();

            }
        });
    }


}

