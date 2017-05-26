package com.ecommerce.r1000.rakshit.drynutties;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
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
 * Created by Rakshit on 1/8/2016.
 */
public class CartCheckapi extends AsyncTask<String, String, String>
{

public String link="http://192.168.137.1/drynutties/Cart_item.php";
        Activity act;
public JSONArray ja;
public JSONObject jo;
public int len=0;
public StringBuilder str = new StringBuilder();
public boolean flag =false;
        StringBuilder result;
        String msj;
public  String line;
   public int f=0;

        CartCheckapi(Activity c,String q,int f)

        {
        this.act=c;
        result=new StringBuilder();
            this.f=f;
        try {
        result.append("&");
        result.append(URLEncoder.encode("uid", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(q, "UTF-8"));
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
       line=br.toString();




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


if(line.indexOf("null")>-1)
{
    Intent in=new Intent(act,EmptyCartActivity.class);
    if(f==1)
     act.finish();
    act.startActivity(in);

}
  else
{
    Intent in=new Intent(act,CartActivity.class);
    if(f==1)
        act.finish();
    act.startActivity(in);
}


}
        }


