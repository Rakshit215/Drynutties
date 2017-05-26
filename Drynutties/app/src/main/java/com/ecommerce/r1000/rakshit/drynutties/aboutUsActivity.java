package com.ecommerce.r1000.rakshit.drynutties;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class aboutUsActivity extends ActionBarActivity {

    public RecyclerView mRecyclerView,r;
    public RecyclerView rv;
    public RecyclerView.LayoutManager mLayoutManager,m;
    String navTitle[]={"Home","About Us","Contact Us","Policy","Terms & Condition","Developer","Logout"};
    String NA = "Rakshit";
    String EMAIL = "rakshitj21@gmail.comm";
    public Toolbar toolbar;                              // Declaring the Toolbar Object
    DrawerLayout Drawer;

    public String name[]={"Aviral Sharma","Anush Swaminathan"};
    public String post[]={"Founder","Co-Founder"};
    public String cont[]={"The Main aim of Opening Drynutties was to Provide High Quality Dry Fruits all over India at a reasonable rate.","Dry Fruits Consumed by almost Everyone in India lacks the Presence of a Market Providing High Quality Products at Low Rates."};
    String fontPath = "fonts/at.ttf";
SharedPreferences sp;
    int img[]={R.drawable.home,R.drawable.about,R.drawable.contact,R.drawable.setting,R.drawable.setting,R.drawable.setting,R.drawable.devep,R.drawable.log};
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle
    int PROFILE = R.drawable.welcome;
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setIcon(R.drawable.welcome);

        mRecyclerView = (RecyclerView)findViewById(R.id.about);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        sp=getSharedPreferences("detail",MODE_PRIVATE);

        mRecyclerView.setAdapter(new aboutUsAdapter(this));



        r = (RecyclerView)findViewById(R.id.about2);
        r.setHasFixedSize(true);
        r.setLayoutManager(new LinearLayoutManager(this));
       m = new LinearLayoutManager(this);
       r.setLayoutManager(m);

      r.setAdapter(new aboutAdapter(this));
        rv = (RecyclerView) findViewById(R.id.draw); // Assigning the RecyclerView Object to the xml View
        rv.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size
        sp=getSharedPreferences("detail",MODE_PRIVATE);

        mAdapter = new MyNavAdapter(this,navTitle,img, sp.getString("userName","Rakshit"),sp.getString("userEmail","rakshitj21@gmail.com"), PROFILE,1);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
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

    public class aboutUsAdapter extends RecyclerView.Adapter<aboutUsAdapter.CustomViewHolder>

    {

        private Context mContext;
        public TextView title;
        public TextView po;
        public TextView matter;
        public LinearLayout lm;


        public aboutUsAdapter(Context context)
        {

            this.mContext = context;
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {

            public CustomViewHolder(View view) {
                super(view);
                Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

                // Applying font
                title=(TextView)view.findViewById(R.id.head);
                po=(TextView)view.findViewById(R.id.post);
                matter=(TextView)view.findViewById(R.id.matter);
                matter.setTypeface(tf);

                lm=(LinearLayout)view.findViewById(R.id.back);

            }
        }

        public  CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.about_single, null);

            CustomViewHolder viewHolder = new CustomViewHolder(view);

            return viewHolder;
        }



        @Override
        public void onBindViewHolder(CustomViewHolder customViewHolder, int i)
        {
                lm.setBackgroundColor(Color.parseColor("#ff4c18"));
            title.setText(name[i]);
            po.setText(post[i]);

            matter.setText(cont[i]);

        }



        @Override
        public int getItemCount()
        {
            return 1;
        }



    }
    public class aboutAdapter extends RecyclerView.Adapter<aboutAdapter.CustomViewHolder>

    {

        private Context mContext;
        public TextView title;
        public TextView po;
        public TextView matter;
        public LinearLayout lm;


        public aboutAdapter(Context context)
        {

            this.mContext = context;
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {

            public CustomViewHolder(View view) {
                super(view);
                Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);


                title=(TextView)view.findViewById(R.id.head);
                po=(TextView)view.findViewById(R.id.post);
                matter=(TextView)view.findViewById(R.id.matter);
                matter.setTypeface(tf);

                lm=(LinearLayout)view.findViewById(R.id.back);

            }
        }

        public  CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.about_single, null);

            CustomViewHolder viewHolder = new CustomViewHolder(view);

            return viewHolder;
        }



        @Override
        public void onBindViewHolder(CustomViewHolder customViewHolder, int i)
        {
                lm.setBackgroundColor(Color.parseColor("#4ba321"));

            title.setText(name[i+1]);
            po.setText(post[i+1]);
            matter.setText(cont[i+1]);
        }



        @Override
        public int getItemCount()
        {
            return 1;
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about_us, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }


        int id = item.getItemId();
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
