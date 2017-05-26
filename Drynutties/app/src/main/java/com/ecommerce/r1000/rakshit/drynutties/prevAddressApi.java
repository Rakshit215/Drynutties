package com.ecommerce.r1000.rakshit.drynutties;

import android.app.Activity;
import android.content.Intent;
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
 * Created by Rakshit on 1/13/2016.
 */
public class prevAddressApi extends AsyncTask<String,String,String>
{
    public Activity con;
    public String link="http://192.168.137.1/drynutties/AddCart_api.php";
    public  StringBuilder result;
    public String line;
    public String uid;

    public ArrayList<String> name=new ArrayList<String>();
    public ArrayList<String> email=new ArrayList<String>();
    public ArrayList<String>     phno=new ArrayList<String>();
    public ArrayList<String>     ship_ad=new ArrayList<String>();
    public ArrayList<String>     ship_city=new ArrayList<String>();

    public ArrayList<String>     ship_state=new ArrayList<String>();
    public ArrayList<String>     ship_zip=new ArrayList<String>();
    public JSONArray ja;
    public JSONObject jo;
public RecyclerView mview;
    public addressFragment.addressAdapter lm;
    public prevAddressApi(Activity c,String uid,RecyclerView r,addressFragment.addressAdapter l)
    {
        this.con=c;
        this.uid=uid;
        this.mview=r;
        this.lm=l;

result=new StringBuilder();
        try {
            result.append("&");
            result.append(URLEncoder.encode("uid", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(uid, "UTF-8"));
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
            line= "abc";
            StringBuilder br=new StringBuilder();

            while ((line=rd.readLine()) != null)
            {
                Log.e("Input Line", line);
                br.append(line);

                //Toast.makeText(getApplicationContext(), "I enter here", Toast.LENGTH_LONG).show();
            }
            ja=new JSONArray(br.toString());




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
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
        try {
            for (int i = 0; i < ja.length(); i++)
            {

                jo = ja.getJSONObject(i);
                name.add(i, jo.getString("name"));
                email.add(i, jo.getString("email"));
                phno.add(i, jo.getString("phno"));
                ship_ad.add(i,jo.getString("shipping_address"));
                ship_city.add(i,jo.getString("shipping_city"));
                ship_state.add(i,jo.getString("shipping_state"));
                ship_zip.add(i,jo.getString("shipping_zip"));


            }
        }catch (JSONException e)
        {
Log.e("Exception",e.toString());
        }

                mview.setAdapter(lm);




    }
}
