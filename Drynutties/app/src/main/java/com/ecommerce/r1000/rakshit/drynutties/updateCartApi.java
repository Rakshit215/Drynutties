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
 * Created by Rakshit on 1/13/2016.
 */
public class updateCartApi extends AsyncTask<String,String ,String>
{
    public String link="http://192.168.137.1/drynutties/updateCart_api.php";
    public Activity con;
    public StringBuilder result;
    public StringBuilder br;
public String uid;

    public updateCartApi(Activity con,String val,String uid,String pid)
    {
        this.con=con;
        this.uid=uid;
        result=new StringBuilder();
        try {
            result.append("&");
            result.append(URLEncoder.encode("quantity", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(val, "UTF-8"));

            result.append("&");
            result.append(URLEncoder.encode("uid", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(uid, "UTF-8"));

            result.append("&");
            result.append(URLEncoder.encode("pid", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pid, "UTF-8"));

        }
        catch (UnsupportedEncodingException e)
        {
            Log.e("Error", "Detected");
        }


    }

    @Override
    protected String doInBackground(String... strings)
    {


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
            int i=connection.getResponseCode();

            Log.e("Input Line 0", String.valueOf(i));


            InputStream inputStream = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line = "abc";
            br=new StringBuilder();

            while ((line=rd.readLine()) != null)
            {
                Log.e("Input Line", line);
                br.append(line);

                //Toast.makeText(getApplicationContext(), "I enter here", Toast.LENGTH_LONG).show();
            }




        }catch (MalformedURLException e)
        {
            Log.e("Error 1", "Hello");

        }
        catch (IOException e)
        {
            // writing exception to log
            e.printStackTrace();
            Log.e("Error 2", "world");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Error 3", "End");

        }
        //Log.e("Final String" , ja.toString());


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        new CartCheckapi(con,uid,1).execute();
        Toast.makeText(con, br, Toast.LENGTH_LONG).show();

    }
}
