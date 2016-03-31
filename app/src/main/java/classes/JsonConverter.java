package classes;


import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public final class JsonConverter {

    static StorageHelper storageHelper;

    public static ArrayList<Depense> convertJsonArrayToDepenseArray(JSONArray jsonArray, Activity act) {
        MyDBHelper myDBHelper = new MyDBHelper();
        JSONObject json;
        ArrayList<Depense> mesDepenses = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                json = jsonArray.getJSONObject(i);
                mesDepenses.add(new Depense(json.getInt("id_depense"), makeDate(json.getString("date_depense")), json.getDouble("montant_depense"), storageHelper.getUtilisateur(act.getBaseContext()), myDBHelper.getDomaineWithId(json.getInt("domaine")), myDBHelper.getMagasinWithId(json.getInt("magasin")), json.getString("piece_jointe")));
            } catch (JSONException e) {
                Log.e("json", "jsonExeption, vérifie le nomage des champs");
            }
        }
        return mesDepenses;
    }

    public static Magasin jsonToMagasin(JSONObject json) {
        Magasin magasin = null;
        try {
            magasin = new Magasin(json.getInt("id_magasin"), json.getString("ref_magasin"), json.getString("adresse_magasin1"), json.getString("adresse_magasin2"), json.getString("code_postal_magasin"), json.getString("site_web_magasin"), json.getString("telephone_magasin"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return magasin;
    }

    public static Date makeDate(String date) {
        Date myDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", java.util.Locale.getDefault());

        try {
            myDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myDate;
    }


    public static ArrayList<String> ConvertDomaineToStringArrayList(JSONArray myJsonarray) {
        ArrayList<String> Domaines = new ArrayList<>();
        JSONObject json;

        for (int i = 0; i < myJsonarray.length(); i++) {
            try {
                json = myJsonarray.getJSONObject(i);
                Domaines.add(json.getString("ref_domaine"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return Domaines;
    }

    public static ArrayList<String> ConvertMagasinToStringArrayList(JSONArray myJsonarray) {
        ArrayList<String> Magasins = new ArrayList<>();
        JSONObject json;

        for (int i = 0; i < myJsonarray.length(); i++) {
            try {
                json = myJsonarray.getJSONObject(i);
                Magasins.add(json.getString("ref_magasin"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return Magasins;
    }

    public static Utilisateur ConvertJsonArrayToUtilisateur(JSONArray myJsonarray) throws Exception {
        Utilisateur utilisateur = null;
        JSONObject json;

        for (int i = 0; i < myJsonarray.length(); i++) {
            try {
                json = myJsonarray.getJSONObject(i);
                utilisateur = new Utilisateur(json.getInt("id_utilisateur"), json.getString("mail_utilisateur"), json.getString("mot_de_passe_utilisateur"));
            } catch (JSONException e) {
                e.printStackTrace();
                throw new Exception();
            }
        }
        return utilisateur;
    }

}
