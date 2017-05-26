package com.ecommerce.r1000.rakshit.drynutties;

import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class termConActivity extends ActionBarActivity {

    public TextView tv;
    public String str="When you use the www.drynutties.com, it is understood that the usage is subject to the rules and regulations set forth in this agreement (the \"Agreement\"). In addition, when you use any www.drynutties.com’s service (e.g., Client Reviews), you will be subject to the rules, guidelines, policies, terms, and conditions applicable to such service, and they are incorporated into this Agreement by this reference. www.drynutties.com and Soft Computing India Pvt. Ltd. reserves the right to change these terms and conditions at any time. ACCESSING, BROWSING OR OTHERWISE USING THE SITE INDICATES YOUR AGREEMENT TO ALL THE TERMS AND CONDITIONS IN THIS AGREEMENT, SO PLEASE READ THIS AGREEMENT CAREFULLY BEFORE PROCEEDING.<br><br><b>1. Reviews and Comments</b><br><br>Except as otherwise provided elsewhere in this Agreement or on the Site, anything that anyone posts to the Site and/or provide, including without limitation, ideas, know-how, techniques, questions, reviews, comments, and suggestions (collectively, \"Submissions\") is and will be treated as non-confidential and non-proprietary, and drynutties.com or Soft Computing India Pvt. Ltd. shall have the royalty-free,  perpetual, irrevocable and transferable right to use, copy, distribute, display, publish, perform, sell, lease, transmit, adapt, create derivative works from such Submissions by any means and in any form, and to translate, modify, reverse-engineer, disassemble, or decompile such Submissions. All Submissions shall automatically become the sole and exclusive property of Drynutties.com and shall not be returned to you.<br><br><b>2. Risk of Loss & Other Terms of Sale</b><br><br>The risk of loss and title for items purchased by you is passed to you upon drynutties.com's delivery of the items to the carrier pursuant to drynutties.com's standard terms of sale.<br><br><b>3. Termination and Effect of Termination</b><br><br>In addition to any other legal or equitable remedies, drynutties.com or Soft Computing India Pvt ltd. may, without prior notice to you, immediately terminate the Agreement or revoke any or all of your rights granted under this Agreement.<br><br><b>4. International Access</b><br><br>This Site may be accessed from countries other than India. This Site may contain products or references to products that are not available outside of India. Any such references do not imply that such products will be made available outside India. If you access and use this Site outside India you are responsible for complying with your local laws and regulations. Moreover, we ship only in India at present. Thus any compliance with international orders is not entertained at present.<br><br><b>5. Disclaimer and Limitation of Liability</b><br><br>EXCEPT AS OTHERWISE PROVIDED IN THE STANDARD TERMS OF SALE THAT GOVERN THE SALE OF EACH PRODUCT ON THIS SITE, THIS SITE, THE PRODUCTS OFFERED FOR SALE ON IT AND THE TRANSACTIONS CONDUCTED THROUGH IT ARE PROVIDED BY drynutties.COM ON AN \"AS IS\" BASIS. drynutties.COM MAKES NO REPRESENTATIONS OR WARRANTIES OF ANY KIND, EXPRESS OR IMPLIED, AS TO THE OPERATION OF THE SITE OR THE INFORMATION, CONTENT, MATERIALS, OR PRODUCTS INCLUDED ON THIS SITE. TO THE FULL EXTENT PERMISSIBLE BY APPLICABLE LAW, drynutties.COM and Soft Computing India Pvt ltd. DISCLAIMS ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE, NONINFRINGEMENT, TITLE, QUIET ENJOYMENT, DATA ACCURACY AND SYSTEM INTEGRATION. THIS SITE MAY INCLUDE INACCURACIES, MISTAKES OR TYPOGRAPHICAL ERRORS. DRYNUTTIES.COM and Soft Computing India Pvt. Ltd. DOES NOT WARRANT THAT THE CONTENT WILL BE UNINTERRUPTED OR ERROR FREE.<br><br><b>6. Typographical Errors</b><br><br>In the event a product is listed at an incorrect price or with incorrect information due to typographical error or error in pricing or product information received from our suppliers, drynutties.com & Soft Computing India Pvt. Ltd. shall have the right to refuse or cancel any orders placed for product listed at the incorrect price. drynutties.com shall have the right to refuse or cancel any such orders whether or not the order has been confirmed and your credit card charged. If your credit card has already been charged for the purchase and your order is canceled, Soft Computing India Pvt. Ltd. shall immediately issue a credit to your credit card account in the amount of the charge.<br><br><b>7. Links</b><br><br>This site may contain links to other sites on the Internet that are owned and operated by third parties. You acknowledge that Soft Computing India Pvt. Ltd. And Drynutties.com is not responsible for the operation of or content located on or through any such site.<br><br><b>8. Copyright complaints</b><br><br>drynutties.com respects the intellectual property of others. If you believe that your work has been copied in a way that constitutes copyright infringement, please contact us at info@drynutties.com.<b><br><br>9. Remedies</b><br><br>You agree that drynutties.com's remedy at law for any actual or threatened breach of this Agreement would be inadequate and that DRYNUTTIES.com shall be entitled to specific performance or injunctive relief, or both, in addition to any damages that drynutties.com may be legally entitled to recover, together with reasonable expenses of any form of dispute resolution, including, without limitation, attorneys' fees.<br>No right or remedy of drynutties.com shall be exclusive of any other, whether at law or in equity, including without limitation damages injunctive relief, attorneys' fees and expenses.<br>No instance of waiver by drynutties.com of its rights or remedies under these terms and conditions shall imply any obligation to grant any similar, future or other waiver.<br><br><b>10. Entire Agreement</b><br><br>If any part of this agreement is determined to be invalid or unenforceable pursuant to applicable law including, but not limited to, the warranty disclaimers and liability limitations set forth above, then the invalid or unenforceable provision will be deemed to be superseded by a valid, enforceable provision that most closely matches the intent of the original provision and the remainder of the agreement shall continue in effect. Unless otherwise specified herein, this agreement constitutes the entire agreement between you and drynutties.com with respect to the drynutties.com sites/services and it supersedes all prior or contemporaneous communications and proposals, whether electronic, oral or written, between you and drynutties.com with respect to the drynutties.com sites/services. drynutties.com's failure to act with respect to a breach by you or others does not waive its right to act with respect to subsequent or similar breaches.<br><br><b>11. Site Security</b><br><br>You are prohibited from violating or attempting to violate the security of the Site, including, without limitation, (a) accessing data not intended for you or logging onto a server or an account which you are not authorized to access; (b) attempting to perform a penetration testing without written consent of Soft Computing India Pvt. Ltd.; (c) attempting to interfere with service to any other user, host or network, including, without limitation, via means of submitting a virus to the Site, overloading, \"flooding,\" \"spamming,\" \"mail bombing\" or \"crashing;\" (d) sending unsolicited email, including promotions and/or advertising of products or services; or (e) forging any TCP/IP packet header or any part of the header information in any email or newsgroup posting. Violations of system or network security may result in civil or criminal liability.<br><br><b>12. Objectionable material</b><br><br>It should be noted that drynutties.com is purely an e-shopping website for sale of nuts of all forms. We do not post or promote use any sort objectionable material on our website.<br><br><b>14. Copyright & Trademark</b><br><br>drynutties.com and its suppliers and licensors solely reserve all intellectual property rights in all text, programs, products, processes, technology, content and other materials, which appear on this Site. Access to this Site does not confer and shall not be considered as conferring upon anyone any license under any of drynutties.com or any third party's intellectual property rights.<br><br><b>15.  Disputes and Jurisdiction</b><br><br>In case of any breach of terms or violation of the Agreement, you agree to notify drynutties.com or Soft Computing India Pvt. Ltd. in private about the possible breach and give it at least 90 days to investigate and rectify any such breach or violations. In an unlikely event of failure by drynutties.com to do so, you agree to serve Soft Computing India Pvt. Ltd. a legal notice clearly mentioning and evidencing such breach and give Soft Computing India Pvt. Ltd. minimum 90 further days to explain the cause and rectify the breach (if applicable and under the control of drynutties.com and Soft Computing India). This should be sent to us over email: info@drynutties.com<br>In case of a settlement not having been reached, you may initiate legal action or lodge a complaint against drynutties.com at the appropriate Court of Law or authorities like the Consumer Court. This Agreement shall be construed in accordance with the applicable Laws and Constitution the Republic of India and is subject to the exclusive jurisdiction of the Honorable Courts of Jammu and Kashmir only.<br><br><b>16. Indemnity</b><br><br>You agree to defend, indemnify and hold harmless drynutties.com, its employees, directors, officers, agents, representatives, business partners, service providers and their successors and assigns from and against any and all claims, liabilities, damages, losses, costs and expenses, including attorney's fees, caused by or arising out of claims based upon your actions or inactions, which may result in any loss or liability to drynutties.com or any third party including but not limited to breach of any warranties, representations or undertakings or in relation to the non-fulfillment of any of your obligations under this User Agreement or arising out of the your violation of any applicable laws, regulations including but not limited to Intellectual Property Rights, payment of statutory dues and taxes, claim of libel, defamation, violation of rights of privacy or publicity, loss of service by other subscribers and infringement of intellectual property or other rights. This clause shall survive the expiry or termination of this User Agreement.";
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_con);
        tv=(TextView)findViewById(R.id.term);
        tv.setText(Html.fromHtml(str));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setIcon(R.drawable.welcome);
        sp=getSharedPreferences("detail",MODE_PRIVATE);

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
        getMenuInflater().inflate(R.menu.menu_term_con, menu);
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
