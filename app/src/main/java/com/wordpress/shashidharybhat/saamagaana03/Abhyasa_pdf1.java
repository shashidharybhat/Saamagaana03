package com.wordpress.shashidharybhat.saamagaana03;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class Abhyasa_pdf1 extends AppCompatActivity implements View.OnClickListener {
    protected SeekBar seekBar;
    PDFView book1;
    Button play, stop;
    MediaPlayer mdp;
    int pause_pos = 0;
    File localFile1;
    FirebaseStorage storage;
    StorageReference storageRef;
    StorageReference pdfref;
    private Runnable runnable;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abhyasa_pdf1);
        book1 = findViewById(R.id.pdf_abhyasa1);
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://fir-test1-db197.appspot.com");
        pdfref = storageRef.child("sarali-varisai.pdf");
        try {
            localFile1 = File.createTempFile("sarali-varisai", "pdf");

            pdfref.getFile(localFile1).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    book1.fromFile(localFile1).load();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                    Log.println(Log.ERROR, "Failure", "It Failed Man!!!!!!");
                }
            });
        } catch (Exception e) {
        }
        play = findViewById(R.id.btn_play);
        stop = findViewById(R.id.stop);
        seekBar = findViewById(R.id.seekBar2);
        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        handler = new Handler();

        mdp = MediaPlayer.create(getApplicationContext(), R.raw.sobhillu);

        mdp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                seekBar.setMax(mdp.getDuration());

                changeSeekbar();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    mdp.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    private void changeSeekbar() {
        seekBar.setProgress(mdp.getCurrentPosition());
        if (mdp.isPlaying()) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    changeSeekbar();
                }
            };
            handler.postDelayed(runnable, 500);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_play:
                if (mdp.isPlaying()) {
                    mdp.pause();
                    pause_pos = mdp.getCurrentPosition();
                } else {
                    mdp.seekTo(pause_pos);
                    mdp.start();
                }
                break;
            case R.id.stop:
                if (mdp != null) {
                    pause_pos = mdp.getCurrentPosition();
                    mdp.seekTo(0);
                    pause_pos = 0;
                    changeSeekbar();
                    mdp.stop();
                    mdp = MediaPlayer.create(getApplicationContext(), R.raw.sobhillu);
                }
                break;
        }

    }

}
