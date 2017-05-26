package com.ecommerce.r1000.rakshit.drynutties;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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
 * Created by Rakshit on 1/12/2016.
 */
public class TransactionSuccess extends AsyncTask<String,String,String>
{
    JSONObject jo=null;
    Activity con;
    StringBuilder result;
    public SharedPreferences sp;
    public SharedPreferences.Editor ed;
public     String link="http://192.168.137.1/drynutties/TransacUpdate_api.php";
public    String line = "abc";



    public TransactionSuccess(Activity c,String j)
    {
     this.con=c;
        result=new StringBuilder();
        sp=con.getSharedPreferences("detail",c.MODE_PRIVATE);
ed=sp.edit();
        try
        {
            jo=new JSONObject(j);
            result.append("&");
            result.append(URLEncoder.encode("name", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(sp.getString("name","null"), "UTF-8"));

            result.append("&");
            result.append(URLEncoder.encode("email", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(sp.getString("email","null"), "UTF-8"));

            result.append("&");
            result.append(URLEncoder.encode("uid", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(sp.getString("userId","null"), "UTF-8"));

            result.append("&");
            result.append(URLEncoder.encode("phn", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(sp.getString("phn","null"), "UTF-8"));

            result.append("&");
            result.append(URLEncoder.encode("code", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(sp.getString("disc","null"), "UTF-8"));


            result.append("&");
            result.append(URLEncoder.encode("ship_add", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(sp.getString("ship_add","null"), "UTF-8"));


            result.append("&");
            result.append(URLEncoder.encode("ship_city", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(sp.getString("ship_city","null"), "UTF-8"));


            result.append("&");
            result.append(URLEncoder.encode("ship_state", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(sp.getString("ship_state","null"), "UTF-8"));

            result.append("&");
            result.append(URLEncoder.encode("ship_zip", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(sp.getString("ship_zip","null"), "UTF-8"));

            result.append("&");
            result.append(URLEncoder.encode("amt", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(jo.getString("amount"), "UTF-8"));


            result.append("&");
            result.append(URLEncoder.encode("cod", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode("NO", "UTF-8"));

            result.append("&");
            result.append(URLEncoder.encode("status", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(jo.getString("TxStatus"), "UTF-8"));


        }catch (JSONException e)
        {

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
            StringBuilder br=new StringBuilder();

            while ((line=rd.readLine()) != null)
            {
                Log.e("Input Line", line);
                br.append(line);

            }
line=br.toString();
            Toast.makeText(con, br, Toast.LENGTH_LONG).show();



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
 ed.remove("amt");
        ed.remove("name");
        ed.remove("email");
        ed.remove("phn");
        ed.remove("ship_add");
        ed.remove("ship_city");
        ed.remove("ship_state");
        ed.remove("ship_zip");

ed.commit();
        Toast.makeText(con,line,Toast.LENGTH_LONG).show();
        con.finish();
    }
}