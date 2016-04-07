package com.example.pcportablevidjay.financesnous;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import classes.MyDBHelper;
import classes.Utils;

public class Domaine_Activity extends AppCompatActivity {

    private final MyDBHelper myDBHelper = new MyDBHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_domaine);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void ajoutDomaine(View v) {

        EditText nomDomaineEdit = (EditText) findViewById(R.id.editTextNomDomaine);

        boolean remplit = true;

        if (TextUtils.isEmpty(nomDomaineEdit.getText())) {
            nomDomaineEdit.setError("Veuillez entrer un nom de domaine");
            nomDomaineEdit.setFocusable(true);
            remplit = false;
        }
        if (remplit) {
            if (Utils.getConnectivityStatus(getApplicationContext())) {
                myDBHelper.ajoutDomaine(nomDomaineEdit.getText().toString());
                Toast.makeText(this, "Le domaine a bien été créé.", Toast.LENGTH_LONG).show();
                this.finish();
            } else {
                Toast.makeText(this, "Pas de connexion internet, veuillez réessayer plus tard.", Toast.LENGTH_LONG).show();
                this.finish();
            }
        }

    }
}
