package com.ecommerce.r1000.rakshit.drynutties;

import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class EmptyCartActivity extends ActionBarActivity
{
    String TITLES[];
    String navTitle[]={"Home","About Us","Contact Us","Policy","Terms & Condition","Developer","Logout"};
    String NA = "Rakshit";
    String EMAIL = "rakshitj21@gmail.comm";
    int PROFILE = R.drawable.welcome;
    public Toolbar toolbar;                              // Declaring the Toolbar Object
    SharedPreferences sp;
    public RecyclerView rv;
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;
    ViewPager mViewPager;// Declaring DrawerLayout
    int img[]={R.drawable.home,R.drawable.about,R.drawable.contact,R.drawable.setting,R.drawable.setting,R.drawable.setting,R.drawable.devep,R.drawable.log};
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_cart);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setIcon(R.drawable.welcome);

        rv = (RecyclerView) findViewById(R.id.draw); // Assigning the RecyclerView Object to the xml View
        rv.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size
        sp=getSharedPreferences("detail",MODE_PRIVATE);

        mAdapter = new MyNavAdapter(this,navTitle,img,sp.getString("userName","Rakshit"),sp.getString("userEmail","rakshitj21@gmail.com"), PROFILE,1);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
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




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_empty_cart, menu);
        return true;
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

        return super.onOptionsItemSelected(item);
    }
}
