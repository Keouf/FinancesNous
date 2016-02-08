package com.example.pcportablevidjay.financesnous;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import classes.Depense;

public class DepenseDetail_Activity extends AppCompatActivity {


    Depense depense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_depense_detail);

        Intent i = getIntent();
        depense = (Depense) i.getSerializableExtra("Depense");
        populateLayout();


    }

    private void populateLayout() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

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
