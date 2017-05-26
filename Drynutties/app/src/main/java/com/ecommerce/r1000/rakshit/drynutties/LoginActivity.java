package com.ecommerce.r1000.rakshit.drynutties;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class LoginActivity extends ActionBarActivity implements  loginViewFragment.OnFragmentInteractionListener,registerFragment.OnFragmentInteractionListener{
int j=0;
    public TabApi ob;
    public String name[];
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new loginViewFragment())
                .commit();

       // ob=new TabApi(this);


        try {
            final ImageView ImageView1 = (ImageView) findViewById(R.id.imageView3);
            ImageView1.setBackgroundResource((int)R.drawable.flag1);
            final AnimationDrawable frameAnimation = (AnimationDrawable) ImageView1.getBackground();
           ImageView1.post(new Runnable() {
                @Override
                public void run() {
                    frameAnimation.start();
                }
            });



            final ImageView ImageView2 = (ImageView) findViewById(R.id.imageView);
            ImageView2.setBackgroundResource((int)R.drawable.flag2);
            final AnimationDrawable frameAnimation2 = (AnimationDrawable) ImageView2.getBackground();
            ImageView2.post(new Runnable() {
                @Override
                public void run() {
                    frameAnimation2.start();
                }
            });

            final ImageView ImageView3 = (ImageView) findViewById(R.id.imageView2);
            ImageView3.setBackgroundResource((int)R.drawable.flag3);
            final AnimationDrawable frameAnimation3 = (AnimationDrawable) ImageView3.getBackground();
            ImageView3.post(new Runnable() {
                @Override
                public void run() {
                    frameAnimation3.start();
                }
            });

            final ImageView ImageView4 = (ImageView) findViewById(R.id.imageView4);
            ImageView4.setBackgroundResource((int)R.drawable.flag4);
            final AnimationDrawable frameAnimation4 = (AnimationDrawable) ImageView4.getBackground();
            ImageView4.post(new Runnable() {
                @Override
                public void run() {
                    frameAnimation4.start();
                }
            });
            final ImageView ImageView5 = (ImageView) findViewById(R.id.imageView5);
            ImageView5.setBackgroundResource((int)R.drawable.flag2);
            final AnimationDrawable frameAnimation5 = (AnimationDrawable) ImageView5.getBackground();
            ImageView5.post(new Runnable() {
                @Override
                public void run() {
                    frameAnimation5.start();
                }
            });

            Thread t=new Thread()
            {
                public void run()
                {

                    try {
                        while (j < 6) {

                            Thread.sleep(2000);
                            j++;
                        }
         //               ob.execute();
                    }  catch(InterruptedException e)
                    {

                    }


                }
            };
            t.start();

        }
        catch(Exception i)
        {

        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
}
