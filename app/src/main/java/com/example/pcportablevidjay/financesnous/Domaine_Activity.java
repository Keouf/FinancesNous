package com.example.pcportablevidjay.financesnous;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import classes.AndroidConnectivity;
import classes.Global;
import classes.MyDBHelper;

public class Domaine_Activity extends AppCompatActivity {

    Global global = (Global) this.getApplication();
    MyDBHelper myDBHelper = new MyDBHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_domaine);
    }

    public void tryToSendDomaine(View v) {

        EditText nomDomaineEdit = (EditText) findViewById(R.id.editTextNomDom);

        boolean remplit = true;

        // Vérifier si les champs ont bien été rempli
        if (TextUtils.isEmpty(nomDomaineEdit.getText())) {
            nomDomaineEdit.setError("Veuillez entrer le nom du domaine svp");
            nomDomaineEdit.setFocusable(true);
            remplit = false;
        }
        if (remplit) {
            AndroidConnectivity androidConnectivity = new AndroidConnectivity(this);
            if (androidConnectivity.getConnectivityStatus())
            {
                // à compléter
            }
            else
                Toast.makeText(this, "Pas de connection internet, veuillez réessayer plus tard", Toast.LENGTH_LONG).show();
        }

    }

    public void annulerDomaine (View v) {
        this.finish();
    }

}
