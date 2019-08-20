package com.example.rishan.rishan;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
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

import android.widget.TextView;

import com.example.rishan.rishan.Fragments.CartFragment;
import com.example.rishan.rishan.Fragments.HomeFragment;
import com.example.rishan.rishan.Fragments.ManageAccountFragment;
import com.example.rishan.rishan.Fragments.PurchaseHistoryFragment;

public class TabActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.containerv);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.purchaseHistory) {
            PurchaseHistoryFragment purchaseHistoryFragment=new PurchaseHistoryFragment();
            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, purchaseHistoryFragment);
            ft.commit();
            return  true;
        }
        if (id == R.id.ContactUs) {
            PurchaseHistoryFragment purchaseHistoryFragment=new PurchaseHistoryFragment();
            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, purchaseHistoryFragment);
            ft.commit();
            return  true;
        }


        return super.onOptionsItemSelected(item);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        String[] titles=new String[]{"HOME" ,"BROWSE", "PROFILE"};
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment=null;
            switch (position){
                case 0:
                    HomeFragment homeFragment=new HomeFragment();
//                FragmentTransaction ft=  ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.fragment_container, fragment);
//                ft.commit();
                    return homeFragment;

                case 1:
                    CartFragment cartFragmentfragment=new CartFragment();
//                 ft=  ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.fragment_container, fragment);
//                ft.commit();
                    return cartFragmentfragment;

                case 2:
                    ManageAccountFragment manageAccountFragment=new ManageAccountFragment();
//                ft=  ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.fragment_container, fragment);
//                ft.commit();
                    return manageAccountFragment;

                default:
                    return fragment;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
