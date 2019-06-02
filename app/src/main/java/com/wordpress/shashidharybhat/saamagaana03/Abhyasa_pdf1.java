package com.wordpress.shashidharybhat.saamagaana03;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
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
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class Abhyasa_pdf1 extends AppCompatActivity {

    PDFView book1;
    Button play, stop;

    int pause_pos = 0;
    //File localFile1;
    //FirebaseStorage storage;
    //StorageReference storageRef;
    //StorageReference pdfref;
    //private Runnable runnable;
    Context context;
    MediaPlayer mymedia;
    private SeekBar seekBar;
    //private TextView textView;
    private MediaPlayer mMediaplayer;
    private Handler mHandler;
    private Runnable mRunnable;
    String filepath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abhyasa_pdf1);
        context = getApplicationContext();
        File path = context.getFilesDir();
        play = findViewById(R.id.btn_play);
        stop = findViewById(R.id.stop);
        seekBar = findViewById(R.id.seekBar2);
        Toast.makeText(getApplicationContext(), "Please wait till the song is ready", Toast.LENGTH_LONG).show();
        mHandler = new Handler();
        //mMediaplayer = new MediaPlayer();

        fetchAudio();
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtn(view);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtn(view);
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /*
                void onProgressChanged (SeekBar seekBar, int progress, boolean fromUser)
                    Notification that the progress level has changed. Clients can use the fromUser
                    parameter to distinguish user-initiated changes from those that occurred programmatically.

                Parameters
                    seekBar SeekBar : The SeekBar whose progress has changed
                    progress int : The current progress level. This will be in the range min..max
                                   where min and max were set by setMin(int) and setMax(int),
                                   respectively. (The default values for min is 0 and max is 100.)
                    fromUser boolean : True if the progress change was initiated by the user.
            */
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (mymedia != null && b) {
                    /*
                        void seekTo (int msec)
                            Seeks to specified time position. Same as seekTo(long, int)
                            with mode = SEEK_PREVIOUS_SYNC.

                        Parameters
                            msec int: the offset in milliseconds from the start to seek to

                        Throws
                            IllegalStateException : if the internal player engine has not been initialized
                    */
                    mymedia.seekTo(i * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        boolean connected;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        if (!connected) {
            Toast.makeText(context, "Please check your Internet Connection", Toast.LENGTH_SHORT).show();
        }
        //File file = new File(path, "sarali-varisai.pdf");
        book1 = findViewById(R.id.pdf_abhyasa1);
        String filepath = Environment.getExternalStorageDirectory() + File.separator + "Saamagaana" + File.separator + "sarali-varisai.pdf";
        File file = new File(filepath);
        book1.fromFile(file).load();
        /*storage = FirebaseStorage.getInstance();
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
                    Log.println(Log.ERROR, "Failure", "It Failed Man!!!!!!");
                }
            });
        } catch (Exception e) {
        }*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mymedia.isPlaying()) {
            mymedia.stop();
        }
    }

    private void fetchAudio() {

        //final FirebaseStorage storage = FirebaseStorage.getInstance();
        filepath = Environment.getExternalStorageDirectory() + File.separator + "Saamagaana" + File.separator + "sobhillu.mp3";
        //File file = new File(filepath);

        // Download url of file
        //final String url = uri.toString();
        mMediaplayer = MediaPlayer.create(this, Uri.parse(filepath));
        //mMediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // wait for media player to get prepare
        Toast.makeText(getApplicationContext(), "The song is ready", Toast.LENGTH_SHORT).show();
        mymedia = mMediaplayer;
        initializeSeekBar();
        // mMediaplayer.setOnPreparedListener(Abhyasa_pdf1.this);
        //mMediaplayer.prepareAsync();


        // Create a storage reference from our app
        //StorageReference storageRef = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/fir-test1-db197.appspot.com/o/sobhillu.mp3?alt=media&token=04059489-c82c-45b1-8912-34a40258e622");
        //storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
        //   @Override
        //   public void onSuccess(Uri uri) {


        //  }
        //})
        //        .addOnFailureListener(new OnFailureListener() {
        //          @Override
        //         public void onFailure(@NonNull Exception e) {
        //         Log.i("TAG", e.getMessage());
        //       }
        //   });*/

    }

    /*@Override
    public void onPrepared(MediaPlayer mp) {
        Toast.makeText(getApplicationContext(), "The song is ready", Toast.LENGTH_SHORT).show();
        mymedia = mp;
        initializeSeekBar();
    }*/

    public void setBtn(View view) {
        if (view == findViewById(R.id.btn_play)) {
            if (mymedia.isPlaying()) {
                mymedia.pause();
                pause_pos = mymedia.getCurrentPosition();

                seekBar.setProgress(pause_pos);
            } else if (pause_pos == 0 && !mymedia.isPlaying()) {
                fetchAudio();
                mymedia.start();
                initializeSeekBar();
            } else {
                mymedia.seekTo(pause_pos);

                mymedia.start();
                initializeSeekBar();
            }
        } else {
            if (mHandler != null) {
                mHandler.removeCallbacks(mRunnable);
            }
            pause_pos = 0;
            seekBar.setProgress(pause_pos);
            mymedia.stop();
        }
    }

    protected void initializeSeekBar() {
        seekBar.setMax(mymedia.getDuration() / 1000);

        mRunnable = new Runnable() {
            @Override
            public void run() {
                if (mymedia != null) {
                    int mCurrentPosition = mymedia.getCurrentPosition() / 1000; // In milliseconds
                    seekBar.setProgress(mCurrentPosition);
                }
                mHandler.postDelayed(mRunnable, 1000);
            }
        };
        mHandler.postDelayed(mRunnable, 1000);
    }

}

       /* play = findViewById(R.id.btn_play);
         stop = findViewById(R.id.stop);
        seekBar = findViewById(R.id.seekBar2);
        handler = new Handler();
        try {
            mdp.setDataSource("https://firebasestorage.googleapis.com/v0/b/fir-test1-db197.appspot.com/o/sobhillu.mp3?alt=media&token=04059489-c82c-45b1-8912-34a40258e622");
            mdp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    seekBar.setMax(mdp.getDuration());
                }

            });



        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // mdp = MediaPlayer.create(getApplicationContext(), R.raw.sobhillu);

        /*mdp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                seekBar.setMax(mdp.getDuration());

                changeSeekbar();
            }

        });*/