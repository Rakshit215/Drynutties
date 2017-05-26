package com.ecommerce.r1000.rakshit.drynutties;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class SplashActivity extends ActionBarActivity implements Animation.AnimationListener {
    Animation animFadein;
    Animation animZoomout;
    ConnectionDetector cd;
    Boolean isInternetPresent;
    Animation animSequenceTrans;
    ImageView wlcmImage;
    TextView wlcmMessage;
    ProgressBar pb;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    public          String s;
int i=0;
    int j=0;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();



        sp = getSharedPreferences("detail", MODE_PRIVATE);
        ed = sp.edit();
s="0";
        s=sp.getString("status","0");



        cd = new ConnectionDetector(getApplicationContext());

        isInternetPresent= cd.isConnectingToInternet();
        if (isInternetPresent) {
            flag = 1;
        }
        else
        {
            Toast.makeText(SplashActivity.this,"Network Not available",Toast.LENGTH_LONG).show();

        }
            animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        animSequenceTrans = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.sequence_trans);
        animZoomout = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_out);

        wlcmMessage = (TextView) findViewById(R.id.wlcmtext);
        wlcmImage=(ImageView)findViewById(R.id.welcomeimg);
pb=(ProgressBar)findViewById(R.id.progressBar);
        animFadein.setAnimationListener(this);


        wlcmImage.setVisibility(View.VISIBLE);
        wlcmImage.startAnimation(animFadein);


        // start the animation



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAnimationStart(Animation animation) {



    }

    @Override
    public void onAnimationEnd(Animation animation) {

        if (animation == animFadein) {

        }
        if(i==0)
        {
            wlcmImage.clearAnimation();
            i++;
            wlcmMessage.setVisibility(View.VISIBLE);
            wlcmMessage.startAnimation(animFadein);


        }
        else if(i==1) {
            i++;
            pb.setVisibility(View.VISIBLE);
wlcmMessage.clearAnimation();
            animSequenceTrans.setAnimationListener(this);

            wlcmImage.startAnimation(animSequenceTrans);


        }

else if (i==2)
        {
            i++;
            Thread t=new Thread()
            {
                public void run()
                {

                    try
                    {
                        while(j<6)
                        {

                            Thread.sleep(2000);
                            j++;
                        }
                        if (flag==1) {
                            Intent intent=null;
                            if(s.equalsIgnoreCase("0"))
                            {

                                intent = new Intent(SplashActivity.this, LoginActivity.class);
                                finish();
                                startActivity(intent);
                            }
                            else
                            {
                               new TabApi(SplashActivity.this).execute();

                            }



                        }
                        else{
                            finish();
                        }

                    }
                    catch(InterruptedException e)
                    {

                    }
                }
            };
            t.start();



        }

            }
        @Override
        public void onAnimationRepeat (Animation animation){


        }

    }
