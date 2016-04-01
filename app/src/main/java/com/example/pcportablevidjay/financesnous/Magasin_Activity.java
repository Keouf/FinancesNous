package com.example.pcportablevidjay.financesnous;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import classes.Magasin;
import classes.MyDBHelper;
import classes.Utils;

public class Magasin_Activity extends AppCompatActivity {

    private final MyDBHelper myDBHelper = new MyDBHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_magasin);

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

    public void tryToSendMagasin(View v) {

        EditText nomMagasinEdit = (EditText) findViewById(R.id.editTextNomMag);
        EditText adresseMagasinEdit = (EditText) findViewById(R.id.editTextAdrMag);
        EditText codePostalMagasinEdit = (EditText) findViewById(R.id.editTextCPMag);
        EditText villeMagasinEdit = (EditText) findViewById(R.id.editTextVilleMag);
        EditText siteMagasinEdit = (EditText) findViewById(R.id.editTextSiteMag);
        EditText telMagasinEdit = (EditText) findViewById(R.id.editTextTelMag);

        boolean remplit = true;

        // Vérifier si les champs ont bien été rempli
        if (TextUtils.isEmpty(nomMagasinEdit.getText())) {
            nomMagasinEdit.setError("Veuillez entrer le nom du magasin");
            nomMagasinEdit.setFocusable(true);
            remplit = false;
        }
        if (TextUtils.isEmpty(villeMagasinEdit.getText())) {
            villeMagasinEdit.setError("Veuillez entrer la ville du magasin");
            villeMagasinEdit.setFocusable(true);
            remplit = false;
        }
        if(telMagasinEdit.getText().length() < 10){
            telMagasinEdit.setError("Le numéro de téléphone doit obligatoirement contenir 10 chiffres");
            telMagasinEdit.setFocusable(true);
            remplit = false;
        }
        if(codePostalMagasinEdit.getText().length() < 5){
            codePostalMagasinEdit.setError("Le code postal doit obligatoirement contenir 5 chiffres");
            codePostalMagasinEdit.setFocusable(true);
            remplit = false;
        }

        if (remplit) {
            if (Utils.getConnectivityStatus(getApplicationContext())) {
                Magasin monMagasin = new Magasin(2, nomMagasinEdit.getText().toString(), adresseMagasinEdit.getText().toString(), villeMagasinEdit.getText().toString(), codePostalMagasinEdit.getText().toString(), siteMagasinEdit.getText().toString(), telMagasinEdit.getText().toString());
                myDBHelper.ajoutMagasin(monMagasin, this);
                Toast.makeText(this, "Le magasin a bien été crée", Toast.LENGTH_LONG).show();
                this.finish();

            } else
                Toast.makeText(this, "Pas de connection internet, veuillez réessayer plus tard", Toast.LENGTH_LONG).show();
        }

    }

}
