package com.amugika.progressexample;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by anartzmugika on 9/3/16.
 */
public class Utils {

    public static ProgressDialog startProgressDialog(Activity activity)
    {
        ProgressDialog progressBarDialog =new ProgressDialog(activity);
        progressBarDialog.setMessage("Datuak kargatzen...");
        progressBarDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBarDialog.setCancelable(false);
        progressBarDialog.show();
        return progressBarDialog;
    }

    public static int getProgress(int current_position, int total_items)
    {
        return (int) ((current_position / (float) total_items) * 100);
    }
}
