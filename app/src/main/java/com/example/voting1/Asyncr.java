package com.example.voting1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static android.content.Context.MODE_PRIVATE;

public class Asyncr extends AsyncTask<String, Void, String> {
    AlertDialog dialog;
    Context ctx;

    Asyncr(Context ctx) {
        this.ctx = ctx;
    }


    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(ctx).create();
        dialog.setTitle("Login info...");
    }

    @Override
    protected String doInBackground(String... params) {

        String log_url = "waiting";
       final String method = params[0];
        String response = "";

        String regno = params[1];
        String password = params[2];
        try {
            URL login = new URL(log_url);
            HttpURLConnection conn = (HttpURLConnection) login.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            String data = URLEncoder.encode("regno", "UTF-8") + "=" + URLEncoder.encode(regno, "UTF-8") + "&" +
                    URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            write.write(data);
            write.flush();
            write.close();
            os.close();

//                    Response
            InputStream io = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(io, "iso-8859-1"));
            response = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                response += line;
            }
            reader.close();
            io.close();
            conn.disconnect();
            return response;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Log.e("result", result);
        Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
        String[] separated = result.split(":");
        SharedPreferences preferences = ctx.getSharedPreferences("info", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("remember", "true");
        editor.putString("lname", separated[2]);
        editor.putString("fname", separated[1]);
        editor.putString("regno", separated[0]);
        editor.putString("phnoe", separated[3]);
        editor.commit();


    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }


}

