package com.example.pcportablevidjay.financesnous;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import classes.AndroidConnectivity;
import classes.Depense;
import classes.Global;
import classes.MyDBHelper;

public class Depense_Activity extends FragmentActivity {

    Global global = (Global)this.getApplication();
    MyDBHelper myDBHelper = new MyDBHelper();
    Date date = new Date();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_depense);

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


    public void tryToSendDepense(View v)
    {
        EditText montantEdit = (EditText)findViewById(R.id.editTextMontant);
        Spinner domaineSpinner = (Spinner)findViewById(R.id.spinner_domaine);
        Spinner magasinSpinner = (Spinner)findViewById(R.id.spinner_enseigne);

        boolean remplit = true;
        // check if they are empty
        if (TextUtils.isEmpty(montantEdit.getText())) {
            montantEdit.setError("veuiller saisir un montant svp");
            montantEdit.setFocusable(true);
            remplit = false;
        }

        if (remplit) {
            AndroidConnectivity androidConnectivity = new AndroidConnectivity(this);
            if (androidConnectivity.getConnectivityStatus())
            {
                global = (Global)this.getApplication();
                Depense maDepense = new Depense(myDBHelper.getLastDepenseID(), date, Double.parseDouble(montantEdit.getText().toString()), global.getMainUtilisateur(), domaineSpinner.getItemAtPosition(domaineSpinner.getSelectedItemPosition()).toString(), myDBHelper.getMagasinWithId(1), "");
                global.getMainUtilisateur().addDepense(maDepense);
                myDBHelper.insertDepense(maDepense);
                Toast.makeText(this, "Dépense Crée!", Toast.LENGTH_LONG).show();
                this.finish();
            }
            else
                Toast.makeText(this, "Pas de connection internet, veiller esssaier plustard", Toast.LENGTH_LONG).show();
        }


    }

    public void annuler (View v)
    {
        this.finish();
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

