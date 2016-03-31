package com.example.pcportablevidjay.financesnous;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import classes.Depense;
import classes.MyDBHelper;
import classes.StorageHelper;
import classes.Utilisateur;

public class DepenseDetail_Activity extends AppCompatActivity {

    private final MyDBHelper myDBHelper = new MyDBHelper();
    private Depense depense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_depense_details);

        Intent i = getIntent();
        depense = (Depense) i.getSerializableExtra("Depense");
        populateLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.depense_detail_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.depense_detail_bt_supprimer) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Suppression?");
            adb.setMessage("Etes vous sur de vouloir supprimer cette dépense ?");
            adb.setNegativeButton("Annuler", null);
            adb.setPositiveButton("Oui, supprime cette dépense", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // if accepted

                    myDBHelper.supprimerDepense(depense);
                    Utilisateur user = StorageHelper.getUtilisateur(getBaseContext());
                    user.removeDepense(depense);
                    StorageHelper.storeObject(getBaseContext(), user);

                    finish();
                }
            });
            adb.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateLayout() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd", java.util.Locale.getDefault());

        EditText ed_enseigne = (EditText) findViewById(R.id.depense_detail_et_enseigne);
        EditText ed_date = (EditText) findViewById(R.id.depense_detail_et_date);
        EditText ed_domaine = (EditText) findViewById(R.id.depense_detail_et_domaine);
        EditText ed_adresse = (EditText) findViewById(R.id.depense_detail_et_adresse);
        EditText ed_adresse2 = (EditText) findViewById(R.id.depense_detail_et_adresse2);
        EditText ed_telephone = (EditText) findViewById(R.id.depense_detail_et_telephone);
        EditText ed_montant = (EditText) findViewById(R.id.depense_detail_et_montant);
        EditText ed_site = (EditText) findViewById(R.id.depense_detail_et_site);

        ed_date.setText(dateFormat.format(depense.getDateDepense()));
        ed_adresse.setText(depense.getMagasin().getAdresse1());
        ed_adresse2.setText(depense.getMagasin().getAdresse2());
        ed_domaine.setText(depense.getDomaine());
        ed_enseigne.setText(depense.getMagasin().getNom_managasin());
        ed_montant.setText(Double.toString(depense.getMontant()) + " €");
        ed_site.setText(depense.getMagasin().getSiteWeb());
        ed_telephone.setText(depense.getMagasin().getTelephone());
    }
}
