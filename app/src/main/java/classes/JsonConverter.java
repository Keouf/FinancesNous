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

public class JsonConverter {


    public ArrayList<Depense> convertJsonArrayToDepenseArray(JSONArray jsonArray, Global g)
    {
        MyDBHelper myDBHelper =new MyDBHelper();
        JSONObject json;
        ArrayList<Depense> mesDepenses = new ArrayList<>();
        for (int i=0; i < jsonArray.length(); i++) {
            try {
                json = jsonArray.getJSONObject(i);
                mesDepenses.add(new Depense(json.getInt("id_depense"), makeDate(json.getString("date_depense")), json.getDouble("montant_depense"), g.getMainUtilisateur(), myDBHelper.getDomaineWithId(json.getInt("domaine")), myDBHelper.getMagasinWithId(json.getInt("magasin")), json.getString("piece_jointe")));
            } catch (JSONException e) {
                Log.e("json", "jsonExeption, vérifie le nomage des champs");
            }
        }
        return mesDepenses;
    }

    public Magasin jsonToMagasin(JSONObject json)
    {
        Magasin magasin = null;
        try {
            magasin = new Magasin(json.getInt("id_magasin"), json.getString("ref_magasin"), json.getString("adresse_magasin1"), json.getString("adresse_magasin2"), json.getString("code_postal_magasin"), json.getString("site_web_magasin"), json.getString("telephone_magasin"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  magasin;
    }

    public Date makeDate(String date)
    {
        Date myDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

        try {
            myDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  myDate;
    }

    public ArrayList<Depense> ConvertDepensesToDepenseArrayList(Activity act)
    {
        Global g = (Global)act.getApplication();
        ArrayList<Depense> Depenses = new ArrayList<Depense>();
        for (int i=1; i <= g.getMainUtilisateur().getMesDepenses().size(); i++)
            Depenses.add(g.getMainUtilisateur().getMesDepenses().get(i));
        return Depenses;
    }

    public ArrayList<String> ConvertDomaineToStringArrayList(JSONArray myJsonarray)
    {
        ArrayList<String> Domaines = new ArrayList<String>();
        JSONObject json = null;

        for (int i = 0; i < myJsonarray.length(); i++)
        {
            try {
                json = myJsonarray.getJSONObject(i);
                Domaines.add(json.getString("ref_domaine"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return Domaines;
    }

    public ArrayList<String> ConvertMagasinToStringArrayList(JSONArray myJsonarray)
    {
        ArrayList<String> Magasins = new ArrayList<String>();
        JSONObject json = null;

        for (int i = 0; i < myJsonarray.length(); i++)
        {
            try {
                json = myJsonarray.getJSONObject(i);
                Magasins.add(json.getString("ref_magasin"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return Magasins;
    }

    public Utilisateur ConvertJsonArrayToUtilisateur(JSONArray myJsonarray) throws Exception {
        Utilisateur utilisateur = null;
        JSONObject json = null;

        for (int i = 0; i < myJsonarray.length(); i++)
        {
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
