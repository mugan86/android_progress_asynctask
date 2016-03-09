package com.amugika.progressexample.server;

import android.content.Context;
import android.net.Uri;
import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/***************************************************************************************************
 * Created by Anartz on 06/10/2014.
 **************************************************************************************************/
public class Request {


    //get request from server with GET
    public static String getHttpPostAPI(String request_url, Uri.Builder builder) throws IOException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url = new URL(request_url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setUseCaches(true);
        conn.addRequestProperty("Cache-Control", "max-age=1800");
        conn.setRequestMethod("POST");

        conn.setDoInput(true);
        conn.setDoOutput(true);

        //Encode Query parameters....
        String query = builder.build().getEncodedQuery();

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        conn.connect();

        System.out.println("Response code: " + conn.getResponseCode());
        System.out.println("Response message: " + conn.getResponseMessage());

        // read the output from the server
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null)
        {
            stringBuilder.append(line + "\n");
        }
        return stringBuilder.toString();

    }


    //Example to set auth token to get request from server
    public static String getHttpGETAPI (Context context, String request_url) throws IOException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url;
        BufferedReader reader = null;
        StringBuilder stringBuilder;


        System.out.println("Request URL: " + request_url);

        try
        {
            // create the HttpURLConnection
            url = new URL(request_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // just want to do an HTTP GET here
            connection.setRequestMethod("GET");

            // uncomment this if you want to write output to this url
            //connection.setDoOutput(true);
            connection.setUseCaches(true);
            connection.addRequestProperty("Cache-Control", "max-age=1800");

            // give it 15 seconds to respond
            connection.setReadTimeout(15*1000);
            connection.connect();

            System.out.println("Response code: " + connection.getResponseCode());
            System.out.println("Response message: " + connection.getResponseMessage());

            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
        }
    }

}