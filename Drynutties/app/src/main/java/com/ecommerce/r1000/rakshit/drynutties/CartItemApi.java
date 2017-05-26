package com.ecommerce.r1000.rakshit.drynutties;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
 * Created by Rakshit on 1/7/2016.
 */
public class CartItemApi extends AsyncTask<String,String,String> {
    public cartFragment.CartItemAdapter adp;
    private RecyclerView mRecyclerView;
    public     String link="http://192.168.137.1/drynutties/CartItem_Api.php";
    public String u="http://192.168.137.1/drynutties/images/cart/";
    public ArrayList<String> name=new ArrayList<String>();
    public ArrayList<String> img=new ArrayList<String>();
    public ArrayList<String>     wgt=new ArrayList<String>();
    public ArrayList<String>     price=new ArrayList<String>();
    public ArrayList<String>     qnty=new ArrayList<String>();
    public ArrayList<String>     pid=new ArrayList<String>();
    StringBuilder result=new StringBuilder();
    public Context con;
    public LinearLayout lp;
    public JSONObject jo;
    public JSONArray ja;
    public View root;
    public TextView tv;
    public TextView tv2;
    public TextView tv3;
    public TextView tv4;





    CartItemApi(Context c,String n, cartFragment.CartItemAdapter o,RecyclerView r,LinearLayout l,View vi)
    {
        this.con=c;
        this.adp=o;
        this.lp=l;
        this.mRecyclerView=r;
        this.root=vi;

        try {
            result.append("&");
            result.append(URLEncoder.encode("uid", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(n, "UTF-8"));

        }
        catch (UnsupportedEncodingException e)
        {
            Log.e("Error","Detected");
        }

    }
    @Override
    protected String doInBackground(String... strings)
    {
        try
        {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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

            Log.e("Cart Api Connector", "Done");


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
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
        try {
            for (int i = 0; i < ja.length(); i++) {

                jo = ja.getJSONObject(i);
                Log.e("String", jo.toString());
                name.add(i, jo.getString("name"));
                wgt.add(i, jo.getString("weight"));
                img.add(i, u+jo.getString("img"));
                price.add(i,jo.getString("price"));
                qnty.add(i,jo.getString("quantity"));
                pid.add(i,jo.getString("product_id"));



            }
        }catch (JSONException e)
        {

        }
        mRecyclerView.setAdapter(adp);
        tv=(TextView)root.findViewById(R.id.csubt);
        double amt=0;
        for(int j=0;j<price.size();j++)
        {
            amt=amt+(Float.parseFloat(price.get(j))*Float.parseFloat(qnty.get(j)));
        }

        tv.append("\n₹"+String.valueOf(amt));
        tv2=(TextView)root.findViewById(R.id.scost);
        Double str;
        if(amt<650.0)
            str=130.0;
        else
            str=0.0;
        tv2.append("\n₹"+String.valueOf(str));
        tv3=(TextView)root.findViewById(R.id.gtotal);
        tv4=(TextView)root.findViewById(R.id.discn);
        tv3.append("\n₹"+String.valueOf(amt+str));
        tv4.append("\n₹0.0");


    }
}
