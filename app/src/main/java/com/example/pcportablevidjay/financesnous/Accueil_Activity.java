package com.example.pcportablevidjay.financesnous;

import android.app.Fragment;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import classes.StorageHelper;
import classes.Utilisateur;
import layout.Fragment_Recherche_Depense;

public class Accueil_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    StorageHelper storageHelper;
    Utilisateur mainUtilisateur;


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

        //onNavigationItemSelected(navigationView.getMenu().getItem(0));

        Fragment_Accueil fragment = new Fragment_Accueil();
        changerFragment(fragment);
        navigationView.getMenu().getItem(0).setChecked(true);

        storageHelper = new StorageHelper(this);
        mainUtilisateur = storageHelper.getUtilisateur();


        //global.getMainUtilisateur().setMesDepenses(myDBHelper.getMesDepenses(global));
        //Log.e("json", "arraylist of all depenses = " + mainUtilisateur.getMesDepenses().toString());
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

        TextView t = (TextView) findViewById(R.id.TV_IDUser);
        t.setText(mainUtilisateur.getMail());

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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_accueil) {
            Fragment_Accueil fragment = new Fragment_Accueil();
            changerFragment(fragment);
        } else if (id == R.id.nav_depense) {
            Fragment_Recherche_Depense fragment = new Fragment_Recherche_Depense();
            changerFragment(fragment);
        } else if (id == R.id.nav_stats) {
            Fragment_Statistique fragment = new Fragment_Statistique();
            changerFragment(fragment);
        } else if (id == R.id.nav_garantie) {

        } else if (id == R.id.nav_noteDeFrais) {

        } else if (id == R.id.nav_about) {
            Intent about = new Intent(getBaseContext(), About_Activity.class);
            startActivity(about);
        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changerFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}
