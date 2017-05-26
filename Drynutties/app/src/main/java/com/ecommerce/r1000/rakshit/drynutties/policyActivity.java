package com.ecommerce.r1000.rakshit.drynutties;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class policyActivity extends ActionBarActivity
{
    String navTitle[]={"Home","About Us","Contact Us","Policy","Terms & Condition","Developer"};
    String NA = "Rakshit";
    String EMAIL = "rakshitj21@gmail.comm";
    public Toolbar toolbar;                              // Declaring the Toolbar Object
    DrawerLayout Drawer;
    public RecyclerView rv;
    SharedPreferences sp;

    int img[]={R.drawable.home,R.drawable.about,R.drawable.contact,R.drawable.setting,R.drawable.setting,R.drawable.setting,R.drawable.devep};
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle
    int PROFILE = R.drawable.welcome;
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View



    public String matter[]={"We don’t disclose any personal information like emails, phone nos., etc. to any third party. You will not have a mail or call if it doesn’t intend to inform you about your orders. In case you opt for the promotional mailing, then and only then promotional mails will be sent.","We currently ship to all tier 1 and tier 2 cities across India\nWe ship items within 72 hours of order placement.\nIn the unlikely event that we are not able to ship your order completely within 3 working days of the order, we shall cancel the remaining unshipped part of the order, and send you an email informing you about the same. In such cases, your payment against the unshipped part of the order shall be refunded, in the manner you have made the payment.\nTo ensure that your order reaches you in the fastest time and in good condition, we only ship through reputed courier agencies.\nWhile we shall Endeavour to ship all items in your order together, this may not always be possible due to product characteristics, or availability.\nIf you believe that the product is not in good condition, or if the packaging is tampered with or damaged, please refuse to take delivery of the package and email us at info@drynutties.com, mentioning your order reference number. We shall make our best efforts to ensure that a replacement delivery is made to you at the earliest.\nPlease note all items (including gifts) will be shipped with an invoice mentioning the price, as per Indian Tax Regulations"};
    public RecyclerView mRecyclerView,r;
    public RecyclerView.LayoutManager mLayoutManager,m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setIcon(R.drawable.welcome);
        sp=getSharedPreferences("detail",MODE_PRIVATE);

        TextView tv=(TextView)findViewById(R.id.priv);
        TextView tv2=(TextView)findViewById(R.id.ship);

        tv.setText(matter[0]);
        tv2.setText(matter[1]);

        rv = (RecyclerView) findViewById(R.id.draw); // Assigning the RecyclerView Object to the xml View
        rv.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

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
        getMenuInflater().inflate(R.menu.menu_policy, menu);
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

        if (id == R.id.action_cart)
        {
            CartCheckapi ob=new CartCheckapi(this,sp.getString("userId","1"),1);
            ob.execute();

            int time=7000;
            Toast.makeText(this, "Loading....", Toast.LENGTH_LONG).show();
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
