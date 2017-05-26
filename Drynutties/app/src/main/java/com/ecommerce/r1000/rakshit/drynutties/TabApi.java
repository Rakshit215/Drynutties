package com.ecommerce.r1000.rakshit.drynutties;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Rakshit on 1/4/2016.
 */
public class TabApi extends AsyncTask<String, String, String>
{

    public String link="http://192.168.137.1/drynutties/tablist_api.php";
Activity act;
    public JSONArray ja;
    public JSONObject jo;
    public int len=0;
    public StringBuilder str = new StringBuilder();
    public boolean flag =false;
    TabApi(Activity c)
    {
this.act=c;
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

            connection.connect();
            int i=connection.getResponseCode();
            Log.e("Input Line 0", String.valueOf(i));

            Log.e("Input Line","Hello");

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
            ja=new JSONArray(br.toString());
            len=ja.length();




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



        return "Hello";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        str.append("Featured item");
        for(int i=0;i<ja.length();i++)
        {
            try {


                jo = ja.getJSONObject(i);
                Log.e("String ",jo.getString("name"));
                str.append(",");
                str.append(jo.getString("name"));
            }catch (JSONException e)
            {

            }
        }
        String sub[]=str.toString().split(",");
        Bundle bundel = new Bundle();
        bundel.putStringArray("key",sub);

        Intent intent=new Intent(act,GridActivity.class);
        intent.putExtras(bundel);
        act.finish();
        act.startActivity(intent);


    }
}
