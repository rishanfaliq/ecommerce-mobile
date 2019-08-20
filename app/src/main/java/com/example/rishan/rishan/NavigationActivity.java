package com.example.rishan.rishan;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import com.example.rishan.rishan.Fragments.CancelledOrders;
import com.example.rishan.rishan.Fragments.ContactUsFragment;
import com.example.rishan.rishan.Fragments.FavouritesFragment;
import com.example.rishan.rishan.Fragments.TabMain;
import com.example.rishan.rishan.Models.User;
import com.github.clans.fab.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rishan.rishan.Fragments.CartFragment;
import com.example.rishan.rishan.Fragments.HomeFragment;
import com.example.rishan.rishan.Fragments.ManageAccountFragment;
import com.example.rishan.rishan.Fragments.PurchaseHistoryFragment;
import com.example.rishan.rishan.Fragments.ViewCart;
import com.example.rishan.rishan.Models.tabPager;
import com.github.clans.fab.FloatingActionMenu;
import com.orm.SugarRecord;
import com.squareup.picasso.Picasso;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String uid;
    Bundle bundle;
    FloatingActionMenu fam;
    FloatingActionButton fabFavs, fabCart, fabOrders, fabProfile, fabShipping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("uid", "");
        final User userData = SugarRecord.findById(User.class, Long.parseLong(id));

        //cast the serializable object to a User type object after getting it throug  the intent
        fam = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        fabCart = (FloatingActionButton) findViewById(R.id.fabCart);
        fabOrders = (FloatingActionButton) findViewById(R.id.fabOrders);
        fabProfile =(FloatingActionButton) findViewById(R.id.fabProfile);
        fabFavs =  (FloatingActionButton)findViewById(R.id.fabHome);
        fabShipping =  (FloatingActionButton)findViewById(R.id.fabShipping);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displaySettingsScreen(R.id.nav_home);
        setTitle("Home");



        fam.setClosedOnTouchOutside(true);

        // fab.setOnTouchListener(NavigationActivity.this);


        fabCart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new TabMain();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();
                fam.close(true);
                    }
        });


        fabShipping.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new PurchaseHistoryFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();
                fam.close(true);
            }
        });


        fabFavs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new FavouritesFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();
                fam.close(true);
            }
        });

        fabProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new ManageAccountFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();
                fam.close(true);
            }
        });
        fabOrders.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new ViewCart();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();
                fam.close(true);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
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


     /*FragmentTransaction tx = fragmentManager.beginTransation();
tx.replace( R.id.fragment, new MyFragment() ).addToBackStack( "tag" ).commit();
     final Myfragment fragment = new MyFragment();
     final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.fragment, fragment, TAG_FRAGMENT);
    transaction.addToBackStack(null);
    transaction.commit();
*/

    private void displaySettingsScreen(int id) {
        Fragment fragment = null;

        switch (id) {
// 1
            case R.id.nav_home:
                fragment = new TabMain();
                break;
            case R.id.nav_cancelled:
                fragment = new CancelledOrders();
                break;


            case R.id.nav_contactUs:
                fragment = new ContactUsFragment();
                break;

            case R.id.nav_logout:
                SharedPreferences sharedPreferences=getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();
                Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                startActivity(intent);
                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.addToBackStack(null);
            ft.commit();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

      /*  if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
*/
        displaySettingsScreen(id);


        return true;
    }


}
