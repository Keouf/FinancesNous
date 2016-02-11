package com.example.pcportablevidjay.financesnous;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import classes.Depense;
import classes.Global;
import classes.MyDBHelper;
import classes.Utils;

public class Depense_Activity extends AppCompatActivity {

    Global global = (Global)this.getApplication();
    MyDBHelper myDBHelper = new MyDBHelper();
    Date date = new Date();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_depense);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final AppCompatImageButton btnAjouterDomaine = (AppCompatImageButton) findViewById(R.id.btnAjouterDomaine);
        btnAjouterDomaine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent creerDomaine = new Intent(getBaseContext(), Domaine_Activity.class);
                startActivity(creerDomaine);
            }
        });

        final AppCompatImageButton btnAjouterEnseigne = (AppCompatImageButton) findViewById(R.id.btnAjouterEnseigne);
        btnAjouterEnseigne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent creerEnseigne = new Intent(getBaseContext(), Magasin_Activity.class);
                startActivity(creerEnseigne);
            }
        });

        final CheckBox checkGarantie = (CheckBox) findViewById(R.id.CBGarantie);
        checkGarantie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (checkGarantie.isChecked())
                    affichagetest(true,findViewById(R.id.form_garantie));
                else
                    affichagetest(false,findViewById(R.id.form_garantie));
            }
        });

        final CheckBox checkNoteDeFrais = (CheckBox) findViewById(R.id.CBNoteDeFrais);
        checkNoteDeFrais.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (checkNoteDeFrais.isChecked())
                    affichagetest(true,findViewById(R.id.form_noteDeFrais));
                else
                    affichagetest(false,findViewById(R.id.form_noteDeFrais));
            }
        });

        // populate date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        EditText dateEdit = (EditText)findViewById(R.id.editText_date);
        dateEdit.setText(dateFormat.format(date));


        // populate domaine spinner
        Spinner spinnerDomaine = (Spinner) findViewById(R.id.spinner_domaine);
        ArrayAdapter<String> adapterDomaine = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myDBHelper.getAllDomaines());
        adapterDomaine.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDomaine.setAdapter(adapterDomaine);

        // populate magasin spinner
        Spinner spinnerMagasin = (Spinner) findViewById(R.id.spinner_enseigne);
        ArrayAdapter<String> adapterMagasin = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myDBHelper.getAllMagasins());
        adapterMagasin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMagasin.setAdapter(adapterMagasin);

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

    public void affichagetest(final boolean show,final View mForm){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_mediumAnimTime);

            mForm.setVisibility(show ? View.VISIBLE : View.GONE);
            mForm.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mForm.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mForm.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    public void tryToSendDepense(View v)
    {
        EditText montantEdit = (EditText)findViewById(R.id.editTextMontant);
        Spinner domaineSpinner = (Spinner)findViewById(R.id.spinner_domaine);
        Spinner magasinSpinner = (Spinner)findViewById(R.id.spinner_enseigne);

        boolean remplit = true;
        // check if they are empty
        if (TextUtils.isEmpty(montantEdit.getText())) {
            montantEdit.setError("Veuillez saisir un montant.");
            montantEdit.setFocusable(true);
            remplit = false;
        }

        if (remplit) {
            if (Utils.getConnectivityStatus(getApplicationContext()))
            {
                global = (Global)this.getApplication();
                Depense maDepense = new Depense(myDBHelper.getLastDepenseID(), date, Double.parseDouble(montantEdit.getText().toString()), global.getMainUtilisateur(), domaineSpinner.getItemAtPosition(domaineSpinner.getSelectedItemPosition()).toString(), myDBHelper.getMagasinWithId(1), "");
                global.getMainUtilisateur().addDepense(maDepense);
                myDBHelper.insertDepense(maDepense);
                Toast.makeText(this, "Dépense Créée !", Toast.LENGTH_LONG).show();
                this.finish();
            }
            else
                Toast.makeText(this, "Erreur ! Verifiez votre connection internet.", Toast.LENGTH_LONG).show();
        }


    }

    //----------date picker-----------------
    public void selectDate(View view) {
        DialogFragment newFragment = new SelectDateFragment();
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }
    public void populateSetDate(int year, int month, int day) {
        EditText dateEdit = (EditText)findViewById(R.id.editText_date);
        dateEdit.setText(year+"-"+month+"-"+day);
        date.setYear(year);
        date.setMonth(month);
        date.setDate(day);
    }

    @SuppressLint("ValidFragment")
    public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }
    }
    //-------------end datepicker------------

}

