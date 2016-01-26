package com.example.pcportablevidjay.financesnous;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Depense_Activity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_depense);

        final Button button = (Button) findViewById(R.id.buttonAnnuler);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }


    public boolean tryToSendDepense()
    {
        boolean send = true;

        //interactions
        EditText domaineEdit = (EditText)findViewById(R.id.editTextDomaine);
        EditText enseigneEdit = (EditText)findViewById(R.id.editTextEnseigne);
        EditText adresse1Edit = (EditText)findViewById(R.id.editText_rue);
        EditText adresse2Edit = (EditText)findViewById(R.id.editText_ville);
        EditText codePostalEdit = (EditText)findViewById(R.id.editText_codePostal);
        EditText montantEdit = (EditText)findViewById(R.id.editTextMontant);



        return  send;
    }


}

