package com.example.rishan.rishan.Models;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.example.rishan.rishan.Fragments.CartFragment;
import com.example.rishan.rishan.Fragments.HomeFragment;
import com.example.rishan.rishan.Fragments.ManageAccountFragment;
import com.example.rishan.rishan.Fragments.PurchaseHistoryFragment;
import com.example.rishan.rishan.R;

/**
 * Created by Rishan on 6/2/2018.
 */

public class tabPager extends FragmentStatePagerAdapter {
        Context context;

    public tabPager(FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
    }



    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                 HomeFragment homeFragment=new HomeFragment();
                FragmentTransaction ft=  ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, homeFragment);
                ft.commit();
                return homeFragment;

            case 1:
                CartFragment cartFragmentfragment=new CartFragment();
                 ft=  ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, cartFragmentfragment);
                ft.commit();
                return cartFragmentfragment;

            case 2:
                ManageAccountFragment manageAccountFragment=new ManageAccountFragment();
                ft=  ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, manageAccountFragment);
                ft.commit();
                return manageAccountFragment;

            default:
                return fragment;
        }
    }

    @Override
    public int getCount() {
        return 3;

    }
}
