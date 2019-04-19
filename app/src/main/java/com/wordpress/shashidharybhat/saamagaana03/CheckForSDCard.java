package com.wordpress.shashidharybhat.saamagaana03;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

public class CheckForSDCard {
    //Check If SD Card is present or not method
    public boolean isSDCardPresent() {
        return Environment.getExternalStorageState().equals(

                Environment.MEDIA_MOUNTED);
    }
}
