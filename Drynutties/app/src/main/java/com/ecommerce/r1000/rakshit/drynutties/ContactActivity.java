package com.ecommerce.r1000.rakshit.drynutties;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class ContactActivity extends ActionBarActivity implements contactFragment.OnFragmentInteractionListener {
    String navTitle[]={"Home","About Us","Contact Us","Policy","Terms & Condition","Developer","Logout"};
    String NA = "Rakshit";
    String EMAIL = "rakshitj21@gmail.comm";
    public Toolbar toolbar;                              // Declaring the Toolbar Object
    DrawerLayout Drawer;
    public RecyclerView rv;
    SharedPreferences sp;

    int img[]={R.drawable.home,R.drawable.about,R.drawable.contact,R.drawable.setting,R.drawable.setting,R.drawable.setting,R.drawable.devep,R.drawable.log};
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle
    int PROFILE = R.drawable.welcome;
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setIcon(R.drawable.welcome);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new contactFragment())
                .commit();
        sp=getSharedPreferences("detail",MODE_PRIVATE);

        rv = (RecyclerView) findViewById(R.id.draw); // Assigning the RecyclerView Object to the xml View
        rv.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new MyNavAdapter(this,navTitle,img,  sp.getString("userName","Rakshit"),sp.getString("userEmail","rakshitj21@gmail.com"), PROFILE,1);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view

        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, Drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void setToolbarNavigationClickListener(View.OnClickListener onToolbarNavigationClickListener) {
                super.setToolbarNavigationClickListener(onToolbarNavigationClickListener);
                finish();
            }

            @Override
            public View.OnClickListener getToolbarNavigationClickListener() {
                finish();
                return super.getToolbarNavigationClickListener();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }


        }; // Drawer Toggle Object Made
        Drawer.post(
                new Runnable() {
                    @Override
                    public void run() {
                        mDrawerToggle.syncState();

                    }
                });
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle



        //      ArrayAdapter adapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,scr);
    //    setListAdapter(adapter);
    }
/*
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {

        super.onListItemClick(l, v, position, id);
        if(scr[position].equals("PHONE CALL"))
        {
            Intent i= new Intent(Intent.ACTION_DIAL, Uri.parse("tel:07830464465"));
            startActivity(i);
        }
        else if(scr[position].equals("SMS"))
        {
            Intent i= new Intent(Intent.ACTION_SENDTO,Uri.parse("sms:07830464465"));
            i.putExtra("sms_body","Message Generated for enquiry :\n Subject : ");
            startActivity(i);
        }
        else if(scr[position].equals("Email"))
        {
            Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "info@drynutties.com"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Enquiry Subject :");
            intent.putExtra(Intent.EXTRA_TEXT, "Enter the details :");
            startActivity(intent);
        }

    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact, menu);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_cart)
        {
            CartCheckapi ob=new CartCheckapi(this,sp.getString("userId","1"),1);
            ob.execute();

            int time=7000;
            Toast.makeText(this, "Loading....", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
