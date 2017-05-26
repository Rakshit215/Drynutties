package com.ecommerce.r1000.rakshit.drynutties;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
public class loginApi extends AsyncTask<String,String,String>
{
    public String link="http://192.168.137.1/drynutties/log_api.php";
    Activity act;
    public TabApi ob;
    public SharedPreferences sp;
    public SharedPreferences.Editor ed;
public JSONArray ja;
    public int len=0;
    public StringBuilder str = new StringBuilder();
    public  JSONObject jo;

public  String log;
    public String pass;
   public StringBuilder result;
    public loginApi(Activity c,String st,String s)
    {
        this.act=c;
        this.log=st;
        this.pass=s;
        sp=act.getSharedPreferences("detail",act.MODE_PRIVATE);
        ed=sp.edit();
        result=new StringBuilder();
        try {
            result.append("&");
            result.append(URLEncoder.encode("id", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(log, "UTF-8"));

            result.append("&");
            result.append(URLEncoder.encode("pass", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pass, "UTF-8"));

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
            StringBuilder br=new StringBuilder();

            while ((line=rd.readLine()) != null)
            {
                Log.e("Input Line", line);
                br.append(line);

                //Toast.makeText(getApplicationContext(), "I enter here", Toast.LENGTH_LONG).show();
            }
Log.e("String::::",br.toString());
            ja=new JSONArray(br.toString());
jo=ja.getJSONObject(0);


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

        return null;
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
        Toast.makeText(act,"Login Successfull",Toast.LENGTH_LONG).show();

        try {
            ed.putString("userId",jo.getString("id"));
            ed.putString("userName",jo.getString("name"));
            ed.putString("userEmail", jo.getString("email"));
            ed.putString("status","1");
            ed.commit();


        }catch (JSONException e)
        {

        }

        ob=new TabApi(act);
        ob.execute();


    }
}
