package com.ecommerce.r1000.rakshit.drynutties;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
import java.util.ArrayList;

/**
 * Created by Rakshit on 1/4/2016.
 */
public class ElementsApi extends  AsyncTask<String, String, String>
{
    public JSONArray ja;
    public JSONObject json=null;
    public     String link="http://192.168.137.1/drynutties/Elementlist_api.php";
    public String u="http://192.168.137.1/drynutties/images/shop/";

    public RecyclerView mRecyclerView;
    public Context con;
    public String position;
    public ElementFragment.EventsAdapter adapter;

    public ArrayList<String> name=new ArrayList<String>();
    public ArrayList<String> img=new ArrayList<String>();
    public ArrayList<String>     wgt=new ArrayList<String>();
    public ArrayList<String>     price=new ArrayList<String>();
    StringBuilder result=new StringBuilder();


    ElementsApi(Context c,RecyclerView r,String pos)
    {
        this.con=c;
        this.mRecyclerView=r;
        this.position=pos;
        try {
            result.append("&");
            result.append(URLEncoder.encode("tab", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(position, "UTF-8"));
            Log.e("Error",position);
        }
        catch (UnsupportedEncodingException e)
        {
            Log.e("Error","Detected");
        }

    }

    @Override
    protected String doInBackground(String... strings) {
        InputStream in;
        Bitmap icon=null;

        try
        {
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

            Log.e("Api Connector", "two");
            in=new URL(link).openStream();
            icon= BitmapFactory.decodeStream(in);


connection.disconnect();
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
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
try {
    for (int i = 0; i < ja.length(); i++) {

        json = ja.getJSONObject(i);
        Log.e("String", json.toString());
        name.add(i, json.getString("name"));
        wgt.add(i, json.getString("weight"));
        img.add(i, u+json.getString("img"));
        price.add(i,json.getString("price"));


    }
}catch (JSONException e)
{

}
        mRecyclerView.setAdapter(adapter);

    }


}
