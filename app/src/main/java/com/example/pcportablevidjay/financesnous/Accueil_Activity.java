package com.example.pcportablevidjay.financesnous;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import classes.Utils;

import App_Fragment.*;

public class Accueil_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String fragmentCourant = "";

    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Accueil_Activity.this.receivedBroadcast(intent);
        }
    };

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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Intent depenseActivity = new Intent(getBaseContext(), Depense_Activity.class);
                        startActivity(depenseActivity);
                    }
                }).start();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //onNavigationItemSelected(navigationView.getMenu().getItem(0));

        Fragment_Accueil fragment = new Fragment_Accueil();
        changerFragment(fragment);
        navigationView.getMenu().getItem(0).setChecked(true);


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
        t.setText(StorageHelper.getUtilisateur(this.getBaseContext()).getMail());

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void afficherAjoutDepense(boolean statut){
        if(statut) {
            findViewById(R.id.fab).setVisibility(View.VISIBLE);
        }
        else{
            findViewById(R.id.fab).setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int item_id = item.getItemId();

        if (item_id == R.id.nav_accueil) {
            afficherAjoutDepense(true);
            Fragment_Accueil fragmentAccueil = new Fragment_Accueil();
            fragmentCourant = "accueil";
            changerFragment(fragmentAccueil);
        } else if (item_id == R.id.nav_depense) {
            afficherAjoutDepense(true);
            Fragment_Recherche_Depense fragmentRechercheDepense = new Fragment_Recherche_Depense();
            fragmentCourant = "rechercheDepense";
            changerFragment(fragmentRechercheDepense);
        } else if (item_id == R.id.nav_stats) {
            afficherAjoutDepense(false);
            Fragment_Statistique fragmentStatistique = new Fragment_Statistique();
            fragmentCourant = "statistique";
            changerFragment(fragmentStatistique);
        } else if (item_id == R.id.nav_garantie) {
            Toast.makeText(getApplicationContext(), "Disponible dans la prochaine mise à jour.", Toast.LENGTH_SHORT).show();
        } else if (item_id == R.id.nav_noteDeFrais) {
            Toast.makeText(getApplicationContext(), "Disponible dans la prochaine mise à jour.", Toast.LENGTH_SHORT).show();
        } else if (item_id == R.id.nav_about) {
            afficherAjoutDepense(false);
            Intent about = new Intent(getBaseContext(), About_Activity.class);
            startActivity(about);
        } else if (item_id == R.id.nav_share) {
            afficherAjoutDepense(false);
            Intent about = new Intent(getBaseContext(), Social_Activity.class);
            startActivity(about);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changerFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();

        IntentFilter iff = new IntentFilter();
        iff.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.registerReceiver(this.mBroadcastReceiver, iff);

        switch (fragmentCourant) {
               case "accueil":
               case "rechercheDepense":
                   afficherAjoutDepense(true);
                   break;
               case "statistique":
                   afficherAjoutDepense(false);
                   break;
               default:
                   afficherAjoutDepense(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        this.unregisterReceiver(this.mBroadcastReceiver);
    }

    private void receivedBroadcast(Intent i) {
        if (!Utils.getConnectivityStatus(getApplicationContext())) {
            Intent login = new Intent(getBaseContext(), Login_Activity.class);
            startActivity(login);
            Toast.makeText(getApplicationContext(), "Connexion perdu", Toast.LENGTH_SHORT).show();
            Accueil_Activity.this.finish();
        }
    }
}
