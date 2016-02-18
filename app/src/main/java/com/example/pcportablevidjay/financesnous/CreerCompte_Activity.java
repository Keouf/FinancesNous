package com.example.pcportablevidjay.financesnous;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.Domaine;
import classes.MyDBHelper;
import classes.Utilisateur;
import classes.Utils;

public class CreerCompte_Activity extends AppCompatActivity {

    MyDBHelper myDBHelper = new MyDBHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_creer_compte);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void tryToCreerCompte(View v){

        EditText emailEdit = (EditText) findViewById(R.id.editTextCreerEmail);
        EditText mdpEdit = (EditText) findViewById(R.id.editTextCreerMdp);

        final String loginTxt = emailEdit.getText().toString();

        boolean remplit = true;

        // pattern pour vérifier adresse mail
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        // matcher qui compare
        Matcher m = p.matcher(loginTxt);

        // Vérifier champ mail rempli
        if (TextUtils.isEmpty(emailEdit.getText())) {
            emailEdit.setError("Veuillez entrer une adresse email");
            emailEdit.setFocusable(true);
            remplit = false;
        }
        // Vérifier que l'adresse mail est correct
        if (!m.matches()) {
            emailEdit.setError("L'adresse mail que vous avez entrer n'est pas valide");
            emailEdit.setFocusable(true);
            remplit = false;
        }
        // Vérifier champ mot de passe rempli
        if (TextUtils.isEmpty(mdpEdit.getText())) {
            mdpEdit.setError("Veuillez entrer un mot de passe");
            mdpEdit.setFocusable(true);
            remplit = false;
        }

        if (remplit) {
            if (Utils.getConnectivityStatus(getApplicationContext()))
            {
                Utilisateur newUser = new Utilisateur(0,emailEdit.getText().toString(),mdpEdit.getText().toString());
                myDBHelper.creerCompte(newUser);
                Toast.makeText(this, "L'utilisateur a bien été créé.", Toast.LENGTH_LONG).show();
                this.finish();
            }
            else {
                Toast.makeText(this, "Pas de connexion internet, veuillez réessayer plus tard.", Toast.LENGTH_LONG).show();
                this.finish();
            }
        }
    }
}
