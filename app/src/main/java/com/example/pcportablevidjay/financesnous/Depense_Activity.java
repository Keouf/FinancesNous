package com.example.pcportablevidjay.financesnous;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.DatePicker;
import android.support.v4.app.FragmentActivity;
import android.app.Dialog;
import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import classes.Depense;
import classes.Domaine;
import classes.Global;
import classes.Magasin;
import classes.MyDBHelper;

public class Depense_Activity extends FragmentActivity {

    Global global = (Global)this.getApplication();
    MyDBHelper myDBHelper = new MyDBHelper();
    Date date = new Date();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_depense);


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        EditText dateEdit = (EditText)findViewById(R.id.editText_date);
        dateEdit.setText(dateFormat.format(date));

    }


    public void tryToSendDepense(View v)
    {
        //interactions
        EditText domaineEdit = (EditText)findViewById(R.id.editTextDomaine);
        EditText enseigneEdit = (EditText)findViewById(R.id.editTextEnseigne);
        EditText montantEdit = (EditText)findViewById(R.id.editTextMontant);


        boolean remplit = true;
        // check if they are empty
        if (TextUtils.isEmpty(domaineEdit.getText())) {
            domaineEdit.setError("choisis une domaine");
            remplit = false;
        }
        if (TextUtils.isEmpty(enseigneEdit.getText())) {
            enseigneEdit.setError("choisis un magasin");
            remplit = false;
        }
        if (TextUtils.isEmpty(montantEdit.getText())) {
            montantEdit.setError("veuiller saisir un montant svp");
            remplit = false;
        }

        if (remplit) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
            Depense maDepense = new Depense(0, date, Double.parseDouble(montantEdit.getText().toString()), global.getMainUtilisateur(), myDBHelper.getDomaineWithId(1), myDBHelper.getMagasinWithId(1), "");
            global.getMainUtilisateur().addDepense(maDepense);
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

