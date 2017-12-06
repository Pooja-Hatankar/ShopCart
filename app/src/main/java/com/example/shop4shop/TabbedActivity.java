package com.example.shop4shop;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class TabbedActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    Session session;
    MenuItem selectedMenuItem;
    TextView countTvMenuItem;
    ImageView countImageViewMenuItem;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        session=new Session(this);
        if(!session.loggedin()){
            logout();
        }
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed, menu);
        selectedMenuItem = menu.findItem(R.id.action_cart);
        View actionView=selectedMenuItem.getActionView();

        if(actionView!= null){
            countTvMenuItem = actionView.findViewById(R.id.count_tv);
            countImageViewMenuItem = actionView.findViewById(R.id.cart_ic_image);
        }
        countImageViewMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TabbedActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });
        return super.onCreateOptionsMenu(menu);
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
        else if(id==R.id.action_logout1){
            if(session.loggedin()){
                logout();
            }
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void logout() {
        session.setLoggedin(false);
        finish();
        Intent intent=new Intent(TabbedActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    /**
     * A placeholder fragment containing a simple view is deleted bcoz we created 3 different tab class.
     */


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    Tab1Electronics tab1=new Tab1Electronics();
                    return tab1;
                case 1:
                    Tab2Grossary tab2=new Tab2Grossary();
                    return tab2;
                case 2:
                    Tab3Sports tab3=new Tab3Sports();
                    return tab3;
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        public CharSequence getPageTitle(int position){
            switch(position){
                case 0:
                    return "ELECTRONICS";
                case 1:
                    return "GROSSARY";
                case 2:
                    return "SPORTS";
            }
            return null;

        }
    }
}
