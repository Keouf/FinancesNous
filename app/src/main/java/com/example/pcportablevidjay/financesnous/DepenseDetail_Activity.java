package com.example.pcportablevidjay.financesnous;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import classes.Depense;

public class DepenseDetail_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_depense_detail);


        Intent i = getIntent();
        Depense depense = (Depense)i.getSerializableExtra("Depense");
        Toast.makeText(getApplicationContext(), depense.getDomaine(), Toast.LENGTH_LONG).show();
    }
}
