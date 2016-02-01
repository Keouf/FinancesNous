package com.example.pcportablevidjay.financesnous;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import java.util.ArrayList;
import classes.AndroidConnectivity;
import classes.Depense;
import classes.DepenseAdapter;
import classes.Global;
import classes.JsonConverter;
import classes.MyDBHelper;
import layout.Fragment_Recherche_Depense;

public class Accueil_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static Fragment currentFragment = null;
    public static MyDBHelper myDBHelper = new MyDBHelper();
    public AndroidConnectivity androidConnectivity = new AndroidConnectivity(this);
    Global global = (Global)this.getApplication();
    public static JsonConverter jsonConverter = new JsonConverter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_accueil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent depense = new Intent(getBaseContext(), Depense_Activity.class);
                startActivity(depense);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.accueil, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_depense) {
            Toast.makeText(getApplicationContext(), "Dépenses", Toast.LENGTH_SHORT).show();
            Fragment_Recherche_Depense fragment = new Fragment_Recherche_Depense();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();;
            fragmentTransaction.replace(R.id.frame,fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_stats) {
            Toast.makeText(getApplicationContext(), "Statistiques", Toast.LENGTH_SHORT).show();
            Fragment_Recherche_Depense fragment = new Fragment_Recherche_Depense();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();;
            fragmentTransaction.replace(R.id.frame,fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_garantie) {

        } else if (id == R.id.nav_noteDeFrais) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changerFragment(Fragment fragment){
        if(currentFragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(currentFragment);
            fragmentTransaction.add(R.id.fragment_container, fragment);
            currentFragment = fragment;
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else{
            currentFragment = fragment;
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, currentFragment);
            fragmentTransaction.commit();
        }
    }
}
