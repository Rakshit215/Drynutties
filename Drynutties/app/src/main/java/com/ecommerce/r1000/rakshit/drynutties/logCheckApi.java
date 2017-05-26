package com.ecommerce.r1000.rakshit.drynutties;

import android.app.Activity;
import android.content.Intent;
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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Rakshit on 1/15/2016.
 */
public class logCheckApi extends AsyncTask<String,String,String> {
    public String link = "http://192.168.137.1/drynutties/logCheck_api.php";

    public String login;
    public String pass;
    public Activity con;
    public StringBuilder result;
    public String line;

    public logCheckApi(Activity c, String log, String pass) {
        this.con = c;
        this.login = log;
        this.pass = pass;
        result = new StringBuilder();
        try {
            result.append("&");
            result.append(URLEncoder.encode("id", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(login, "UTF-8"));

            result.append("&");
            result.append(URLEncoder.encode("pass", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pass, "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            Log.e("Error", "Detected");
        }

    }

    @Override
    protected String doInBackground(String... strings) {


        try {

            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //connection.setRequestProperty("User-Agent","Mozilla/5.0");
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);


            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(result.toString());
            writer.flush();
            writer.close();
            os.close();
            connection.connect();
            int i = connection.getResponseCode();

            Log.e("Input Line 0", String.valueOf(i));


            InputStream inputStream = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder br = new StringBuilder();

            while ((line = rd.readLine()) != null) {
                Log.e("Input Line", line);
                br.append(line);

                //Toast.makeText(getApplicationContext(), "I enter here", Toast.LENGTH_LONG).show();
            }

            line = br.toString();


        } catch (MalformedURLException e) {
            Log.e("Error 1", "Hello");

        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
            Log.e("Error 2", "world");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Error 3", "End");

        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        if (line.indexOf("null") > -1) {
            Toast.makeText(con, "User name/Password Incorrect\nPlease Try Again", Toast.LENGTH_LONG).show();
        } else {
            new loginApi(con, login, pass).execute();
        }
    }
}
