package com.amugika.progressexample.server;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.TextView;


import com.amugika.progressexample.ConstantValues;
import com.amugika.progressexample.Mountain;
import com.amugika.progressexample.Utils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by anartzmugika on 25/2/16.
 */
public class GetLoadData extends AsyncTask<String,Integer,ArrayList<Mountain>> {
    private ArrayList<Mountain> mountain_list;
    private Activity activity;
    private ProgressDialog progressBarDialog;
    private TextView load_dataTextView;

    public GetLoadData(Activity activity, TextView load_dataTextView)
    {
        this.activity = activity;
        this.mountain_list = new ArrayList<>();
        this.load_dataTextView = load_dataTextView;
    }

    @Override
    protected  void onPreExecute()
    {
        super.onPreExecute();
        progressBarDialog = Utils.startProgressDialog(activity);
    }

    @Override
    protected ArrayList<Mountain> doInBackground(String... params) {
        String json;

        try
        {
            json = Request.getHttpGETAPI(activity, ConstantValues.ALL_MOUNTAINS_LIST);
            JSONArray mountains_list_json_array = Mountain.getMountainJsonArray(json, true);
            for(int i = 0; i < mountains_list_json_array.length(); i++)
            {
                JSONObject mountain = mountains_list_json_array.getJSONObject(i);

                Mountain object_mountain = new Gson().fromJson(String.valueOf(mountain), Mountain.class);
                mountain_list.add(object_mountain);
                System.out.println(object_mountain.toString());
                if (i == mountains_list_json_array.length() - 1)
                {
                    publishProgress(100);
                }
                else
                {
                    publishProgress((int) ((i / (float) mountains_list_json_array.length()) * 100));
                }

                // Escape early if cancel() is called
                if (isCancelled()) break;
            }
            return mountain_list;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(ArrayList<Mountain> result) {
        super.onPostExecute(result);

        if (result != null)
        {
            load_dataTextView.setText("Mendi kopurua: " + result.size());
        }
        else
        {
            progressBarDialog.cancel();
        }

    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        System.out.println("Progress: " + progress[0]);
        progressBarDialog.setProgress(progress[0]);
        if(progress[0] == 100)
        {
            progressBarDialog.dismiss();
            progressBarDialog.cancel();
        }
     }
}
