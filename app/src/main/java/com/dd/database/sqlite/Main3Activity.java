package com.dd.database.sqlite;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dd.database.sqlite.ertegiler.FragmentMainErtegiler;
import com.dd.database.sqlite.makalMatel.FragmentMainMakal;
import com.dd.database.sqlite.zhaniltpashtar.FragmentMainZhaniltpashtar;

public class Main3Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentMainMakal.onSomeEventListener {


    //Фрагменты
    FragmentMainMakal frag1;
    FragmentTransaction fTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //Фрагменты
        frag1 = new FragmentMainMakal();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.fragmentContent, frag1);
        fTrans.commit();



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
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
        getMenuInflater().inflate(R.menu.main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "settings pressed", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
                Toast.makeText(getApplicationContext(),"Back button clicked", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        //Фрагменты
        frag1 = new FragmentMainMakal();
        FragmentMainErtegiler fragmentMainErtegiler = new FragmentMainErtegiler();
        FragmentMainZhaniltpashtar fragmentMainZhaniltpashtar = new FragmentMainZhaniltpashtar();
        fTrans = getSupportFragmentManager().beginTransaction();

        switch (item.getItemId()) {
            case R.id.nav_item0:
                //open makal matel
                fTrans.replace(R.id.fragmentContent, frag1);
                fTrans.addToBackStack(null);
                fTrans.commit();
                break;
            case R.id.nav_item1:
                //open ertegiler
                fTrans.replace(R.id.fragmentContent, fragmentMainErtegiler);
                fTrans.addToBackStack(null);
                fTrans.commit();
                break;

            case R.id.nav_item2:
                fTrans.replace(R.id.fragmentContent, fragmentMainZhaniltpashtar);
                fTrans.addToBackStack(null);
                fTrans.commit();
                break;
            case R.id.nav_item3:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;

            default:
                ;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }



    @Override
    public void someEvent(String position) {

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



//        ActivityMakalList frag1 = new ActivityMakalList();
//        Bundle bundle = new Bundle();
//        bundle.putInt("position", Integer.parseInt(position));
//        frag1.setArguments(bundle);
//        fTrans = getSupportFragmentManager().beginTransaction();
//        fTrans.replace(R.id.frgmCont, frag1);
//        fTrans.addToBackStack(null);


//        getSupportFragmentManager().addOnBackStackChangedListener(
//                new FragmentManager.OnBackStackChangedListener() {
//                    public void onBackStackChanged() {
//                        // Update your UI here.
////                        ActionBar ab = getActionBar().ac;
////                        ab.NavigationMode(ActionBar.DISPLAY_HOME_AS_UP);
////                        ab.setDisplayHomeAsUpEnabled(true);
////                        ab.setDisplayShowHomeEnabled(true);
////                        boolean canback = getSupportFragmentManager().getBackStackEntryCount() > 0;
////                        getSupportActionBar().setDisplayHomeAsUpEnabled(canback);
//
////                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
////                        toggle.syncState();
//
//                    }
//                });
//
//        fTrans.commit();


    }


}

