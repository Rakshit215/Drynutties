package com.ecommerce.r1000.rakshit.drynutties;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
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
public class discountApi extends AsyncTask<String,String,String>
{
    public Activity con;
    public String cpn;
    public String uid;
    public String link="http://192.168.137.1/drynutties/discount_api.php";
public StringBuilder result;
    public         String line;
    public JSONArray ja;
    public JSONObject jo;
    public TextView disc;
    public TextView desc;
    public TextView cart;
    public TextView gto;
    public View root;
    public SharedPreferences sp;
    public SharedPreferences.Editor ed;

    public discountApi(Activity c,String cpn,View r)
    {
        this.con=c;
        this.cpn=cpn;
        this.uid=uid;
        this.root=r;

        sp=con.getSharedPreferences("detail",con.MODE_PRIVATE);
        ed=sp.edit();
        disc=(TextView)root.findViewById(R.id.discn);
        desc=(TextView)root.findViewById(R.id.discndet);
        cart=(TextView)root.findViewById(R.id.csubt);
        gto=(TextView)root.findViewById(R.id.gtotal);
        result=new StringBuilder();
        try {
            result.append("&");
            result.append(URLEncoder.encode("cpn", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(cpn, "UTF-8"));

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

                //Toast.makeText(getApplicationContext(), "I enter here", Toast.LENGTH_LONG).show();
            }
jo=new JSONArray(br.toString()).getJSONObject(0);




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
        String dsc,discount,te;
      String gtotal,gup;
        float f,fn,j,a;
        int num;
        String sub;
        try
        {
            if(jo!=null) {
                sub = cart.getText().toString();
                te = disc.getText().toString();
                sub = sub.substring(sub.indexOf("₹") + 1);
                f = Float.parseFloat(sub);
                dsc = jo.getString("description");
                discount = jo.getString("discount");
                num = (int) Float.parseFloat(discount);
                a = f * num / 100;
                f = f - a;
                te = te.substring(0, te.indexOf("₹") + 1);
                te += String.valueOf(a);
                disc.setText(te);
                desc.setText(dsc);

                gtotal = gto.getText().toString();
                gup = gto.getText().toString();
                gup = gup.substring(0, gup.indexOf("₹") + 1);
                gtotal = gtotal.substring(gtotal.indexOf("₹") + 1);

                fn = Float.parseFloat(gtotal);
                j = fn - a;
                ed.putString("disc", jo.getString("code"));
                ed.commit();
                //   disc.append("₹" + String.valueOf(j));
                gto.setText(gup + String.valueOf(j));


            }
            else
            {
                Toast.makeText(con,"Wrong Code",Toast.LENGTH_LONG).show();
            }
        }
        catch (JSONException e)
        {

        }

    }
}
