package com.example.pcportablevidjay.financesnous;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import classes.Depense;
import classes.MyDBHelper;
import classes.StorageHelper;
import classes.Utilisateur;
import classes.Utils;

public class Depense_Activity extends AppCompatActivity {

    StorageHelper storageHelper;
    final private MyDBHelper myDBHelper = new MyDBHelper();
    private Date date = new Date();
    private File filePathPhoto;
    private Bitmap ImageBmp;
    private String lienEnvoie;


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

        final Button btnPrendrePhoto = (Button) findViewById(R.id.buttonPrendrePhoto);
        btnPrendrePhoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent prendrePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                prendrePhoto.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(returnFile()));
                startActivityForResult(prendrePhoto, 2);
            }
        });

        final Button btnParcourir = (Button) findViewById(R.id.buttonParcourir);
        btnParcourir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent parcourirPhoto = new Intent(Intent.ACTION_GET_CONTENT);
                parcourirPhoto.setType("image/*");
                startActivityForResult(Intent.createChooser(parcourirPhoto, "Choisir dossier"), 1);
            }
        });

        final CheckBox checkGarantie = (CheckBox) findViewById(R.id.CBGarantie);
        checkGarantie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (checkGarantie.isChecked())
                    affichageCheckBox(true, findViewById(R.id.form_garantie));
                else
                    affichageCheckBox(false, findViewById(R.id.form_garantie));
            }
        });

        final CheckBox checkNoteDeFrais = (CheckBox) findViewById(R.id.CBNoteDeFrais);
        checkNoteDeFrais.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (checkNoteDeFrais.isChecked())
                    affichageCheckBox(true, findViewById(R.id.form_noteDeFrais));
                else
                    affichageCheckBox(false, findViewById(R.id.form_noteDeFrais));
            }
        });

        // populate date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
        EditText dateEdit = (EditText) findViewById(R.id.editText_date);
        dateEdit.setText(dateFormat.format(date));
        EditText garantieDebutEditText = (EditText) findViewById(R.id.editText_DebutGarantie);
        garantieDebutEditText.setText(dateFormat.format(date));

        // populate domaine spinner
        Spinner spinnerDomaine = (Spinner) findViewById(R.id.spinner_domaine);
        ArrayAdapter<String> adapterDomaine = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, myDBHelper.getAllDomaines());
        adapterDomaine.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDomaine.setAdapter(adapterDomaine);

        // populate magasin spinner
        Spinner spinnerMagasin = (Spinner) findViewById(R.id.spinner_enseigne);
        ArrayAdapter<String> adapterMagasin = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, myDBHelper.getAllMagasins());
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1: {
                if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                    try {
                        Uri file = data.getData();
                        lienEnvoie = file.toString();
                        ImageBmp = MediaStore.Images.Media.getBitmap(getContentResolver(), file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            case 2: {
                if (resultCode == RESULT_OK) {
                    try {
                        Uri file = Uri.fromFile(filePathPhoto);
                        lienEnvoie = file.toString();
                        ImageBmp = MediaStore.Images.Media.getBitmap(getContentResolver(), file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
    }

    private void affichageCheckBox(final boolean show, final View mForm) {

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

    public void tryToSendDepense(View v) {
        EditText montantEdit = (EditText) findViewById(R.id.editTextMontant);
        Spinner domaineSpinner = (Spinner) findViewById(R.id.spinner_domaine);
        Spinner magasinSpinner = (Spinner) findViewById(R.id.spinner_enseigne);
        CheckBox garantieCheckBox = (CheckBox) findViewById(R.id.CBGarantie);
        EditText garantieDebutEditText = (EditText) findViewById(R.id.editText_DebutGarantie);
        EditText garantieFinEditText = (EditText) findViewById(R.id.editText_DureeGarantie);
        EditText DateEdit = (EditText) findViewById(R.id.editText_date);

        boolean remplit = true;
        // check if they are empty
        if (TextUtils.isEmpty(montantEdit.getText())) {
            montantEdit.setError("Veuillez saisir un montant.");
            montantEdit.setFocusable(true);
            remplit = false;
        }

        if (remplit) {
            if (Utils.getConnectivityStatus(getApplicationContext())) {
                Depense maDepense = null;
                DateFormat format = new SimpleDateFormat("yyyy-mm-dd", java.util.Locale.getDefault());
                if (garantieCheckBox.isChecked()) {
                    Date garantieDebutDate;
                    Date garantieFinDate;
                    try {
                        garantieDebutDate = format.parse(garantieDebutEditText.getText().toString());
                        garantieFinDate = format.parse(garantieFinEditText.getText().toString());

                        if(ImageBmp!=null)
                            uploadImage();
                        if(lienEnvoie!=null)
                            maDepense = new Depense(myDBHelper.getLastDepenseID(), format.parse(DateEdit.getText().toString()), Double.parseDouble(montantEdit.getText().toString()), storageHelper.getUtilisateur(this.getBaseContext()), domaineSpinner.getItemAtPosition(domaineSpinner.getSelectedItemPosition()).toString(), myDBHelper.getMagasinWithReference(magasinSpinner.getItemAtPosition(magasinSpinner.getSelectedItemPosition()).toString(), this), lienEnvoie, garantieDebutDate, garantieFinDate);
                        else{
                            maDepense = new Depense(myDBHelper.getLastDepenseID(), format.parse(DateEdit.getText().toString()), Double.parseDouble(montantEdit.getText().toString()), storageHelper.getUtilisateur(this.getBaseContext()), domaineSpinner.getItemAtPosition(domaineSpinner.getSelectedItemPosition()).toString(), myDBHelper.getMagasinWithReference(magasinSpinner.getItemAtPosition(magasinSpinner.getSelectedItemPosition()).toString(), this), "", garantieDebutDate, garantieFinDate);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        if(ImageBmp!=null)
                            uploadImage();
                        if(lienEnvoie!=null)
                            maDepense = new Depense(myDBHelper.getLastDepenseID(), format.parse(DateEdit.getText().toString()), Double.parseDouble(montantEdit.getText().toString()), storageHelper.getUtilisateur(this.getBaseContext()), domaineSpinner.getItemAtPosition(domaineSpinner.getSelectedItemPosition()).toString(), myDBHelper.getMagasinWithReference(magasinSpinner.getItemAtPosition(magasinSpinner.getSelectedItemPosition()).toString(), this), lienEnvoie);
                        else{
                            maDepense = new Depense(myDBHelper.getLastDepenseID(), format.parse(DateEdit.getText().toString()), Double.parseDouble(montantEdit.getText().toString()), storageHelper.getUtilisateur(this.getBaseContext()), domaineSpinner.getItemAtPosition(domaineSpinner.getSelectedItemPosition()).toString(), myDBHelper.getMagasinWithReference(magasinSpinner.getItemAtPosition(magasinSpinner.getSelectedItemPosition()).toString(), this), "");

                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                }

                Utilisateur user = storageHelper.getUtilisateur(this.getBaseContext());
                Log.e("json", user.getMesDepenses().toString());
                user.addDepense(maDepense);
                storageHelper.storeObject(this.getBaseContext(), user);

                myDBHelper.insertDepense(maDepense);
                Toast.makeText(this, "Dépense Créée !", Toast.LENGTH_LONG).show();
                this.finish();
            } else
                Toast.makeText(this, "Erreur ! Verifiez votre connection internet.", Toast.LENGTH_LONG).show();
        }


    }

    //----------date picker-----------------
    private EditText selectedEditText = null;

    public void selectDate(View view) {
        switch (view.getId()) {
            case R.id.editText_date:
                selectedEditText = (EditText) findViewById(R.id.editText_date);
                break;
            case R.id.editText_DebutGarantie:
                selectedEditText = (EditText) findViewById(R.id.editText_DebutGarantie);
                break;
            case R.id.editText_DureeGarantie:
                selectedEditText = (EditText) findViewById(R.id.editText_DureeGarantie);
                break;
        }
        DialogFragment newFragment = new SelectDateFragment();
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    private void populateSetDate(int year, int month, int day) {
        StringBuilder stringDate = new StringBuilder();
        stringDate.append(year).append("-");
        if (month < 10)
            stringDate.append("0");
        stringDate.append(month).append("-");
        if (day < 10)
            stringDate.append("0");
        stringDate.append(day);
        selectedEditText.setText(stringDate.toString());
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
            populateSetDate(yy, mm + 1, dd);
        }
    }
    //-------------end datepicker------------


    private String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String> {
            //ProgressDialog loading;
            final RequestHandler rh = new RequestHandler();
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);
                HashMap<String,String> data = new HashMap<>();
                data.put("image", uploadImage);
                return rh.sendPostRequest("http://berghuis-peter.net/FinanceNous/uploadImage.php", data);
            }
        }

        UploadImage uploadImage = new UploadImage();
        uploadImage.execute(ImageBmp);
    }

    private File returnFile(){
        final File path = new File( Environment.getExternalStorageDirectory(), this.getPackageName());
        if(!path.exists()){
            path.mkdir();
        }
        Calendar calendrier = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", java.util.Locale.getDefault());
        String date = dateformat.format(calendrier.getTime());
        filePathPhoto = new File(path, "photo_"+date+".jpg");
        return filePathPhoto;
    }
}

